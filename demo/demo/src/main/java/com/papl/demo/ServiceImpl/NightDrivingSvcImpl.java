package com.papl.demo.ServiceImpl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.logging.Logger;

import com.papl.demo.AxestracDb.Entity.GpsData;
import com.papl.demo.AxestracDb.Repo.GpsDataRepo;
import com.papl.demo.TripData.Entity.TripData;
import com.papl.demo.TripData.Repo.TripDataRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.papl.demo.NightDriving.Entity.NightDriving;
import com.papl.demo.NightDriving.Repo.NightDrivingRepo;

@Service
public class NightDrivingSvcImpl {

	private static final Logger logger = Logger.getLogger(NightDrivingSvcImpl.class.getName());

	@Autowired
	NightDrivingRepo nightDrivingRepo;
	@Autowired
	GpsDataRepo gpsDataRepo;

	@Autowired
	TripDataRepo tripDataRepo;

	public void nightDriving() {
		List<GpsData> gpsDataList = gpsDataRepo.fetchExtraShift();
		Map<String, NightDriving> currentVehicles = new HashMap<>();

		// Process current GPS data
		for (GpsData ex : gpsDataList) {

			float latLongDistance = Float.parseFloat(ex.getLatLongDistance());

			if (latLongDistance > 0.100) {
				String vehicleNo = ex.getVehicleNo();
				List<NightDriving> existingRecords = nightDrivingRepo.findByVehicleNo(vehicleNo);

				// Determine if vehicle is already active or needs a new record
				boolean isActive = existingRecords.stream()
						.anyMatch(record -> record.getEndLocation() == null && record.getEndGpsId() == null);

				if (isActive) {
					NightDriving activeRecord = existingRecords.stream()
							.filter(record -> record.getEndLocation() == null && record.getEndGpsId() == null)
							.findFirst()
							.orElse(null);

					if (activeRecord != null) {
						// Update the in-memory map with the active record
						currentVehicles.put(vehicleNo, activeRecord);
					}
				} else {
					// Create a new NightDriving record if there is no active record
					NightDriving newNightDriving = createNightDrivingRecord(ex);
					nightDrivingRepo.save(newNightDriving);

					String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
					logger.info("Active night driving: VehicleNo=" + newNightDriving.getVehicleNo() + " Timestamp=" + timestamp);
					currentVehicles.put(vehicleNo, newNightDriving);
				}
			}else {
					NightDriving lastRecord = nightDrivingRepo.findActiveRecordByVehicleNo(ex.getVehicleNo());
					float distance = Float.parseFloat(ex.getLatLongDistance());
					if (lastRecord!=null) {
						if (ex.getVehicleNo().equals(lastRecord.getVehicleNo())) {
							// Update endLocation and other details for vehicles that are not in currentVehicles
							lastRecord.setEndLocation(ex.getLocation());

							lastRecord.setEndGpsId(ex.getId());
							logger.info("Fetching startGpsId: " + lastRecord.getGpsDataId());
							logger.info("Fetching EndGpsId: " + ex.getId());
							BigDecimal kmDis = gpsDataRepo.findTotalLatLongDistance(lastRecord.getGpsDataId(), ex.getId(), lastRecord.getVehicleNo());
							logger.info("Fetching kmDis values for vehicle: " + kmDis);
							if (kmDis != null) {
								lastRecord.setKmDifference(kmDis.toString());
							}

							// Set duration
							LocalDateTime startDateTime = lastRecord.getDate();
							LocalDateTime endDateTime = ex.getDate();
							lastRecord.setDuration(calculateDuration(startDateTime, endDateTime));
							lastRecord.setEndTime(ex.getDate());
							nightDrivingRepo.save(lastRecord);
							logger.info("Updated end location and duration for vehicle: " + lastRecord.getVehicleNo());
						}
					}
			}
		}
	}

	private String calculateDuration(LocalDateTime startDateTime, LocalDateTime endDateTime) {
		if (startDateTime != null && endDateTime != null) {
			Duration duration = Duration.between(startDateTime, endDateTime);
			long hours = duration.toHours();
			long minutes = duration.toMinutes() % 60;
			long seconds = duration.getSeconds() % 60;

			// Format as "HH.MM.SS"
			return String.format("%02d.%02d.%02d", hours, minutes, seconds);
		}
		return null;
	}

	private NightDriving createNightDrivingRecord(GpsData ex) {
		NightDriving newNightDriving = new NightDriving();
		newNightDriving.setVehicleNo(ex.getVehicleNo());
		newNightDriving.setLatLongDistance(ex.getLatLongDistance());
		newNightDriving.setDate(ex.getDate());
		newNightDriving.setLocation(ex.getLocation());
		newNightDriving.setLatitude(ex.getLattitude());
		newNightDriving.setLongitude(ex.getLongitude());
		newNightDriving.setGpsDataId(ex.getId());
		newNightDriving.setCallingTime(ex.getCallingTime());


		TripData lastTripOfVehicle = tripDataRepo.getTripByVehicleNo(ex.getVehicleNo());
		newNightDriving.setTripNo(lastTripOfVehicle.getTripNo());
		newNightDriving.setChallanDate(lastTripOfVehicle.getChallanDate());
		newNightDriving.setOrigin(lastTripOfVehicle.getOrigin());
		newNightDriving.setDestination(lastTripOfVehicle.getDestination());
		newNightDriving.setDriverName(lastTripOfVehicle.getDriverName());

		return newNightDriving;
	}

}


