package com.example.clothingstoreapp.Repositories;

import com.example.clothingstoreapp.Models.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleModel, Integer> {
    RoleModel findByRoleName(String roleName);
}
