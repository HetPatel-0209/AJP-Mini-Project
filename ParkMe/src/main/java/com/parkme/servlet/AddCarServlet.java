package com.parkme.servlet;

import com.parkme.dao.CarDAO;
import com.parkme.dao.DAOException;
import com.parkme.model.Car;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/AddCarServlet")
public class AddCarServlet extends HttpServlet {
    private CarDAO carDAO = new CarDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String make = request.getParameter("make");
        String model = request.getParameter("model");
        String licensePlate = request.getParameter("licensePlate");

        if (make == null || model == null || licensePlate == null || 
            make.trim().isEmpty() || model.trim().isEmpty() || licensePlate.trim().isEmpty()) {
            request.setAttribute("error", "Please fill in all fields");
            request.getRequestDispatcher("addCar.jsp").forward(request, response);
            return;
        }

        try {
            Car car = new Car(0, make, model, licensePlate);
            carDAO.addCar(car);
            request.setAttribute("success", "Car added successfully!");
            request.getRequestDispatcher("addCar.jsp").forward(request, response);
        } catch (DAOException e) {
            request.setAttribute("error", "Error adding car: " + e.getMessage());
            request.getRequestDispatcher("addCar.jsp").forward(request, response);
        }
    }
}