package com.example.webappmpl_2.servlet;

import com.example.webappmpl_2.entity.Coffeeshop;
import com.example.webappmpl_2.entity.CoffeeshopMenu;
import com.example.webappmpl_2.hibernate.DAOCoffeeshop;
import com.example.webappmpl_2.hibernate.DAOCoffeeshopMenu;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/Menu")
public class MenuServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String coffeeshopIdParam = req.getParameter("coffeeshopId");
        if (coffeeshopIdParam != null) {
            long coffeeshopId = Long.parseLong(coffeeshopIdParam);
            Coffeeshop selectedCoffeeshop = DAOCoffeeshop.getCoffeeShopById(coffeeshopId);
            if (selectedCoffeeshop != null) {
                List<CoffeeshopMenu> customer = DAOCoffeeshopMenu.getMenuByCoffeeshop(selectedCoffeeshop);
                req.setAttribute("selectedCoffeeshop", selectedCoffeeshop);
                req.setAttribute("customer", customer);
                req.getRequestDispatcher("/showMenu.jsp").forward(req,resp);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
