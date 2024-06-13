package com.example.clothingstoreapp.Services;

import com.example.clothingstoreapp.Models.ProductModel;
import com.example.clothingstoreapp.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<ProductModel> findAllProducts() {
        return productRepository.findAll();
    }

    public Optional<ProductModel> findProductById(Integer id) {
        return productRepository.findById(id);
    }

    public ProductModel saveProduct(ProductModel product) {
        return productRepository.save(product);
    }

    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);
    }

    public List<ProductModel> findProductsByType(String type) {
        return productRepository.findByType(type);
    }

    public List<ProductModel> findProductsByName(String name) {
        return productRepository.findByName(name);
    }

    public List<ProductModel> findProductsBySize(String size) {
        return productRepository.findBySize(size);
    }

    public List<ProductModel> findProductsByManufacturer(String manufacturer) {
        return productRepository.findByManufacturer(manufacturer);
    }

    public List<ProductModel> findProductsByProductionDate(Date date) {
        return productRepository.findByProductionDate(date);
    }

    public List<ProductModel> findProductsByPriceRange(Double minPrice, Double maxPrice) {
        return productRepository.findByPriceBetween(minPrice, maxPrice);
    }

    public List<ProductModel> findProductsByStatus(String status) {
        return productRepository.findByStatus(status);
    }
}
