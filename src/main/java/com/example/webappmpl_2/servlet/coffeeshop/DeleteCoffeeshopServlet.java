package com.example.webappmpl_2.servlet.coffeeshop;

import com.example.webappmpl_2.hibernate.DAOCoffeeshop;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;

@WebServlet("/DeleteCoffeeshopServlet")
public class DeleteCoffeeshopServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long coffeeshopId = Long.parseLong(request.getParameter("coffeeshopId"));
        DAOCoffeeshop.deleteCoffeeShopById(coffeeshopId);
        response.sendRedirect(request.getContextPath() + "/coffeeshop");
    }
}
