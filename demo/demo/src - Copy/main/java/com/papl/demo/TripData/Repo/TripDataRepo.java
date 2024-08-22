package com.papl.demo.TripData.Repo;

import com.papl.demo.TripData.Entity.TripData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface TripDataRepo extends JpaRepository<TripData, BigInteger> {

    @Query(value = "SELECT * FROM  professionalautomotives.trips_data_stable where vehicleNo = :vehicleNo order by creationDate  desc limit 1", nativeQuery = true)
    TripData getTripByVehicleNo(@Param("vehicleNo") String vehicleNo);
}
