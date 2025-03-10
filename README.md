# Online Agricultural Product Auction  Platform
# E-commerce Backend API

This repository contains the backend implementation of an e-commerce platform using Spring Boot. The API provides endpoints for managing bids, deliveries, products, and users, with a focus on secure, efficient, and scalable operations.

##   Features

- **Bid Management:   **
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

   ## Set Up Database

1. **Create a Database:**  
   Create a database for the application (e.g., `ecommerce_db`).

2. **Update Configuration:**  
   Update the database configuration in the `application.properties` file.

## Build the Project

Run the following command to build the project:
```bash
mvn clean install

# API Documentation

## Access the API
The application will run on `http://localhost:8080` by default.

## API Endpoints

### Bid Endpoints
- **Create a new bid:** `POST /api/bids`
- **Retrieve a bid by ID:** `GET /api/bids/{id}`
- **Retrieve all bids:** `GET /api/bids`
- **Update a bid by ID:** `PUT /api/bids/{id}`
- **Delete a bid by ID:** `DELETE /api/bids/{id}`
- **Retrieve bids for a specific product:** `GET /api/bids/product/{productId}`
- **Retrieve the bid with the highest total amount:** `GET /api/bids/max-total`
- **Retrieve the highest bid for a specific product:** `GET /api/bids/max-total/{productId}`

### Delivery Endpoints
- **Create a new delivery:** `POST /api/deliveries`
- **Retrieve a delivery by ID:** `GET /api/deliveries/{id}`
- **Retrieve all deliveries:** `GET /api/deliveries`
- **Update a delivery by ID:** `PUT /api/deliveries/{id}`
- **Delete a delivery by ID:** `DELETE /api/deliveries/{id}`

### Product Endpoints
- **Create a new product:** `POST /api/products` (with optional image upload)
- **Retrieve a product by ID:** `GET /api/products/{id}`
- **Retrieve all products:** `GET /api/products` (filter by price range and sort)
- **Update a product by ID:** `PUT /api/products/{id}` (with optional image upload)
- **Delete a product by ID:** `DELETE /api/products/{id}`

### User Endpoints
- **Register a new user:** `POST /user/addUser`
- **Authenticate and login a user:** `POST /user/login`
- **Logout the current user:** `POST /user/logout`
- **Retrieve user details by ID:** `GET /user/getUserById/{id}`
- **Retrieve user details by email:** `GET /user/getUserByEmail/{id}`
- **Retrieve all users:** `GET /user/allUsers`
- **Update user details:** `PUT /user/updateUser/{id}`
- **Delete a user by ID:** `DELETE /user/delete/{id}`
- **Send a recovery code for password reset:** `POST /user/send-code`
- **Verify a recovery code:** `POST /user/verify-code`
- **Update a user's password:** `POST /user/update-password`

## Configuration
Update the following properties in the `application.properties` file:

```properties
# Server configuration
server.port=8080

# Database configuration
spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce_db
spring.datasource.username=your-username
spring.datasource.password=your-password
spring.jpa.hibernate.ddl-auto=update

# JWT configuration
jwt.secret=your-secret-key
jwt.expiration=86400000
```

## Testing
Use tools like Postman or curl to test the endpoints. Example:

```bash
curl -X POST -H "Content-Type: application/json" -d '{"userEmail":"test@example.com", "password":"password123"}' http://localhost:8080/user/login
```

## Contribution Guidelines
1. Fork the repository.
2. Create a feature branch.
3. Commit your changes with clear messages.
4. Submit a pull request.

## License
This project is licensed under the MIT License.

## Contact
For questions or support, please contact leharaoshan@icloud.com.

