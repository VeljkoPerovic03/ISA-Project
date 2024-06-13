package com.example.clothingstoreapp.Controllers;

import com.example.clothingstoreapp.Models.OrderModel;
import com.example.clothingstoreapp.Services.OrderService;
import com.example.clothingstoreapp.Dto.CreateOrderRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public List<OrderModel> getAllOrders() {
        return orderService.findAllOrders();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderModel> getOrderById(@PathVariable Integer id) {
        return orderService.findOrderById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<OrderModel> createOrder(@RequestBody CreateOrderRequest request) {
        OrderModel order = orderService.createOrder(request);
        return ResponseEntity.ok(order);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderModel> updateOrder(@PathVariable Integer id, @RequestBody CreateOrderRequest request) {
        OrderModel updatedOrder = orderService.updateOrder(id, request);
        return ResponseEntity.ok(updatedOrder);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Integer id) {
        return orderService.findOrderById(id)
                .map(order -> {
                    orderService.deleteOrder(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}

