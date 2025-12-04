package com.inventory.smart_inventory.service;

import com.inventory.smart_inventory.model.Product;
import com.inventory.smart_inventory.model.OrderRecord;
import com.inventory.smart_inventory.repository.ProductRepository;
import com.inventory.smart_inventory.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public ProductService(ProductRepository productRepository, OrderRepository orderRepository) {
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product addOrUpdateProduct(Product p) {
        return productRepository.save(p);
    }

    @Transactional
    public String processOrder(Integer productId, Integer quantity) {
        if (quantity == null || quantity <= 0) {
            return "Invalid quantity";
        }

        Optional<Product> opt = productRepository.findById(productId);
        if (opt.isEmpty()) {
            return "Product not found";
        }

        Product product = opt.get();
        Integer currentStock = product.getStockQuantity() == null ? 0 : product.getStockQuantity();

        if (currentStock < quantity) {
            return "Insufficient stock. Available: " + currentStock;
        }

        // subtract stock and save product
        product.setStockQuantity(currentStock - quantity);
        productRepository.save(product);

        // record the order
        OrderRecord order = new OrderRecord(productId, quantity);
        orderRepository.save(order);

        // check low stock
        Integer minLevel = product.getMinStockLevel() == null ? 0 : product.getMinStockLevel();
        if (product.getStockQuantity() < minLevel) {
            // "Smart" alert - currently prints to console. Later you can send email or webhook.
            String alert = "ALERT: LOW STOCK FOR ITEM [" + productId + "] current=" + product.getStockQuantity() + " min=" + minLevel;
            System.out.println(alert);
            return "Order processed. " + alert;
        }

        return "Order processed successfully";
    }
}
