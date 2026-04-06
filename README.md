# 🛒 Sales Management System (JDBC Project)

## 📌 Project Overview

This is a **Java JDBC-based menu-driven application** developed as part of a DBMS project.
The system manages **Customers, Products, and Orders** using an Oracle database.

It allows users to perform **CRUD operations** and generate reports through a console-based interface.

---

## 🚀 Features

### 👤 Customer Management

* Add new customer
* View all customers
* Search customer by ID
* Update customer details
* Delete customer

### 📦 Product Management

* Add new product
* View all products
* Update product stock
* Delete product

### 🧾 Order Management

* Place new order (multiple products)
* View all orders
* View order details (JOIN operations)
* Delete order
* Generate sales report (customer-wise)

---

## 🏗️ Tech Stack

* **Language:** Java
* **Database:** Oracle XE
* **Connectivity:** JDBC (ojdbc17.jar)
* **IDE:** VS Code / IntelliJ / SQL Developer

---

## 🗂️ Project Structure

```
Sales Management System/
│
├── MainMenu.java        # Entry point (menu-driven UI)
├── CustomerDAO.java    # Customer operations
├── ProductDAO.java     # Product operations
├── OrderDAO.java       # Order logic (core module)
└── README.md
```

---

## 🛠️ Database Design

### Tables:

* **customers**
* **products**
* **orders**
* **order_items**

### Key Concepts:

* Primary Keys & Foreign Keys
* Sequence + Trigger (Oracle auto-increment)
* JOIN operations
* Normalized schema

---

## ⚙️ Setup Instructions

### 1️⃣ Clone the repository

```
git clone https://github.com/your-username/your-repo-name.git
cd your-repo-name
```

---

### 2️⃣ Add JDBC Driver

Download and place:

```
ojdbc17.jar
```

---

### 3️⃣ Compile the project

```
javac *.java
```

---

### 4️⃣ Run the project

```
java -cp ".;path_to_ojdbc17.jar" MainMenu
```

Example:

```
java -cp ".;d:\downloaded\ojdbc17.jar" MainMenu
```

---

## 🧪 Sample Database Setup

* Create tables using Oracle SQL
* Use **SEQUENCE + TRIGGER** for auto-increment IDs
* Insert sample data before running the app

---

## 📊 Key Functionalities

* Uses **PreparedStatement** (prevents SQL injection)
* Implements **CRUD operations**
* Uses **JOIN queries** for order details
* Calculates order totals dynamically
* Menu-driven console UI

---

## ⚠️ Important Notes

* Oracle does **not support AUTO_INCREMENT**, so Sequence + Trigger is used
* `getGeneratedKeys()` is replaced with sequence-based ID generation
* Ensure Oracle services are running before execution

---

## 🎯 Learning Outcomes

* JDBC connectivity with Oracle
* Database design and normalization
* CRUD operations using Java
* Handling relationships (1-M, M-M)
* Writing menu-driven console applications

---

## 📌 Future Improvements

* Add GUI (JavaFX / Swing)
* Add authentication system
* Implement transaction management
* Add order status tracking

---

## 👨‍💻 Author

**Piyush Lende**
DBMS / JDBC Project

---

## ⭐ If you like this project

Give it a ⭐ on GitHub!
