package com.example.webappmpl_2.servlet.coffeeshop;

import com.example.webappmpl_2.entity.Coffeeshop;
import com.example.webappmpl_2.hibernate.DAOCoffeeshop;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



import java.io.IOException;
import java.util.List;

@WebServlet("/coffeeshop")
public class CoffeeshopAllServlet extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String sortOrder = request.getParameter("sort");
        List<Coffeeshop> coffeeshops = (new DAOCoffeeshop()).getAllList();
        if(sortOrder != null) {
            if (sortOrder.equals("by_rate")) {
                coffeeshops = DAOCoffeeshop.sortByRate();
            }
        }
        if(coffeeshops == null){
            request.getRequestDispatcher("/coffeeshop/coffeeshop.jsp").forward(request,response);
        }
        else {
            request.setAttribute("coffeeshops", coffeeshops);
            request.getRequestDispatcher("/coffeeshop/coffeeshop.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html;charset=UTF-8");
        String searchQuery = request.getParameter("searchQuery");
        if (searchQuery != null && !searchQuery.isEmpty()) {
            List<Coffeeshop> searchResults = new DAOCoffeeshop().performSearch(searchQuery);
            if (searchResults != null && !searchResults.isEmpty()) {
                String searchResultsHTML = new DAOCoffeeshop().displaySearchResults(searchResults, response);
                response.getWriter().println(searchResultsHTML);
            } else {
                response.getWriter().println("<html><body>");
                response.getWriter().println("<h1>No results found for '" + searchQuery + "'</h1>");
                response.getWriter().println("</body></html>");
            }
        } else {
            doGet(request, response);
        }

    }
}
