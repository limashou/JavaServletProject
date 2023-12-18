package com.example.webappmpl_2.servlet.order;

import com.example.webappmpl_2.entity.Coffeeshop;
import com.example.webappmpl_2.entity.CoffeeshopCustomer;
import com.example.webappmpl_2.entity.OrderCoffeeshop;
import com.example.webappmpl_2.hibernate.DAOCoffeeshop;
import com.example.webappmpl_2.hibernate.DAOCoffeeshopCustomer;
import com.example.webappmpl_2.hibernate.DAOOrderCoffeeshop;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.util.List;

@WebServlet("/orderCoffeeshop")
public class OrderCoffeeshopAllServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String coffeeshopIdParam = request.getParameter("coffeeshopId");
        String customerIdParam = request.getParameter("customerId");
        if (coffeeshopIdParam != null) {
            long customeId = Long.parseLong(customerIdParam);
            long coffeeshopId = Long.parseLong(coffeeshopIdParam);
            Coffeeshop selectedCoffeeshop = DAOCoffeeshop.getCoffeeShopById(coffeeshopId);
            CoffeeshopCustomer customer = DAOCoffeeshopCustomer.getCoffeesCustomerhopById(customeId);
            if (customer != null) {
                List<OrderCoffeeshop> orders = DAOOrderCoffeeshop.getOrdersByCustomerWithItems(customer);
                request.setAttribute("selectedCoffeeshop", selectedCoffeeshop);
                request.setAttribute("selectedOrderCoffeeshop", customer);
                request.setAttribute("orders", orders);
                request.getRequestDispatcher("/order/coffeeshopOrders.jsp").forward(request, response);
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doGet(request, response);
    }
}

