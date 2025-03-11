package com.yourcompany.ecommerce.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yourcompany.ecommerce.model.Product;
import com.yourcompany.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.PrintWriter;
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

    // ✅ Fetch all products with optional filters
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(
            @RequestParam(required = false, defaultValue = "0") double startBidPrice,
            @RequestParam(required = false, defaultValue = "100000") double maxBidPrice,
            @RequestParam(required = false, defaultValue = "") String category,
            @RequestParam(required = false, defaultValue = "sales") String sort,
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "10") int limit
    ) {
        return ResponseEntity.ok(productService.getAllProducts(startBidPrice, maxBidPrice, category, sort, page, limit));
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
        Product updatedProduct = productService.updateProduct(id, productJson, image);
        return ResponseEntity.ok(updatedProduct);
    }

    // ✅ Delete a product by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

 
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Product>> getProductsByUserId(@PathVariable String userId) {
        List<Product> products = productService.getProductsByUserId(userId);
        if (products.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(products);
    }







    // ✅ Fetch products by category
    @GetMapping("/category/{category}")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable String category) {
        List<Product> products = productService.getProductsByCategory(category);
        return ResponseEntity.ok(products);
    }

    // ✅ Export products to CSV
    @GetMapping("/export/csv")
    public void exportProductsToCSV(PrintWriter writer) {
        writer.write("Product ID,Name,Category,Start Bid Price,Buy Now Price,Status\n");
        productService.exportProductsToCSV(writer);
    }
}



