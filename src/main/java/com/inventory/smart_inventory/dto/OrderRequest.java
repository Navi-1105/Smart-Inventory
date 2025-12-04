package com.inventory.smart_inventory.dto;

public class OrderRequest {
    private Integer productId;
    private Integer quantity;

    public OrderRequest() {}

    public Integer getProductId() { return productId; }
    public void setProductId(Integer productId) { this.productId = productId; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
}
