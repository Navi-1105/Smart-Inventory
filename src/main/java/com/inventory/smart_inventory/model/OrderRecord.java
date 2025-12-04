package com.inventory.smart_inventory.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class OrderRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "quantity_ordered")
    private Integer quantityOrdered;

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    public OrderRecord() {}

    public OrderRecord(Integer productId, Integer quantityOrdered) {
        this.productId = productId;
        this.quantityOrdered = quantityOrdered;
        this.orderDate = LocalDateTime.now();
    }

    // Getters & setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getProductId() { return productId; }
    public void setProductId(Integer productId) { this.productId = productId; }

    public Integer getQuantityOrdered() { return quantityOrdered; }
    public void setQuantityOrdered(Integer quantityOrdered) { this.quantityOrdered = quantityOrdered; }

    public LocalDateTime getOrderDate() { return orderDate; }
    public void setOrderDate(LocalDateTime orderDate) { this.orderDate = orderDate; }
}
