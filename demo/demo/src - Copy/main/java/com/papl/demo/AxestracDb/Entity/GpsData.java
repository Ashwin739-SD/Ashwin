package com.papl.demo.AxestracDb.Entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "gps_data")
public class GpsData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    BigInteger id;
    private String latLongDistance;
    private String vehicleNo;

    private String location;

    private LocalDateTime date;

    private String lattitude;

    private String longitude;

    private LocalDateTime callingTime;

    private BigDecimal odometer;


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

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
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

    public String getLattitude() {
        return lattitude;
    }

    public void setLattitude(String lattitudeitude) {
        this.lattitude = lattitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public LocalDateTime getCallingTime() {
        return callingTime;
    }

    public void setCallingTime(LocalDateTime callingTime) {
        this.callingTime = callingTime;
    }

    public BigDecimal getOdometer() {
        return odometer;
    }

    public void setOdometer(BigDecimal odometer) {
        this.odometer = odometer;
    }


}
