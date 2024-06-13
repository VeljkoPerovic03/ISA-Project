package com.example.clothingstoreapp.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.clothingstoreapp.Models.UserModel;
import com.example.clothingstoreapp.Repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<UserModel> findAllUsers() {
        return userRepository.findAll();
    }

    public Optional<UserModel> findUserById(Integer user_id) {
        return userRepository.findById(user_id);
    }

    public UserModel saveUser(UserModel user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public void deleteUser(Integer user_id) {
        userRepository.deleteById(user_id);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public Optional<UserModel> findByEmail(String email) {
        return Optional.ofNullable(userRepository.findByEmail(email));
    }
}
