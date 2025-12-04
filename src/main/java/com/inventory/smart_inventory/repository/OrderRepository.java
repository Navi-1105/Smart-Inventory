package com.inventory.smart_inventory.repository;

import com.inventory.smart_inventory.model.OrderRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderRecord, Integer> {
}
