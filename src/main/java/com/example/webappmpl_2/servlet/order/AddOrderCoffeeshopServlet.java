package com.example.webappmpl_2.servlet.order;

import com.example.webappmpl_2.entity.CoffeeshopCustomer;
import com.example.webappmpl_2.hibernate.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.example.webappmpl_2.entity.Coffeeshop;
import com.example.webappmpl_2.entity.CoffeeshopMenu;
import com.example.webappmpl_2.entity.OrderCoffeeshop;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/AddOrderCoffeeshopServlet")
public class AddOrderCoffeeshopServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String coffeeshopIdParam = request.getParameter("coffeeshopId");
        String customerIdParam = request.getParameter("customerId");
        if (customerIdParam != null && coffeeshopIdParam != null) {
            try {
                long customerId = Long.parseLong(customerIdParam);
                String orderDate = request.getParameter("order_date");
                try {
                    LocalTime time = LocalTime.parse(orderDate);
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                    String formattedTime = time.format(formatter);
                    String[] menuIds = request.getParameterValues("selectedItems");
                    List<CoffeeshopMenu> selectedMenuItems = new ArrayList<>();
                    for (String menuId : menuIds) {
                        long menuItemId = Long.parseLong(menuId);
                        CoffeeshopMenu menuItem = DAOCoffeeshopMenu.getMenuItemById(menuItemId);
                        selectedMenuItems.add(menuItem);
                    }
                    double totalAmount = DAOCoffeeshopMenu.calculateTotalAmount(selectedMenuItems);
                    boolean status = Boolean.parseBoolean(request.getParameter("status"));
                    boolean paymentMethod = Boolean.parseBoolean(request.getParameter("paymentMethod"));
                    long coffeeshopId = Long.parseLong(coffeeshopIdParam);
                    CoffeeshopCustomer customer = DAOCoffeeshopCustomer.getCoffeesCustomerhopById(customerId);
                    OrderCoffeeshop order = new OrderCoffeeshop(customer, formattedTime, selectedMenuItems, totalAmount, status, paymentMethod);
                    HibernateUtil.addObjectToDB(order);
                    response.sendRedirect(request.getContextPath() + "/orderCoffeeshop?coffeeshopId="+ coffeeshopId +"&customerId=" + customerId);
                } catch (DateTimeParseException e) {
                    request.setAttribute("errorMessage", "Invalid data or order date format. Please use the HH:mm format for order date.");
                    request.getRequestDispatcher("/error.jsp").forward(request, response);
                }
            } catch (NumberFormatException e) {
                response.getWriter().println("Invalid coffeeshopId parameter");
            }
        }
//        else {
//            response.getWriter().println("coffeeshopId parameter is missing");
//            response.sendRedirect(request.getContextPath() + "/coffeeshop");
//        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String customerIdParam = request.getParameter("customerId");
        String coffeeshopIdParam = request.getParameter("coffeeshopId");
        if (customerIdParam != null && !customerIdParam.isEmpty()  && !coffeeshopIdParam.isEmpty() && coffeeshopIdParam != null) {
            try {
                long coffeeshopId = Long.parseLong(coffeeshopIdParam);
                long customerId = Long.parseLong(customerIdParam);
                Coffeeshop selectedCoffeeshop = DAOCoffeeshop.getCoffeeShopById(coffeeshopId);
                CoffeeshopCustomer selectedOrderCoffeeshop = DAOCoffeeshopCustomer.getCoffeesCustomerhopById(customerId);
                List<CoffeeshopMenu> menu = DAOCoffeeshopMenu.getMenuByCoffeeshop(selectedCoffeeshop);
                request.setAttribute("menuList",menu);
                request.setAttribute("selectedCoffeeshop", selectedCoffeeshop);
                request.setAttribute("selectedOrderCoffeeshop", selectedOrderCoffeeshop);
                request.getRequestDispatcher("/order/addOrder.jsp").forward(request, response);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
    }
}
