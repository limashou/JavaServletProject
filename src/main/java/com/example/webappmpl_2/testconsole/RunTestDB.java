package com.example.webappmpl_2.testconsole;

import com.example.webappmpl_2.entity.Coffeeshop;
import com.example.webappmpl_2.entity.CoffeeshopMenu;
import com.example.webappmpl_2.entity.OrderCoffeeshop;
import com.example.webappmpl_2.hibernate.DAOCoffeeshop;
import com.example.webappmpl_2.hibernate.DAOCoffeeshopMenu;
import com.example.webappmpl_2.hibernate.DAOOrderCoffeeshop;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.example.webappmpl_2.hibernate.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class RunTestDB {
    public static void main(String[] args) {
        System.out.println(" DB with console!");
        OrderCoffeeshop order = DAOOrderCoffeeshop.getOrderCoffeeshopById(1L);
        List<CoffeeshopMenu> orderedItems = order.getItems();
        for (CoffeeshopMenu item : orderedItems) {
            System.out.println(item); // Вывод отдельного товара
        }

//        Coffeeshop coffeeshop = DAOCoffeeshop.getCoffeeShopById(1L);
//        List<CoffeeshopMenu> coffeeshopMenu = DAOCoffeeshopMenu.getMenuByCoffeeshop(coffeeshop);
//        for (CoffeeshopMenu empl : coffeeshopMenu) {
//            System.out.println(coffeeshopMenu);
//        }
       // HibernateUtil.shutdown();
    }
}
