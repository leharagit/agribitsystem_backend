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

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // ✅ Fetch all products with optional filtering
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(
            @RequestParam(required = false, defaultValue = "0") double startBidPrice,
            @RequestParam(required = false, defaultValue = "100000") double maxBidPrice,
            @RequestParam(required = false, defaultValue = "sales") String sort
    ) {
        return ResponseEntity.ok(productService.getAllProducts(startBidPrice, maxBidPrice, sort));
    }

    // ✅ Fetch a product by ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable String id) {
        Optional<Product> product = productService.getProductById(id);
        return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // ✅ Create a new product
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

    // ✅ Update an existing product by ID
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProductById(
            @PathVariable String id,
            @RequestParam(value = "product", required = false) String productJson,
            @RequestParam(value = "image", required = false) MultipartFile image
    ) throws IOException {
        Optional<Product> existingProductOpt = productService.getProductById(id);

        if (existingProductOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Product existingProduct = existingProductOpt.get();

        if (productJson != null) {
            Product updatedProductData = new ObjectMapper().readValue(productJson, Product.class);

            // ✅ Update only non-null or non-default fields
            if (updatedProductData.getName() != null) {
                existingProduct.setName(updatedProductData.getName());
            }
            if (updatedProductData.getBuyNowPrice() > 0) { // Assuming price cannot be zero or negative
                existingProduct.setBuyNowPrice(updatedProductData.getBuyNowPrice());
            }
            if (updatedProductData.getCategory() != null) {
                existingProduct.setCategory(updatedProductData.getCategory());
            }
            if (updatedProductData.getDescription() != null) {
                existingProduct.setDescription(updatedProductData.getDescription());
            }
            // Add other fields as needed...
        }

        // ✅ Update image only if a new image is uploaded
        if (image != null && !image.isEmpty()) {
            existingProduct.setImage(image.getBytes());
        }

        Product updatedProduct = productService.updateProduct(id, existingProduct);
        return ResponseEntity.ok(updatedProduct);
    }

    // ✅ Delete a product by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    // ✅ Fetch all products added by a specific user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Product>> getProductsByUserId(@PathVariable String userId) {
        List<Product> products = productService.getProductsByUserId(userId);
        return ResponseEntity.ok(products);
    }
}

