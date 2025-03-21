package com.yourcompany.ecommerce.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;
import java.time.LocalTime;

@Document(collection = "users")
public class User {
    @Id
    private String userId;
    private String name;
    private String userEmail;
    private String firstName;
    private String lastName;
    private String password;
    private String phoneNumber;
    private String location; 
    private String userRole;
    private LocalDate dateRegistered = LocalDate.now();
    private LocalTime timeRegistered = LocalTime.now();

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public LocalDate getDateRegistered() {
        return dateRegistered;
    }

    public void setDateRegistered(LocalDate dateRegistered) {
        this.dateRegistered = dateRegistered;
    }

    public LocalTime getTimeRegistered() {
        return timeRegistered;
    }

    public void setTimeRegistered(LocalTime timeRegistered) {
        this.timeRegistered = timeRegistered;
    }
    // Getter and Setter for 'name'
public String getName() {
    return name;
}

public void setName(String name) {
    this.name = name;
}

// Getter and Setter for 'phoneNumber'
public String getPhoneNumber() {
    return phoneNumber;
}

public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
}

// Getter and Setter for 'location'
public String getLocation() {
    return location;
}

public void setLocation(String location) {
    this.location = location;
}

}
