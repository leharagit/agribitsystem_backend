package com.yourcompany.ecommerce.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Document(collection = "users")
@Data // Lombok: Generates Getters, Setters, toString(), equals(), and hashCode()
@NoArgsConstructor // Lombok: No-args constructor
@AllArgsConstructor // Lombok: All-args constructor
public class User {

    @Id
    private String userId;

    @NotBlank(message = "Full Name is required")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String userEmail;

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String password;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^\\d{10,}$", message = "Phone number must be at least 10 digits")
    private String phoneNumber;

    @NotBlank(message = "Location is required")
    private String location;

    @NotBlank(message = "User role is required")
    @Pattern(regexp = "Farmer|Buyer|Admin", message = "Role must be one of: Farmer, Buyer, Admin")
    private String userRole;

    private LocalDate dateRegistered = LocalDate.now();
    private LocalTime timeRegistered = LocalTime.now();

    // Ensure password is NOT exposed in logs add
    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", location='" + location + '\'' +
                ", userRole='" + userRole + '\'' +
                ", dateRegistered=" + dateRegistered +
                ", timeRegistered=" + timeRegistered +
                '}';
    }
}
