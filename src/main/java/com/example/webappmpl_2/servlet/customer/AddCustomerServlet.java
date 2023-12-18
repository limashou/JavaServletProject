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
import java.util.List;

@WebServlet("/AddCustomer")
public class AddCustomerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String coffeeshopIdParam = request.getParameter("coffeeshopId");
        if (coffeeshopIdParam != null) {
            try {
                long coffeeshopId = Long.parseLong(coffeeshopIdParam);
                String name = request.getParameter("name");
                String email = request.getParameter("email");
                String phone = request.getParameter("phone");
                Coffeeshop coffeeshop = DAOCoffeeshop.getCoffeeShopById(coffeeshopId);
                CoffeeshopCustomer customer = new CoffeeshopCustomer(coffeeshop,name, email, phone);
                HibernateUtil.addObjectToDB(customer);
                response.sendRedirect(request.getContextPath() + "/CustomerCoffeeshop?coffeeshopId=" + coffeeshopId);

            } catch (NumberFormatException e) {
                response.getWriter().println("Invalid coffeeshopId parameter");
            }
        } else {
            response.getWriter().println("coffeeshopId parameter is missing");
            response.sendRedirect(request.getContextPath() + "/coffeeshop");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String coffeeshopIdParam = request.getParameter("coffeeshopId");
        if (coffeeshopIdParam != null && !coffeeshopIdParam.isEmpty()) {
            try {
                long shopId = Long.parseLong(coffeeshopIdParam);
                Coffeeshop selectedCoffeeshop = DAOCoffeeshop.getCoffeeShopById(shopId);
                List<CoffeeshopCustomer> customer = DAOCoffeeshopCustomer.getCustomerByCoffeeshop(selectedCoffeeshop);
                request.setAttribute("selectedCoffeeshop", selectedCoffeeshop);
                request.getRequestDispatcher("/customer/addCustomer.jsp").forward(request, response);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
    }

}
