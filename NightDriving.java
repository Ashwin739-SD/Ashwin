package com.papl.demo.NightDriving.Entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "night_driving")
public class NightDriving {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	Long id;
	private String latLongDistance;
	private String vehicleNo;

	private String location;

	private LocalDateTime date;

	private String latitude;

	private String longitude;

	private BigInteger gpsDataId;

	private LocalDateTime callingTime;

	private  String tripNo;

	private String challanDate;

	private String origin;

	private  String destination;

	private  String driverName;

	private String endLocation;

	private BigInteger endGpsId;

	private String duration;

	private String kmDifference;

	private LocalDateTime endTime;



	public String getLatLongDistance() {
		return latLongDistance;
	}
	
	public void setLatLongDistance(String latLongDistance) {
		this.latLongDistance = latLongDistance;
	}
	
	public String getVehicleNo() {
		return vehicleNo;
	}
	
	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public BigInteger getGpsDataId() {
		return gpsDataId;
	}

	public void setGpsDataId(BigInteger gpsDataId) {
		this.gpsDataId = gpsDataId;
	}

	public LocalDateTime getCallingTime() {
		return callingTime;
	}

	public void setCallingTime(LocalDateTime callingTime) {
		this.callingTime = callingTime;
	}

	public String getTripNo() {
		return tripNo;
	}

	public void setTripNo(String tripNo) {
		this.tripNo = tripNo;
	}

	public String getChallanDate() {
		return challanDate;
	}

	public void setChallanDate(String challanDate) {
		this.challanDate = challanDate;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getEndLocation() {
		return endLocation;
	}

	public void setEndLocation(String endLocation) {
		this.endLocation = endLocation;
	}

	public BigInteger getEndGpsId() {
		return endGpsId;
	}

	public void setEndGpsId(BigInteger endGpsId) {
		this.endGpsId = endGpsId;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;}

	public String getKmDifference() {
		return kmDifference;
	}

	public void setKmDifference(String kmDifference) {
		this.kmDifference = kmDifference;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}
}
