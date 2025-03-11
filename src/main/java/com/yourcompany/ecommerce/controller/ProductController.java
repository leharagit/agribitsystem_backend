package com.yourcompany.ecommerce.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yourcompany.ecommerce.model.Product;
import com.yourcompany.ecommerce.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = {"http://localhost:5173", "https://officialagribit.netlify.app"})
public class ProductController {

    @Autowired
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(
            @RequestParam(required = false, defaultValue = "0") double startBidPrice,
            @RequestParam(required = false, defaultValue = "100000") double maxBidPrice,
            @RequestParam(required = false, defaultValue = "sales") String sort
    ) {
        return ResponseEntity.ok(productService.getAllProducts(startBidPrice, maxBidPrice, sort));
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(
            @RequestParam("product") String productJson,
            @RequestParam(value = "image", required = false) MultipartFile image
    ) throws IOException {
        Product product = new ObjectMapper().readValue(productJson, Product.class);
        if (image != null && !image.isEmpty()) {
            product.setImage(image.getBytes());
        }
        Product createdProduct = productService.createProduct(product);
        return ResponseEntity.ok(createdProduct);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable String id) {
        Optional<Product> product = productService.getProductById(id);
        return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Product>> getProductsByUserId(@PathVariable String userId) {
        System.out.println("Fetching products for user ID: " + userId); // Debug log
        List<Product> products = productService.getProductsByUserId(userId);

        if (products.isEmpty()) {
            System.out.println("No products found for user ID: " + userId); // Debug log
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(products);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable String id,
            @RequestParam("product") String productJson,
            @RequestParam(value = "image", required = false) MultipartFile image
    ) throws IOException {
        Product product = new ObjectMapper().readValue(productJson, Product.class);
        if (image != null && !image.isEmpty()) {
            product.setImage(image.getBytes());
        }
        Product updatedProduct = productService.updateProduct(id, product);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
