package com.papl.demo.AxestracDb.Repo;

import com.papl.demo.AxestracDb.Entity.GpsData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

@Repository
public interface GpsDataRepo extends JpaRepository<GpsData, BigInteger>{
	
	
	@Query(value = "select * from gps_data order by id desc limit 497", nativeQuery=true)
	List<GpsData> fetchExtraShift();

	@Query(value = "SELECT SUM(CAST(latLongDistance AS DECIMAL(20, 10))) " +
			"FROM axestrack.gps_data " +
			"WHERE vehicleNo = :vehicleNo"+" And id BETWEEN :startGpsId AND :endGpsId",
			nativeQuery = true)
	BigDecimal findTotalLatLongDistance(@Param("startGpsId") BigInteger startGpsId,
										@Param("endGpsId") BigInteger endGpsId,@Param("vehicleNo") String vehicleNo);

}
