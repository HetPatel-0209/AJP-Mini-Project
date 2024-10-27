package com.parkme.servlet;

import com.google.gson.Gson;
import com.parkme.dao.BookingDAO;
import com.parkme.dao.CarDAO;
import com.parkme.dao.ParkingSpotDAO;
import com.parkme.dao.DAOException;
import com.parkme.model.Booking;
import com.parkme.model.Car;
import com.parkme.model.ParkingSpot;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/BookingServlet")
public class BookingServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(BookingServlet.class.getName());
    private CarDAO carDAO;
    private ParkingSpotDAO parkingSpotDAO;
    private BookingDAO bookingDAO;

    @Override
    public void init() throws ServletException {
        carDAO = new CarDAO();
        parkingSpotDAO = new ParkingSpotDAO();
        bookingDAO = new BookingDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        if ("getCars".equals(action)) {
            try {
                List<Car> cars = carDAO.getAllCars();
                response.getWriter().write(new Gson().toJson(cars));
            } catch (DAOException e) {
                LOGGER.log(Level.SEVERE, "Error retrieving cars", e);
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } else if ("getParkingSpots".equals(action)) {
            try {
                List<ParkingSpot> parkingSpots = parkingSpotDAO.getAllParkingSpots();
                response.getWriter().write(new Gson().toJson(parkingSpots));
            } catch (DAOException e) {
                LOGGER.log(Level.SEVERE, "Error retrieving parking spots", e);
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } else {
            request.getRequestDispatcher("/booking.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int carId = Integer.parseInt(request.getParameter("carId"));
            int parkingSpotId = Integer.parseInt(request.getParameter("parkingSpotId"));
            Timestamp startTime = Timestamp.valueOf(request.getParameter("startTime").replace("T", " ") + ":00");
            Timestamp endTime = Timestamp.valueOf(request.getParameter("endTime").replace("T", " ") + ":00");

            Booking booking = new Booking(0, 1, carId, parkingSpotId, startTime, endTime); // Assuming a static userId for now
            bookingDAO.addBooking(booking);

            response.sendRedirect(request.getContextPath() + "/viewBookings.jsp"); // Redirect to view bookings after success
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error booking parking spot", e);
            request.setAttribute("error", "Failed to book parking spot. Please try again.");
            request.getRequestDispatcher("/booking.jsp").forward(request, response);
        }
    }
}
