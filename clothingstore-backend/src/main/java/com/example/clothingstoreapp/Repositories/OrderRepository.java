package com.example.clothingstoreapp.Repositories;

import com.example.clothingstoreapp.Models.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderModel, Integer> {
}
