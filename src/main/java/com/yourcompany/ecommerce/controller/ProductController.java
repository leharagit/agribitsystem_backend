package com.yourcompany.ecommerce.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yourcompany.ecommerce.model.Product;
import com.yourcompany.ecommerce.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:5173")
public class ProductController {

    @Autowired
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
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
        return product.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(
            @RequestParam(required = false, defaultValue = "0") double min,
            @RequestParam(required = false, defaultValue = "10000") double max,
            @RequestParam(required = false, defaultValue = "sales") String sort
    ) {
        return ResponseEntity.ok(productService.getAllProducts(min, max, sort));
    }
        // Convert image bytes to Base64 strings for retrieval


    
    

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
