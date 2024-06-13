package com.example.clothingstoreapp.Controllers;

import com.example.clothingstoreapp.Models.UserModel;
import com.example.clothingstoreapp.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.example.clothingstoreapp.Utils.JwtUtils;
import java.util.Collections; 
import java.util.Map;
import java.util.Optional;
import java.util.List;

@RestController
@RequestMapping("/public/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    @GetMapping
    public List<UserModel> getAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserModel> getUserById(@PathVariable Integer user_id) {
        return userService.findUserById(user_id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<UserModel> createUser(@RequestBody UserModel user) {
        UserModel newUser = userService.saveUser(user);
        return ResponseEntity.ok(newUser);
    }

    @PostMapping("/register")
    public ResponseEntity<UserModel> registerUser(@RequestBody UserModel user) {
        if (userService.existsByEmail(user.getEmail())) {
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(userService.saveUser(user));
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Map<String, String> credentials) {
        String email = credentials.get("email");
        String password = credentials.get("password");
        Optional<UserModel> userOpt = userService.findByEmail(email);

        if (!userOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
        }

        UserModel user = userOpt.get();
        if (!passwordEncoder.matches(password, user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email/password");
        }

        String token = jwtUtils.generateToken(user);
        return ResponseEntity.ok(Collections.singletonMap("token", token));
    }


    @PutMapping("/{id}")
    public ResponseEntity<UserModel> updateUser(@PathVariable Integer user_id, @RequestBody UserModel updatedUser) {
        return userService.findUserById(user_id)
                .map(user -> {
                    user.setFullName(updatedUser.getFullName());
                    user.setEmail(updatedUser.getEmail());
                    user.setPhone(updatedUser.getPhone());
                    user.setAddress(updatedUser.getAddress());
                    return ResponseEntity.ok(userService.saveUser(user));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer user_id) {
        return userService.findUserById(user_id)
                .map(user -> {
                    userService.deleteUser(user_id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
