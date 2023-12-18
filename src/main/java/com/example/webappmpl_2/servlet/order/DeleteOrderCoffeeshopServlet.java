package com.example.webappmpl_2.servlet.order;

import com.example.webappmpl_2.hibernate.DAOOrderCoffeeshop;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;

@WebServlet("/DeleteOrderCoffeeshopServlet")
public class DeleteOrderCoffeeshopServlet  extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long coffeeshopId = Long.parseLong(request.getParameter("coffeeshopId"));
        long customerId = Long.parseLong(request.getParameter("customerId"));
        long orderId = Long.parseLong(request.getParameter("orderId"));
        DAOOrderCoffeeshop.deleteOrderCoffeeshopById(orderId);
        response.sendRedirect(request.getContextPath() + "/orderCoffeeshop?coffeeshopId="+ coffeeshopId +"&customerId=" + customerId);

    }
}
