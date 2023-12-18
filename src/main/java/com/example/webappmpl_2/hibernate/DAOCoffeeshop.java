package com.example.webappmpl_2.hibernate;

import com.example.webappmpl_2.entity.Coffeeshop;
import jakarta.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Comparator;
import java.util.List;

public class DAOCoffeeshop {

    public static List<Coffeeshop> getAllList() {
        List<Coffeeshop> list = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            list = session.createQuery("from Coffeeshop", Coffeeshop.class).getResultList();
        }
        return list;
    }

    public static void deleteCoffeeShopById(long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                Coffeeshop coffeeshop = session.get(Coffeeshop.class, id);
                if (coffeeshop != null) {
                    session.remove(coffeeshop);
                }
                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                e.printStackTrace();
            }
        }
    }


    public static Coffeeshop getCoffeeShopById(Long id) {
        Coffeeshop coffeeshop = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            try {
                coffeeshop = session.get(Coffeeshop.class, id);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return coffeeshop;
    }

    public static List<Coffeeshop> sortByRate(){
        List<Coffeeshop> sortedCoffeeshops = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            sortedCoffeeshops = session.createQuery("FROM Coffeeshop c", Coffeeshop.class)
                    .stream()
                    .sorted(Comparator.comparing(Coffeeshop::getRate).reversed())
                    .toList();
        }
        return sortedCoffeeshops;
    }

    public static List<Coffeeshop> performSearch(String searchQuery) {
        List<Coffeeshop> searchResults = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            //hql request
            String queryString = "from Coffeeshop where name like :searchQuery";
            Query query = session.createQuery(queryString, Coffeeshop.class);
            query.setParameter("searchQuery", "%" + searchQuery + "%");
            searchResults = query.getResultList();
        }
        return searchResults;
    }

    public static String displaySearchResults(List<Coffeeshop> searchResults, HttpServletResponse response) {
        StringBuilder resultHTML = new StringBuilder();
        resultHTML.append("<html><body>");
        resultHTML.append("<h1>Search Results</h1>");
        resultHTML.append("<table border='1'>");
        resultHTML.append("<tr><th>Name</th><th>Address</th><th>Rate</th><th>Working Hours</th><th>Email</th></tr>");
        for (Coffeeshop coffeeshop : searchResults) {
            resultHTML.append(coffeeshop.toStringForHTMLtable());
        }
        resultHTML.append("</table>");
        resultHTML.append("<a href=\"coffeeshop\"> Go back</a>");
                resultHTML.append("</body></html>");
        return resultHTML.toString();
    }

}
