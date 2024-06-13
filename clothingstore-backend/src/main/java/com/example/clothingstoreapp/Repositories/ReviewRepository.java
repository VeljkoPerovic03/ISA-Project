package com.example.clothingstoreapp.Repositories;

import com.example.clothingstoreapp.Models.ReviewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewModel, Integer> {
}

