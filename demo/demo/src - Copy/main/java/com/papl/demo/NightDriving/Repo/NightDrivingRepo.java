package com.papl.demo.NightDriving.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.papl.demo.NightDriving.Entity.NightDriving;

import java.math.BigInteger;
import java.util.List;
import java.util.Set;

@Repository
public interface NightDrivingRepo extends JpaRepository<NightDriving, Long>{


    public List<NightDriving> findByVehicleNo(String vehicleNo);

    @Query(value = "SELECT * FROM night_driving WHERE vehicleNo = :vehicleNo AND endLocation IS NULL AND endGpsId IS NULL", nativeQuery = true)
    NightDriving findActiveRecordByVehicleNo(@Param("vehicleNo") String vehicleNo);
}
