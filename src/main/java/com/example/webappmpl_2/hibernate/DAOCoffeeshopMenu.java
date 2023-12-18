package com.example.webappmpl_2.hibernate;


import com.example.webappmpl_2.entity.Coffeeshop;
import com.example.webappmpl_2.entity.CoffeeshopCustomer;
import com.example.webappmpl_2.entity.CoffeeshopMenu;
import com.example.webappmpl_2.entity.OrderCoffeeshop;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class DAOCoffeeshopMenu {
    public static List<CoffeeshopMenu> getAlllist(){
        List<CoffeeshopMenu> list = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            list = session.createQuery("from CoffeeshopMenu ",CoffeeshopMenu.class).getResultList();
        }
        return list;
    }

    public static CoffeeshopMenu getMenuItemById(Long menuItemId) {
        CoffeeshopMenu coffeeshopMenu = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            try {
                coffeeshopMenu = session.get(CoffeeshopMenu.class,menuItemId);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return coffeeshopMenu;
    }
    public static double calculateTotalAmount(List<CoffeeshopMenu> selectedMenuItems) {
        double totalAmount = 0;
        for (CoffeeshopMenu menuItem : selectedMenuItems) {
            totalAmount += menuItem.getCost();
        }
        return totalAmount;
    }
    public static List<CoffeeshopMenu> getMenuByCoffeeshop(Coffeeshop coffeeshop) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM CoffeeshopMenu WHERE coffeeshop = :coffeeshop_id";
            Query<CoffeeshopMenu> query = session.createQuery(hql, CoffeeshopMenu.class);
            query.setParameter("coffeeshop_id", coffeeshop);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
