package com.sheikhiahmed.productmanagerapi.service;

import com.sheikhiahmed.productmanagerapi.exception.ProductNotFoundException;
import com.sheikhiahmed.productmanagerapi.model.Product;
import com.sheikhiahmed.productmanagerapi.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public Product getProductById(Long id) throws ProductNotFoundException{
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("product with id "+ id +" not found"));
    }
}
