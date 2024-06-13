package com.example.clothingstoreapp.Controllers;

import com.example.clothingstoreapp.Models.ProductModel;
import com.example.clothingstoreapp.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<ProductModel> getAllProducts() {
        return productService.findAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductModel> getProductById(@PathVariable Integer id) {
        return productService.findProductById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ProductModel createProduct(@RequestBody ProductModel product) {
        return productService.saveProduct(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductModel> updateProduct(@PathVariable Integer id, @RequestBody ProductModel updatedProduct) {
        return productService.findProductById(id)
                .map(product -> {
                    product.setName(updatedProduct.getName());
                    product.setType(updatedProduct.getType());
                    product.setSize(updatedProduct.getSize());
                    product.setManufacturer(updatedProduct.getManufacturer());
                    product.setProductionDate(updatedProduct.getProductionDate());
                    product.setPrice(updatedProduct.getPrice());
                    product.setStatus(updatedProduct.getStatus());
                    return ResponseEntity.ok(productService.saveProduct(product));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id) {
        return productService.findProductById(id)
                .map(product -> {
                    productService.deleteProduct(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
