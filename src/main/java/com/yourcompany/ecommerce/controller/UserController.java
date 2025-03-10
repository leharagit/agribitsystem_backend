package com.yourcompany.ecommerce.controller;

import com.yourcompany.ecommerce.model.User;
import com.yourcompany.ecommerce.service.UserService;
import com.yourcompany.ecommerce.Util.JwtUtil;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = {"http://localhost:5173", "https://officialagribit.netlify.app", "http://localhost:5175"})
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    // Create a new user with validation
    @PostMapping("/addUser")
    public ResponseEntity<?> createUser(@Valid @RequestBody User user) {
        try {
            User createdUser = userService.createUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error creating user: " + e.getMessage());
        }
    }

    // Get user by ID
    @GetMapping("/getUserById/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") String userId) {
        try {
            User user = userService.getUserById(userId);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    // Get user name by ID
    @GetMapping("/getUserNameByID/{id}")
    public ResponseEntity<?> getUsernameById(@PathVariable("id") String userId) {
        try {
            User user = userService.getUserNameById(userId);
            return ResponseEntity.ok(user.getFirstName());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    // Get user by email
    @GetMapping("/getUserByEmail/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable("email") String userEmail) {
        try {
            User user = userService.getUserByUserEmail(userEmail);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    // Get all users
    @GetMapping("/allUsers")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    // Delete user by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") String userId) {
        try {
            userService.deleteUser(userId);
            return ResponseEntity.ok("User with ID " + userId + " has been deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    // User login
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody User loginRequest) {
        String loginMessage = userService.login(loginRequest);
        if ("Login successful".equals(loginMessage)) {
            User authenticatedUser = userService.getUserByUserEmail(loginRequest.getUserEmail());
            String token = JwtUtil.generateToken(authenticatedUser.getUserEmail());

            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            response.put("email", authenticatedUser.getUserEmail());
            response.put("role", authenticatedUser.getUserRole());
            response.put("userId", authenticatedUser.getUserId());
            response.put("name", authenticatedUser.getName());

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials.");
        }
    }

    // Logout user
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        return ResponseEntity.ok(userService.logout(session));
    }

    // Register user
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody User user) {
        try {
            return ResponseEntity.ok(userService.register(user));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Registration failed: " + e.getMessage());
        }
    }

    // Send recovery code
    @PostMapping("/send-code")
    public ResponseEntity<?> sendRecoveryCode(@RequestBody Map<String, String> payload) {
        String userEmail = payload.get("userEmail");
        if (userEmail == null || userEmail.isBlank()) {
            return ResponseEntity.badRequest().body("Email cannot be empty.");
        }
        return ResponseEntity.ok(userService.sendRecoveryCode(userEmail));
    }

    // Verify recovery code
    @PostMapping("/verify-code")
    public ResponseEntity<Boolean> verifyRecoveryCode(@RequestParam String userEmail, @RequestParam String recoveryCode) {
        return ResponseEntity.ok(userService.verifyRecoveryCode(userEmail, recoveryCode));
    }

    // Update password
    @PostMapping("/update-password")
    public ResponseEntity<?> updatePassword(@RequestParam String userEmail, @RequestParam String newPassword) {
        try {
            return ResponseEntity.ok(userService.updatePassword(userEmail, newPassword));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error updating password: " + e.getMessage());
        }
    }

    // Update user details
    @PutMapping("/updateUser/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable("userId") String userId, @Valid @RequestBody User user) {
        try {
            User updatedUser = userService.updateUser(userId, user);
            return ResponseEntity.ok(updatedUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }
}
