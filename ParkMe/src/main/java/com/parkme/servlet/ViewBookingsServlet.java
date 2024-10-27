package com.parkme.servlet;

import com.parkme.dao.BookingDAO;
import com.parkme.dao.DAOException;
import com.parkme.model.Booking;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/ViewBookingsServlet")
public class ViewBookingsServlet extends HttpServlet {
    private BookingDAO bookingDAO = new BookingDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Booking> bookings = bookingDAO.getAllBookings(); 
            request.setAttribute("bookings", bookings);
            request.getRequestDispatcher("viewBookings.jsp").forward(request, response);
        } catch (DAOException ex) {
            Logger.getLogger(ViewBookingsServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("error", "Error viewing bookings");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}