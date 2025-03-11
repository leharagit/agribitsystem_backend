package com.yourcompany.ecommerce.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yourcompany.ecommerce.model.Product;
import com.yourcompany.ecommerce.repository.ProductRepository;
import com.yourcompany.ecommerce.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Optional<Product> getProductById(String productId) {
        return productRepository.findById(productId);
    }

    @Override
    public List<Product> getAllProducts(double minBidPrice, double maxBidPrice, String category, String sort, int page, int limit) {
        List<Product> products = productRepository.findByStartBidPriceBetween(minBidPrice, maxBidPrice);

        if (!category.isEmpty()) {
            products.removeIf(product -> !product.getCategory().equalsIgnoreCase(category));
        }

        if ("createdAt".equals(sort)) {
            products.sort((p1, p2) -> p2.getProductId().compareTo(p1.getProductId()));
        } else if ("sales".equals(sort)) {
            products.sort((p1, p2) -> Integer.compare(p2.getProductQuantity(), p1.getProductQuantity()));
        }

        int fromIndex = (page - 1) * limit;
        int toIndex = Math.min(fromIndex + limit, products.size());
        return products.subList(fromIndex, toIndex);
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    @Override
    public Product updateProduct(String productId, String productJson, MultipartFile image) throws IOException {
        Optional<Product> existingProductOpt = productRepository.findById(productId);

        if (existingProductOpt.isEmpty()) {
            throw new RuntimeException("Product not found with id: " + productId);
        }

        Product existingProduct = existingProductOpt.get();
        Product updatedProductData = new ObjectMapper().readValue(productJson, Product.class);

        if (updatedProductData.getName() != null) {
            existingProduct.setName(updatedProductData.getName());
        }
        if (updatedProductData.getCategory() != null) {
            existingProduct.setCategory(updatedProductData.getCategory());
        }
        if (updatedProductData.getBuyNowPrice() > 0) {
            existingProduct.setBuyNowPrice(updatedProductData.getBuyNowPrice());
        }
        if (updatedProductData.getDescription() != null) {
            existingProduct.setDescription(updatedProductData.getDescription());
        }
        if (image != null && !image.isEmpty()) {
            existingProduct.setImage(image.getBytes());
        }

        return productRepository.save(existingProduct);
    }

    @Override
    public void deleteProduct(String productId) {
        productRepository.deleteById(productId);
    }

    @Override
    public void exportProductsToCSV(PrintWriter writer) {
        List<Product> products = productRepository.findAll();
        for (Product product : products) {
            writer.write(product.getProductId() + "," + product.getName() + "," + product.getCategory() + "," +
                    product.getStartBidPrice() + "," + product.getBuyNowPrice() + "," + product.getStatus() + "\n");
        }
    }
}
