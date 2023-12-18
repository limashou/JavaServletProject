package com.example.webappmpl_2.servlet.customer;

import com.example.webappmpl_2.hibernate.DAOCoffeeshopCustomer;
import com.example.webappmpl_2.hibernate.DAOOrderCoffeeshop;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/DeleteCustomer")
public class DeleteCustomerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long coffeeshopId = Long.parseLong(request.getParameter("coffeeshopId"));
        long customerId = Long.parseLong(request.getParameter("customerId"));
        DAOCoffeeshopCustomer.deleteCoffeeshopCustomerById(customerId);
        response.sendRedirect(request.getContextPath() + "/CustomerCoffeeshop?coffeeshopId=" + coffeeshopId);
    }
}
