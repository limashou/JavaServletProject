package com.example.webappmpl_2.servlet.customer;

import com.example.webappmpl_2.entity.Coffeeshop;
import com.example.webappmpl_2.entity.CoffeeshopCustomer;
import com.example.webappmpl_2.hibernate.DAOCoffeeshop;
import com.example.webappmpl_2.hibernate.DAOCoffeeshopCustomer;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;


@WebServlet("/CustomerCoffeeshop")
public class CustomerAllServlet  extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String coffeeshopIdParam = request.getParameter("coffeeshopId");
        if (coffeeshopIdParam != null) {
            long coffeeshopId = Long.parseLong(coffeeshopIdParam);
            Coffeeshop selectedCoffeeshop = DAOCoffeeshop.getCoffeeShopById(coffeeshopId);
            if (selectedCoffeeshop != null) {
                List<CoffeeshopCustomer> customer = DAOCoffeeshopCustomer.getCustomerByCoffeeshop(selectedCoffeeshop);
                request.setAttribute("selectedCoffeeshop", selectedCoffeeshop);
                request.setAttribute("customer", customer);
                request.getRequestDispatcher("/customer/customerAll.jsp").forward(request,response);
            }
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
            doGet(request, response);
    }
}

