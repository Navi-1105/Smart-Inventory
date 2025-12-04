package com.inventory.smart_inventory.repository;

import com.inventory.smart_inventory.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
