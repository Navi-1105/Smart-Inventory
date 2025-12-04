package com.inventory.smart_inventory.controller;

import com.inventory.smart_inventory.model.Product;
import com.inventory.smart_inventory.dto.OrderRequest;
import com.inventory.smart_inventory.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // GET /api/products
    @GetMapping("/products")
    public ResponseEntity<List<Product>> allProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    // POST /api/products  (create product)
    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product saved = productService.addOrUpdateProduct(product);
        return ResponseEntity.ok(saved);
    }

    // POST /api/order  (process order)
    @PostMapping("/order")
    public ResponseEntity<String> order(@RequestBody OrderRequest request) {
        if (request.getProductId() == null || request.getQuantity() == null) {
            return ResponseEntity.badRequest().body("productId and quantity are required");
        }
        String result = productService.processOrder(request.getProductId(), request.getQuantity());
        if (result.startsWith("Order processed")) {
            return ResponseEntity.ok(result);
        } else if (result.startsWith("ALERT") || result.startsWith("Order processed. ALERT")) {
            // still OK but with alert
            return ResponseEntity.ok(result);
        } else {
            // some error like insufficient stock or product not found
            return ResponseEntity.badRequest().body(result);
        }
    }
}
