package com.sheikhiahmed.productmanagerapi.repository;

import com.sheikhiahmed.productmanagerapi.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
