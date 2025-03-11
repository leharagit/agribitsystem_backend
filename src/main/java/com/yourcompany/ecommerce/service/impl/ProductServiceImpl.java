package com.yourcompany.ecommerce.service.impl;

import com.yourcompany.ecommerce.model.Product;
import com.yourcompany.ecommerce.repository.ProductRepository;
import com.yourcompany.ecommerce.service.ProductService;
import org.springframework.stereotype.Service;

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
    public List<Product> getAllProducts(double startBidPrice, String sort) {
        // Fetch products based on minimum startBidPrice
        List<Product> products = productRepository.findByStartBidPriceGreaterThanEqual(startBidPrice);

        // Sorting Logic (Best Selling or Newest)
        if ("createdAt".equals(sort)) {
            products.sort((p1, p2) -> p2.getProductId().compareTo(p1.getProductId())); // Sort by newest
        } else if ("sales".equals(sort)) {
            products.sort((p1, p2) -> Integer.compare(p2.getProductQuantity(), p1.getProductQuantity())); // Sort by sales
        }

        return products;
    }

    @Override
    public List<Product> getProductsByUserId(String userId) {
        return productRepository.findByUserId(userId);
    }

    @Override
    public Product updateProduct(String productId, Product product) {
        return productRepository.findById(productId)
                .map(existingProduct -> {
                    existingProduct.setName(product.getName() != null ? product.getName() : existingProduct.getName());
                    existingProduct.setCategory(product.getCategory() != null ? product.getCategory() : existingProduct.getCategory());
                    existingProduct.setDescription(product.getDescription() != null ? product.getDescription() : existingProduct.getDescription());
                    existingProduct.setQuantity(product.getQuantity() != 0 ? product.getQuantity() : existingProduct.getQuantity());
                    existingProduct.setQuality(product.getQuality() != null ? product.getQuality() : existingProduct.getQuality());
                    existingProduct.setLocation(product.getLocation() != null ? product.getLocation() : existingProduct.getLocation());
                    existingProduct.setStartBidPrice(product.getStartBidPrice() != 0 ? product.getStartBidPrice() : existingProduct.getStartBidPrice());
                    existingProduct.setBuyNowPrice(product.getBuyNowPrice() != 0 ? product.getBuyNowPrice() : existingProduct.getBuyNowPrice());
                    existingProduct.setSize(product.getSize() != null ? product.getSize() : existingProduct.getSize());
                    existingProduct.setStatus(product.getStatus() != null ? product.getStatus() : existingProduct.getStatus());
                    existingProduct.setProductQuantity(product.getProductQuantity() != 0 ? product.getProductQuantity() : existingProduct.getProductQuantity());
                    existingProduct.setImage(product.getImage() != null ? product.getImage() : existingProduct.getImage());
                    return productRepository.save(existingProduct);
                })
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));
    }

    @Override
    public void deleteProduct(String productId) {
        if (!productRepository.existsById(productId)) {
            throw new RuntimeException("Product not found with id: " + productId);
        }
        productRepository.deleteById(productId);
    }
}

