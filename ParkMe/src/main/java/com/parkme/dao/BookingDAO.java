package com.parkme.dao;

import com.parkme.model.Booking;
import com.parkme.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;

public class BookingDAO {
    private static final Logger LOGGER = Logger.getLogger(BookingDAO.class.getName());

    public void addBooking(Booking booking) throws DAOException {
        String sql = "INSERT INTO bookings (user_id, car_id, parking_spot_id, start_time, end_time) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, booking.getUserId());
            pstmt.setInt(2, booking.getCarId());
            pstmt.setInt(3, booking.getParkingSpotId());
            pstmt.setTimestamp(4, booking.getStartTime());
            pstmt.setTimestamp(5, booking.getEndTime());
            pstmt.executeUpdate();
            LOGGER.info("Booking added successfully: " + booking);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error adding booking", e);
            throw new DAOException("Error adding booking", e);
        }
    }

    public Booking getBookingById(int id) throws DAOException {
        String sql = "SELECT * FROM bookings WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Booking(rs.getInt("id"), rs.getInt("user_id"),
                            rs.getInt("car_id"), rs.getInt("parking_spot_id"),
                            rs.getTimestamp("start_time"), rs.getTimestamp("end_time"));
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error getting booking by ID", e);
            throw new DAOException("Error getting booking by ID", e);
        }
        return null;
    }
    public List<Booking> getAllBookings() throws DAOException {
    List<Booking> bookings = new ArrayList<>();
    String sql = "SELECT * FROM bookings";
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        try (ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                bookings.add(new Booking(rs.getInt("id"), rs.getInt("user_id"),
                        rs.getInt("car_id"), rs.getInt("parking_spot_id"),
                        rs.getTimestamp("start_time"), rs.getTimestamp("end_time")));
            }
        }
    } catch (SQLException e) {
        LOGGER.log(Level.SEVERE, "Error getting all bookings", e);
        throw new DAOException("Error getting all bookings", e);
    }
    return bookings;
}

public void updateBooking(Booking booking) throws DAOException {
    String sql = "UPDATE bookings SET user_id = ?, car_id = ?, parking_spot_id = ?, start_time = ?, end_time = ? WHERE id = ?";
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setInt(1, booking.getUserId());
        pstmt.setInt(2, booking.getCarId());
        pstmt.setInt(3, booking.getParkingSpotId());
        pstmt.setTimestamp(4, booking.getStartTime());
        pstmt.setTimestamp(5, booking.getEndTime());
        pstmt.setInt(6, booking.getId());
        pstmt.executeUpdate();
        LOGGER.info("Booking updated successfully: " + booking);
    } catch (SQLException e) {
        LOGGER.log(Level.SEVERE, "Error updating booking", e);
        throw new DAOException("Error updating booking", e);
    }
}

public void deleteBooking(int id) throws DAOException {
    String sql = "DELETE FROM bookings WHERE id = ?";
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setInt(1, id);
        pstmt.executeUpdate();
        LOGGER.info("Booking deleted successfully: " + id);
    } catch (SQLException e) {
        LOGGER.log(Level.SEVERE, "Error deleting booking", e);
        throw new DAOException("Error deleting booking", e);
    }
}}