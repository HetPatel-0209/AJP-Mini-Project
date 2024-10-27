package com.parkme.dao;

import com.parkme.model.ParkingSpot;
import com.parkme.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;

public class ParkingSpotDAO {
    private static final Logger LOGGER = Logger.getLogger(ParkingSpotDAO.class.getName());

    // Method to add a parking spot with user-defined available spots
    public void addParkingSpot(ParkingSpot parkingSpot) throws DAOException {
        String sql = "INSERT INTO parking_spots (location, available_spots) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, parkingSpot.getLocation());
            pstmt.setInt(2, parkingSpot.getAvailableSpots());
            pstmt.executeUpdate();
            LOGGER.info("Parking spot added successfully: " + parkingSpot);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error adding parking spot", e);
            throw new DAOException("Error adding parking spot", e);
        }
    }

    // Method to retrieve a parking spot by ID
    public ParkingSpot getParkingSpotById(int id) throws DAOException {
        String sql = "SELECT * FROM parking_spots WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int availableSpots = rs.getInt("available_spots");
                    boolean isAvailable = availableSpots > 0; // Parking is available if spots are > 0
                    return new ParkingSpot(rs.getInt("id"), rs.getString("location"), availableSpots);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error getting parking spot by ID", e);
            throw new DAOException("Error getting parking spot by ID", e);
        }
        return null;
    }

    // Method to retrieve all parking spots
    public List<ParkingSpot> getAllParkingSpots() throws DAOException {
        List<ParkingSpot> parkingSpots = new ArrayList<>();
        String sql = "SELECT * FROM parking_spots";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int availableSpots = rs.getInt("available_spots");
                boolean isAvailable = availableSpots > 0; // Parking is available if spots are > 0
                parkingSpots.add(new ParkingSpot(rs.getInt("id"), rs.getString("location"), availableSpots));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error getting all parking spots", e);
            throw new DAOException("Error getting all parking spots", e);
        }
        return parkingSpots;
    }

    // Method to update available spots when parking spots are booked or freed
    public void updateAvailableSpots(int parkingSpotId, int newAvailableSpots) throws DAOException {
        String sql = "UPDATE parking_spots SET available_spots = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, newAvailableSpots);
            pstmt.setInt(2, parkingSpotId);
            pstmt.executeUpdate();
            LOGGER.info("Updated available spots for parking spot ID: " + parkingSpotId);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error updating available spots", e);
            throw new DAOException("Error updating available spots", e);
        }
    }
}
