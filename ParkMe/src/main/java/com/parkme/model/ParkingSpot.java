package com.parkme.model;

public class ParkingSpot {
    private int id;
    private String location;
    private int availableSpots;

    public ParkingSpot(int id, String location, int availableSpots) {
        this.id = id;
        this.location = location;
        this.availableSpots = availableSpots;
    }

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public int getAvailableSpots() { return availableSpots; }
    public void setAvailableSpots(int availableSpots) { this.availableSpots = availableSpots; }

    @Override
    public String toString() {
        return "ParkingSpot{" +
                "id=" + id +
                ", location='" + location + '\'' +
                ", availableSpots=" + availableSpots +
                '}';
    }
}
