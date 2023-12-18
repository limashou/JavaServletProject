package com.example.webappmpl_2.servlet.coffeeshop;

import com.example.webappmpl_2.entity.Coffeeshop;
import com.example.webappmpl_2.hibernate.DAOCoffeeshop;
import com.example.webappmpl_2.hibernate.HibernateUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/EditCoffeeshopServlet")
public class EditCoffeeshopServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String coffeeshopIdParam = request.getParameter("coffeeshopId");
        if (coffeeshopIdParam != null && !coffeeshopIdParam.isEmpty()) {
            long coffeeshopId = Long.parseLong(coffeeshopIdParam);
            String name = request.getParameter("name");
            String address = request.getParameter("address");
            int rate = Integer.parseInt(request.getParameter("rate"));
            int workingHours = Integer.parseInt(request.getParameter("working_hours"));
            String email = request.getParameter("email");
            Coffeeshop existingCoffeeshop = DAOCoffeeshop.getCoffeeShopById(coffeeshopId);
            if (existingCoffeeshop != null) {
                existingCoffeeshop.setName(name);
                existingCoffeeshop.setAddress(address);
                existingCoffeeshop.setRate(rate);
                existingCoffeeshop.setWorking_hours(workingHours);
                existingCoffeeshop.setEmail(email);
                HibernateUtil.updateObject(existingCoffeeshop);
            }
            response.sendRedirect(request.getContextPath() + "/coffeeshop");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String coffeeshopIdParam = request.getParameter("coffeeshopId");
        long coffeeshopId = Long.parseLong(coffeeshopIdParam);
        Coffeeshop selectedCoffeeshop = DAOCoffeeshop.getCoffeeShopById(coffeeshopId);
        request.setAttribute("selectedCoffeeshop", selectedCoffeeshop);
        request.getRequestDispatcher("/coffeeshop/editCoffeeshop.jsp").forward(request, response);
    }
}