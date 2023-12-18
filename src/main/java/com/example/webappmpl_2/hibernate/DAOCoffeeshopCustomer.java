package com.example.webappmpl_2.hibernate;

import com.example.webappmpl_2.entity.Coffeeshop;
import com.example.webappmpl_2.entity.CoffeeshopCustomer;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class DAOCoffeeshopCustomer {

    public List<CoffeeshopCustomer> getAllList() {
        List<CoffeeshopCustomer> list = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            list = session.createQuery("from CoffeeshopCustomer", CoffeeshopCustomer.class).getResultList();
        }
        return list;
    }

    public static List<CoffeeshopCustomer> getCustomerByCoffeeshop(Coffeeshop coffeeshop) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM CoffeeshopCustomer WHERE coffeeshop = :coffeeshop";
            Query<CoffeeshopCustomer> query = session.createQuery(hql, CoffeeshopCustomer.class);
            query.setParameter("coffeeshop", coffeeshop);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void deleteCoffeeshopCustomerById(long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                CoffeeshopCustomer orderCoffeeshop = session.get(CoffeeshopCustomer.class, id);
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

    public static CoffeeshopCustomer getCoffeesCustomerhopById(Long id) {
        CoffeeshopCustomer orderCoffeeshop = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            try {
                orderCoffeeshop = session.get(CoffeeshopCustomer.class, id);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return orderCoffeeshop;
    }
    public static Long getCoffeeshopIdByCustomerId(Long customerId) {
        Long coffeeshopId = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            try {
                CoffeeshopCustomer customer = session.get(CoffeeshopCustomer.class, customerId);
                if (customer != null) {
                    coffeeshopId = customer.getCoffeeshop().getId();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return coffeeshopId;
    }
}
