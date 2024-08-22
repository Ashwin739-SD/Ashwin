package com.papl.demo.TripData.Entity;

import jakarta.persistence.*;

import java.math.BigInteger;

@Entity
@Table(name = "trips_data_stable")
public class TripData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    BigInteger rowId;

    private  String tripNo;

    private String challanDate;

    private String origin;

    private  String destination;

    private  String driverName;


    public BigInteger getRowId() {
        return rowId;
    }

    public void setRowId(BigInteger rowId) {
        this.rowId = rowId;
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

}
