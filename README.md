

````markdown
# 🛒 Smart Inventory System

[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.0-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![MySQL](https://img.shields.io/badge/Database-MySQL-blue.svg)](https://www.mysql.com/)
[![Build](https://img.shields.io/badge/Build-Maven-red.svg)](https://maven.apache.org/)

> A robust backend system designed to manage product inventory, process real-time orders, and trigger automated low-stock alerts using **Spring Boot** and **RESTful Architecture**.

---

## 🚀 Features

### ✅ **Product Management**
* **CRUD Operations:** Add, view, update, and delete products.
* **Smart Cataloging:** Stores category, pricing, stock quantity, and minimum stock thresholds.

### 📦 **Transactional Order Processing**
* **ACID Compliance:** Ensures stock is only reduced if the order is successfully recorded.
* **Validation Logic:** Automatically prevents orders if stock is insufficient.

### ⚠️ **Automated Stock Alerts**
* **Real-time Monitoring:** Checks inventory levels after every transaction.
* **Alert System:** Triggers a warning response when stock dips below the defined `minStockLevel`.

---

## 🧱 Tech Stack

| Component | Technology | Description |
| :--- | :--- | :--- |
| **Backend** | Java 21 + Spring Boot | Core application logic |
| **Database** | MySQL 8 | Relational data storage |
| **ORM** | Hibernate / JPA | Database communication & mapping |
| **API** | REST | Stateless communication endpoints |
| **Tools** | Maven, IntelliJ IDEA, Postman | Build and testing tools |

---

## 📐 System Logic (Order Flow)

This diagram represents how the system handles an incoming order request:

```mermaid
graph TD;
    A[User Places Order] --> B{Check Stock Availability};
    B -- Yes (Stock > Order) --> C[Reduce Stock Quantity];
    C --> D[Save Order to DB];
    D --> E{Check Min Stock Level};
    E -- Below Threshold --> F[Return Success + LOW STOCK WARNING];
    E -- Above Threshold --> G[Return Success];
    B -- No --> H[Return Error: Insufficient Stock];
````

-----

## 📁 Project Structure

```bash
src/main/java/com/inventory/smart_inventory
│
├── controller      # Handles incoming HTTP requests (API Layer)
│   └── ProductController.java
│
├── dto             # Data Transfer Objects (Input validation)
│   └── OrderRequest.java
│
├── model           # Database Entities (Tables)
│   ├── Product.java
│   └── OrderRecord.java
│
├── repository      # Database Access Layer (SQL operations)
│   ├── ProductRepository.java
│   └── OrderRepository.java
│
└── service         # Business Logic (The "Smart" part)
    └── ProductService.java
```

-----

## ⚙️ Setup Instructions

### **1️⃣ Clone the Repository**

```bash
git clone [https://github.com/Navi-1105/Smart-Inventory.git](https://github.com/Navi-1105/Smart-Inventory.git)
cd Smart-Inventory
```

### **2️⃣ Configure Database**

1.  Open MySQL Workbench and run:
    ```sql
    CREATE DATABASE inventory_db;
    ```
2.  Open `src/main/resources/application.properties` and update:
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/inventory_db
    spring.datasource.username=root
    spring.datasource.password=YOUR_PASSWORD
    ```

### **3️⃣ Run the Application**

Run the main class `SmartInventoryApplication.java` in IntelliJ, or use terminal:

```bash
./mvnw spring-boot:run
```

-----

## 🧪 API Endpoints

### 📌 1. Add a Product

**POST** `/api/products`

```json
{
  "name": "Gaming Laptop",
  "category": "Electronics",
  "price": 55000,
  "stockQuantity": 50,
  "minStockLevel": 5
}
```

### 📌 2. Place an Order

**POST** `/api/order`

```json
{
  "productId": 1,
  "quantity": 2
}
```

**Response (Example):**

> "Order processed successfully\! ⚠️ Warning: Stock is low (3 remaining)."

-----

## 👩‍💻 Author

**Navneet**

  * **GitHub:** [Navi-1105](https://github.com/Navi-1105)

-----

⭐ **Star this repo if you found it useful\!**

```

---
