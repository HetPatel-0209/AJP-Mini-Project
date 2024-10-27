package com.parkme.model;

import java.sql.Timestamp;

public class Booking {
    private int id;
    private int userId;
    private int carId;
    private int parkingSpotId;
    private Timestamp startTime;
    private Timestamp endTime;

    public Booking(int id, int userId, int carId, int parkingSpotId, Timestamp startTime, Timestamp endTime) {
        this.id = id;
        this.userId = userId;
        this.carId = carId;
        this.parkingSpotId = parkingSpotId;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public int getCarId() { return carId; }
    public void setCarId(int carId) { this.carId = carId; }
    public int getParkingSpotId() { return parkingSpotId; }
    public void setParkingSpotId(int parkingSpotId) { this.parkingSpotId = parkingSpotId; }
    public Timestamp getStartTime() { return startTime; }
    public void setStartTime(Timestamp startTime) { this.startTime = startTime; }
    public Timestamp getEndTime() { return endTime; }
    public void setEndTime(Timestamp endTime) { this.endTime = endTime; }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", userId=" + userId +
                ", carId=" + carId +
                ", parkingSpotId=" + parkingSpotId +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}