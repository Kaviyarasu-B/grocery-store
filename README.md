# FreshMart – Grocery Management System

A web-based Grocery Management System built using **Java, Spring Boot, MySQL, HTML, CSS, and JavaScript**. FreshMart helps manage grocery products, monitor stock, process customer bills, and track sales through an admin dashboard.

## 🚀 Project Overview

FreshMart is designed to simplify daily grocery store operations by providing product inventory management, billing functionality, and sales tracking in one application.

## ✨ Features

- **Admin Authentication** – Secure admin login and logout.
- **Product Management** – Add, view, update, and delete grocery products.
- **Inventory Management** – Track product quantities and available stock.
- **Billing System** – Generate customer bills with product details.
- **Stock Validation** – Prevent sales when requested quantities exceed available stock.
- **Customer Details** – Store customer names and phone numbers with bills.
- **Sales Dashboard** – View sales summaries and recent bills.
- **Receipt Printing** – Print customer billing receipts.

## 🛠️ Technologies Used

| Technology | Purpose |
|---|---|
| Java | Backend programming |
| Spring Boot | Backend framework |
| Spring Security | Authentication and security |
| MySQL | Database |
| HTML | Web page structure |
| CSS | User interface styling |
| JavaScript | Frontend functionality |
| Maven | Build and dependency management |
| Eclipse IDE | Development environment |

## 📁 Project Structure

```text
grocery-store/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── GroceryStore/
│   │   │       ├── controllers/
│   │   │       ├── services/
│   │   │       ├── repositories/
│   │   │       └── entities/
│   │   │
│   │   └── resources/
│   │       ├── static/
│   │       ├── templates/
│   │       └── application.properties
│   │
│   └── test/
│
├── pom.xml
└── README.md
```

*Note: The package and folder names above are illustrative. Adjust them to match the actual project structure.*

## ⚙️ Installation and Setup

### Prerequisites

- Java JDK
- Maven
- MySQL Server
- Eclipse IDE (or another Java IDE)

### 1. Clone the repository

```bash
git clone https://github.com/Kaviyarasu-B/grocery-store.git
```

### 2. Open the project

Import the project into Eclipse as an existing Maven project.

### 3. Create the MySQL database

```sql
CREATE DATABASE grocery_store;
```

### 4. Configure database credentials

Update your local `application.properties` with your MySQL connection details.

Do not commit database passwords or other sensitive credentials to GitHub.

### 5. Run the application

Run the Spring Boot main application class from Eclipse, or use:

```bash
mvn spring-boot:run
```

### 6. Open the application

```text
http://localhost:8080
```

## 🔐 Security

- Admin authentication using Spring Security.
- CSRF protection for applicable requests.
- Database credentials should be maintained outside the public repository.

## 🎯 Future Enhancements

- Low-stock alerts.
- Product search and filtering.
- Improved dashboard analytics.
- Cloud deployment using AWS.
- Responsive UI improvements.

## 👨‍💻 Developer

**Kaviyarasu**

GitHub: [Kaviyarasu-B](https://github.com/Kaviyarasu-B)

---

*FreshMart – A Java Spring Boot project for grocery inventory and billing management.*
