package com.parkme.dao;

import com.parkme.model.Car;
import com.parkme.model.Booking;
import com.parkme.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;

public class CarDAO {
    private static final Logger LOGGER = Logger.getLogger(CarDAO.class.getName());

    public void addCar(Car car) throws DAOException {
        String sql = "INSERT INTO cars (make, model, license_plate) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, car.getMake());
            pstmt.setString(2, car.getModel());
            pstmt.setString(3, car.getLicensePlate());
            pstmt.executeUpdate();
            LOGGER.info("Car added successfully: " + car);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error adding car", e);
            throw new DAOException("Error adding car", e);
        }
    }

    public Car getCarById(int id) throws DAOException {
        String sql = "SELECT * FROM cars WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Car(rs.getInt("id"), rs.getString("make"),
                            rs.getString("model"), rs.getString("license_plate"));
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error getting car by ID", e);
            throw new DAOException("Error getting car by ID", e);
        }
        return null;
    }

   public List<Car> getAllCars() throws DAOException {
        List<Car> cars = new ArrayList<>();
        String sql = "SELECT * FROM cars";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                cars.add(new Car(rs.getInt("id"), rs.getString("make"),
                        rs.getString("model"), rs.getString("license_plate")));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error getting all cars", e);
            throw new DAOException("Error getting all cars", e);
        }
        return cars;
    }


    public List<Booking> getBookingsByCarId(int carId) throws DAOException {
        List<Booking> bookings = new ArrayList<>();
        String sql = "SELECT * FROM bookings WHERE car_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, carId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    bookings.add(new Booking(rs.getInt("id"), rs.getInt("user_id"),
                            rs.getInt("car_id"), rs.getInt("parking_spot_id"),
                            rs.getTimestamp("start_time"), rs.getTimestamp("end_time")));
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error getting bookings by car ID", e);
            throw new DAOException("Error getting bookings by car ID", e);
        }
        return bookings;
    }
}