package com.parkme.servlet;

import com.parkme.dao.DAOException;
import com.parkme.dao.ParkingSpotDAO;
import com.parkme.model.ParkingSpot;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/AddParkingServlet")
public class AddParkingServlet extends HttpServlet {
    private ParkingSpotDAO parkingSpotDAO = new ParkingSpotDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String location = request.getParameter("location");
        String availableSpotsStr = request.getParameter("availableSpots");

        if (location.isEmpty() || availableSpotsStr.isEmpty()) {
            request.setAttribute("error", "Please enter all fields");
            request.getRequestDispatcher("addParking.jsp").forward(request, response);
            return;
        }

        try {
            int availableSpots = Integer.parseInt(availableSpotsStr);
            ParkingSpot parkingSpot = new ParkingSpot(0, location, availableSpots);
            parkingSpotDAO.addParkingSpot(parkingSpot);
            response.sendRedirect("index.jsp");
        } catch (DAOException e) {
            throw new ServletException("Error adding parking spot", e);
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid number format for available spots");
            request.getRequestDispatcher("addParking.jsp").forward(request, response);
        }
    }
}