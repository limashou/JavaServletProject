package com.example.webappmpl_2.hibernate;


import com.example.webappmpl_2.entity.CoffeeshopCustomer;
import com.example.webappmpl_2.entity.OrderCoffeeshop;
import jakarta.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


import java.util.List;

public class DAOOrderCoffeeshop {

    public List<OrderCoffeeshop> getAllList() {
        List<OrderCoffeeshop> list = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            list = session.createQuery("from OrderCoffeeshop", OrderCoffeeshop.class).getResultList();
        }
        return list;
    }

    public static List<OrderCoffeeshop> getOrdersByCustomer(CoffeeshopCustomer customer) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM OrderCoffeeshop WHERE coffeeshopCustomer = :coffeeshopCustomer";
            Query<OrderCoffeeshop> query = session.createQuery(hql, OrderCoffeeshop.class);
            query.setParameter("coffeeshopCustomer", customer);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<OrderCoffeeshop> getOrdersByCustomerWithItems(CoffeeshopCustomer customer) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM OrderCoffeeshop oc JOIN FETCH oc.items WHERE oc.coffeeshopCustomer = :coffeeshopCustomer";
            Query<OrderCoffeeshop> query = session.createQuery(hql, OrderCoffeeshop.class);
            query.setParameter("coffeeshopCustomer", customer);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static void deleteOrderCoffeeshopById(long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                OrderCoffeeshop orderCoffeeshop = session.get(OrderCoffeeshop.class, id);
                if (orderCoffeeshop != null) {
                    session.remove(orderCoffeeshop);
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

    public static OrderCoffeeshop getOrderCoffeeshopById(Long id) {
        OrderCoffeeshop orderCoffeeshop = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            try {
                orderCoffeeshop = session.get(OrderCoffeeshop.class, id);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return orderCoffeeshop;
    }
}
