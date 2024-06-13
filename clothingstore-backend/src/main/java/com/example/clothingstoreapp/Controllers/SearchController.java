package com.example.clothingstoreapp.Controllers;

import com.example.clothingstoreapp.Models.ProductModel;
import com.example.clothingstoreapp.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private ProductService productService;

    @GetMapping("/name")
    public List<ProductModel> searchByName(@RequestParam String name) {
        return productService.findProductsByName(name);
    }

    @GetMapping("/type")
    public List<ProductModel> searchByType(@RequestParam String type) {
        return productService.findProductsByType(type);
    }

    @GetMapping("/size")
    public List<ProductModel> searchBySize(@RequestParam String size) {
        return productService.findProductsBySize(size);
    }

    @GetMapping("/manufacturer")
    public List<ProductModel> searchByManufacturer(@RequestParam String manufacturer) {
        return productService.findProductsByManufacturer(manufacturer);
    }

    @GetMapping("/productionDate")
    public List<ProductModel> searchByProductionDate(@RequestParam String date) {
        return productService.findProductsByProductionDate(Date.valueOf(date));
    }

    @GetMapping("/priceRange")
    public List<ProductModel> searchByPriceRange(@RequestParam Double minPrice, @RequestParam Double maxPrice) {
        return productService.findProductsByPriceRange(minPrice, maxPrice);
    }

    @GetMapping("/status")
    public List<ProductModel> searchByStatus(@RequestParam String status) {
        return productService.findProductsByStatus(status);
    }
}

