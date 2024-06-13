package com.example.clothingstoreapp.Services;

import com.example.clothingstoreapp.Dto.CreateOrderRequest;
import com.example.clothingstoreapp.Models.OrderModel;
import com.example.clothingstoreapp.Models.ProductModel;
import com.example.clothingstoreapp.Models.UserModel;
import com.example.clothingstoreapp.Repositories.OrderRepository;
import com.example.clothingstoreapp.Repositories.ProductRepository;
import com.example.clothingstoreapp.Repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    public OrderModel createOrder(CreateOrderRequest request) {
        UserModel user = userRepository.findById(request.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
        ProductModel product = productRepository.findById(request.getProductId()).orElseThrow(() -> new RuntimeException("Product not found"));
    
        OrderModel order = new OrderModel();
        order.setUser(user);
        order.setProduct(product);
        order.setOrderDate(request.getOrderDate());
        order.setStatus(request.getStatus());
    
        return orderRepository.save(order);
    }

    public List<OrderModel> findAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<OrderModel> findOrderById(Integer id) {
        return orderRepository.findById(id);
    }

    public OrderModel updateOrder(Integer id, CreateOrderRequest request) {
        OrderModel order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
        UserModel user = userRepository.findById(request.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
        ProductModel product = productRepository.findById(request.getProductId()).orElseThrow(() -> new RuntimeException("Product not found"));

        order.setUser(user);
        order.setProduct(product);
        order.setOrderDate(request.getOrderDate());
        order.setStatus(request.getStatus());

        return orderRepository.save(order);
    }

    public void deleteOrder(Integer id) {
        orderRepository.deleteById(id);
    }
}

