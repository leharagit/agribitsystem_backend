package com.yourcompany.ecommerce.service;

import com.yourcompany.ecommerce.model.Product;

import io.jsonwebtoken.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

public interface ProductService {
    
    Product createProduct(Product product);
    Optional<Product> getProductById(String productId);
    List<Product> getAllProducts(double minBidPrice, double maxBidPrice, String category, String sort, int page, int limit);
    List<Product> getProductsByCategory(String category);
    Product updateProduct(String productId, String productJson, MultipartFile image) throws IOException, java.io.IOException;
    void deleteProduct(String productId);
    void exportProductsToCSV(PrintWriter writer);
    List<Product> getProductsByUserId(String userId);
}