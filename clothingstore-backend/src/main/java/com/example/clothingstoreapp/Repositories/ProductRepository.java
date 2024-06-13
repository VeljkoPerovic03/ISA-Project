package com.example.clothingstoreapp.Repositories;

import com.example.clothingstoreapp.Models.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel, Integer> {
    List<ProductModel> findByName(String name);

    List<ProductModel> findByType(String type);

    List<ProductModel> findBySize(String size);

    List<ProductModel> findByManufacturer(String manufacturer);

    List<ProductModel> findByProductionDate(Date productionDate);

    List<ProductModel> findByPriceBetween(Double minPrice, Double maxPrice);

    List<ProductModel> findByStatus(String status);
}