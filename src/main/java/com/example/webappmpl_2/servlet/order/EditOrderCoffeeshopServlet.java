package com.example.webappmpl_2.servlet.order;

import com.example.webappmpl_2.entity.Coffeeshop;
import com.example.webappmpl_2.entity.CoffeeshopCustomer;
import com.example.webappmpl_2.entity.CoffeeshopMenu;
import com.example.webappmpl_2.entity.OrderCoffeeshop;
import com.example.webappmpl_2.hibernate.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/EditOrderCoffeeshopServlet")
public class EditOrderCoffeeshopServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long coffeeshopId = Long.parseLong(request.getParameter("coffeeshopId"));
        long orderId = Long.parseLong(request.getParameter("orderId"));
        long customerId = Long.parseLong(request.getParameter("customerId"));
        String orderDate = request.getParameter("order_date");
        try {
            LocalTime time = LocalTime.parse(orderDate);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            String formattedTime = time.format(formatter);
            String[] itemIds = request.getParameterValues("selectedItems");
            List<CoffeeshopMenu> selectedMenuItems = new ArrayList<>();
            for (String itemId : itemIds) {
                long menuItemId = Long.parseLong(itemId);
                CoffeeshopMenu menuItem = DAOCoffeeshopMenu.getMenuItemById(menuItemId);
                selectedMenuItems.add(menuItem);
            }
            double totalAmount = DAOCoffeeshopMenu.calculateTotalAmount(selectedMenuItems);
            boolean status = Boolean.parseBoolean(request.getParameter("status"));
            boolean paymentMethod = Boolean.parseBoolean(request.getParameter("payment_method"));
            OrderCoffeeshop existingOrder = DAOOrderCoffeeshop.getOrderCoffeeshopById(orderId);
            if (existingOrder != null) {
                existingOrder.setOrder_date(formattedTime);
                existingOrder.setItems(selectedMenuItems);
                existingOrder.setTotal_amount(totalAmount);
                existingOrder.setStatus(status);
                existingOrder.setPayment_method(paymentMethod);
                HibernateUtil.updateObject(existingOrder);
            }
            response.sendRedirect(request.getContextPath() + "/orderCoffeeshop?coffeeshopId="+ coffeeshopId +"&customerId=" + customerId);
        }
        catch (DateTimeParseException e) {
            request.setAttribute("errorMessage", "Invalid order date format. Please use the HH:mm format for order date.");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String coffeeshopIdParam = request.getParameter("coffeeshopId");
        String customerIdParam = request.getParameter("customerId");
        String orderIdParam = request.getParameter("orderId");
        if (coffeeshopIdParam != null && !coffeeshopIdParam.isEmpty() && orderIdParam != null && !orderIdParam.isEmpty()) {
            long orderId = Long.parseLong(orderIdParam);
            long customerId = Long.parseLong(customerIdParam);
            long shopId = Long.parseLong(coffeeshopIdParam);
            CoffeeshopCustomer selectedOrderCoffeeshop = DAOCoffeeshopCustomer.getCoffeesCustomerhopById(customerId);
            Coffeeshop selectedCoffeeshop = DAOCoffeeshop.getCoffeeShopById(shopId);
            request.setAttribute("selectedCoffeeshop", selectedCoffeeshop);
            List<CoffeeshopMenu> menu = DAOCoffeeshopMenu.getMenuByCoffeeshop(selectedCoffeeshop);
            request.setAttribute("menuList",menu);
            request.setAttribute("selectedOrderCoffeeshop", selectedOrderCoffeeshop);
            OrderCoffeeshop orderCoffeeshop = DAOOrderCoffeeshop.getOrderCoffeeshopById(orderId);
            request.setAttribute("order", orderCoffeeshop);
            request.getRequestDispatcher("/order/editOrderCoffeeshop.jsp").forward(request, response);
        }
    }
}

