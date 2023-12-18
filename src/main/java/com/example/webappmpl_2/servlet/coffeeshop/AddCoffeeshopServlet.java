package com.example.webappmpl_2.servlet.coffeeshop;

import com.example.webappmpl_2.entity.Coffeeshop;
import com.example.webappmpl_2.hibernate.HibernateUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



import java.io.IOException;

@WebServlet("/AddCoffeeshopServlet")
public class AddCoffeeshopServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/coffeeshop/addCoffeeshop.jsp").forward(request,response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        int rate = Integer.parseInt(request.getParameter("rate"));
        int workingHours = Integer.parseInt(request.getParameter("working_hours"));
        String email = request.getParameter("email");
        Coffeeshop coffeeshop = new Coffeeshop(name, address, rate, workingHours, email);
        HibernateUtil.addObjectToDB(coffeeshop);
        response.sendRedirect(request.getContextPath() + "/coffeeshop");
    }
}
