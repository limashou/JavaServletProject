package com.example.webappmpl_2.servlet.customer;

import com.example.webappmpl_2.entity.Coffeeshop;
import com.example.webappmpl_2.entity.CoffeeshopCustomer;
import com.example.webappmpl_2.hibernate.DAOCoffeeshop;
import com.example.webappmpl_2.hibernate.DAOCoffeeshopCustomer;
import com.example.webappmpl_2.hibernate.HibernateUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/EditCustomer")
public class EditCustomerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long coffeeshopId = Long.parseLong(request.getParameter("coffeeshopId"));
        long customerId = Long.parseLong(request.getParameter("customerId"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        CoffeeshopCustomer existingOrder = DAOCoffeeshopCustomer.getCoffeesCustomerhopById(customerId);
        if (existingOrder != null) {
            existingOrder.setCust_name(name);
            existingOrder.setCust_email(email);
            existingOrder.setCust_phone(phone);
            HibernateUtil.updateObject(existingOrder);
        }
        response.sendRedirect(request.getContextPath() + "/CustomerCoffeeshop?coffeeshopId=" + coffeeshopId);
    }




    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String coffeeshopIdParam = request.getParameter("coffeeshopId");
        String customerIdParam = request.getParameter("customerId");
        if (coffeeshopIdParam != null && !coffeeshopIdParam.isEmpty() && customerIdParam != null && !customerIdParam.isEmpty()) {
            long customerId = Long.parseLong(customerIdParam);
            long shopId = Long.parseLong(coffeeshopIdParam);
            Coffeeshop selectedCoffeeshop = DAOCoffeeshop.getCoffeeShopById(shopId);
            request.setAttribute("selectedCoffeeshop", selectedCoffeeshop);
            CoffeeshopCustomer customer = DAOCoffeeshopCustomer.getCoffeesCustomerhopById(customerId);
            request.setAttribute("customer", customer);
            request.getRequestDispatcher("/customer/editCustomer.jsp").forward(request, response);
        }
    }
}
