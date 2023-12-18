package com.example.webappmpl_2.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "coffeeshop_customer")
public class CoffeeshopCustomer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "coffeeshop_id")
    private Coffeeshop coffeeshop;
    @Column(length = 80,nullable = false)
    private String cust_name;
    @Column(length = 150)
    private String cust_email;
    @Column(length = 15)
    private String cust_phone;

    public CoffeeshopCustomer(Coffeeshop coffeeshop, String cust_name, String cust_email, String cust_phone) {
        this.coffeeshop = coffeeshop;
        this.cust_name = cust_name;
        this.cust_email = cust_email;
        this.cust_phone = cust_phone;
    }
}
