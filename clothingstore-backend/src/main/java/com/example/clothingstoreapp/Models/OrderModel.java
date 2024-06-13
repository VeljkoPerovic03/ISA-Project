package com.example.clothingstoreapp.Models;

import jakarta.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "orders")
public class OrderModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer order_id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false) 
    private UserModel user;
    
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false) 
    private ProductModel product;

    @Column(nullable = false)
    private Date orderDate;

    @Column(nullable = false)
    private String status;

    public Integer getId() {
        return order_id;
    }

    public void setId(Integer order_id) {
        this.order_id = order_id;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public ProductModel getProduct() {
        return product;
    }

    public void setProduct(ProductModel product) {
        this.product = product;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
