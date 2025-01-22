# Online Agricultural Product Auction Platform
# E-commerce Backend API

This repository contains the backend implementation of an e-commerce platform using Spring Boot. The API provides endpoints for managing bids, deliveries, products, and users, with a focus on secure, efficient, and scalable operations.

## Features

- **Bid Management:**
  - Create, retrieve, update, and delete bids.
  - Retrieve bids by product ID.
  - Retrieve the bid with the maximum total amount.

- **Delivery Management:**
  - Create, retrieve, update, and delete deliveries.

- **Product Management:**
  - Create, retrieve, update, and delete products.
  - Upload product images.
  - Filter products by price range and sort by attributes (e.g., sales).

- **User Management:**
  - Register, login, and logout users.
  - Retrieve user information by ID or email.
  - Update user details and reset passwords.
  - Send and verify recovery codes for password recovery.
  - JWT authentication for secure login sessions.

## Technologies Used

- **Framework:** Spring Boot
- **Database:** (Specify your database here, e.g., MySQL, PostgreSQL, etc.)
- **Security:** JWT for authentication
- **Build Tool:** Maven
- **Other Libraries:**
  - Jackson for JSON processing
  - Spring Data JPA for database operations
  - Multipart support for file uploads

## Installation

1. **Clone the Repository:**
   ```bash
   git clone https://github.com/yourusername/ecommerce-backend.git
   cd ecommerce-backend
