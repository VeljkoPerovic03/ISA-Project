package com.example.clothingstoreapp.Repositories;

import com.example.clothingstoreapp.Models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer> {
    UserModel findByEmail(String email);
    boolean existsByEmail(String email); 
}
