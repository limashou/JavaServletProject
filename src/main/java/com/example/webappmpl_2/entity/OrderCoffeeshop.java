package com.example.webappmpl_2.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class OrderCoffeeshop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 6)
    private String order_date;

    @Transient
    private double total_amount;

    @Column(nullable = false)
    @ColumnDefault(value = "1")
    private boolean status;

    @Column(nullable = false)
    @ColumnDefault(value = "1")
    private boolean payment_method;

    @ManyToOne
    @JoinColumn(name = "coffeeshop_customer_id")
    private CoffeeshopCustomer coffeeshopCustomer;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "items",
            joinColumns = @JoinColumn(name = "order_id"),  // Имя столбца в таблице orders_coffeshop_menu
            inverseJoinColumns = @JoinColumn(name = "menu_id")  // Имя столбца в таблице orders_coffeshop_menu
    )
    private List<CoffeeshopMenu> items;

    public OrderCoffeeshop(CoffeeshopCustomer coffeeshopCustomer,String order_date,List<CoffeeshopMenu> items ,double total_amount, boolean status, boolean payment_method) {
        this.coffeeshopCustomer = coffeeshopCustomer;
        this.order_date = order_date;
        this.items = items;
        this.status = status;
        this.payment_method = payment_method;
        getTotal_amount();
    }

    public LocalTime getOrder_date() {
        return LocalTime.parse(order_date, DateTimeFormatter.ofPattern("HH:mm"));
    }

    public String toStringSelectedItems() {
        StringBuilder itemsString = new StringBuilder();
        for (CoffeeshopMenu item : items) {
            if (itemsString.length() > 0) {
                itemsString.append(", ");
            }
            itemsString.append(item.getItems());
        }
        return itemsString.toString();
    }

    public double getTotal_amount() {
        double total = 0.0;
        for (CoffeeshopMenu item : items) {
            total += item.getCost();
        }
        this.total_amount = total;
        return total_amount;
    }

}
