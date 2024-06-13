package com.example.clothingstoreapp.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "reviews")
public class ReviewModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer review_id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private ProductModel product;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserModel user;

    @Column(nullable = false)
    private Integer rating;

    @Column(nullable = true, length = 200)
    private String comment;

    public Integer getId() {
        return review_id;
    }

    public void setId(Integer review_id) {
        this.review_id = review_id;
    }

    public ProductModel getProduct() {
        return product;
    }

    public void setProduct(ProductModel product) {
        this.product = product;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}