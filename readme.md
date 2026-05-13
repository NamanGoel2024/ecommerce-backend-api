# E-Commerce Backend API

A professional Spring Boot based E-Commerce Backend API implementing JWT Authentication, Role-Based Authorization, Cart & Order Management, Swagger Documentation, and MySQL Database Integration.

---

# Features

## Authentication & Security
- JWT Authentication
- BCrypt Password Encryption
- Role-Based Authorization (ADMIN / CUSTOMER)
- Protected APIs using Spring Security

## Product Management
- Add Products
- Update Products
- Delete Products
- View Products
- Inventory Management

## Cart System
- Add to Cart
- View Cart
- Quantity Management

## Order System
- Place Orders
- Order History
- Automatic Stock Reduction

## Professional Backend Features
- DTO Responses
- Global Exception Handling
- Swagger API Documentation
- RESTful API Design

---

# Tech Stack

- Java 17
- Spring Boot
- Spring Security
- JWT
- MySQL
- Hibernate / JPA
- Maven
- Swagger / OpenAPI

---

# API Documentation

Swagger UI:

http://localhost:8080/swagger-ui/index.html

---

# Database

MySQL Database used with:
- JPA/Hibernate Relationships
- Foreign Keys
- Entity Mapping

---

# Project Structure

src/main/java/ecommerce_backend_api

- controller
- service
- repository
- entity
- security
- dto
- exception
- config

---

# Roles

## ADMIN
- Manage Products

## CUSTOMER
- Access Cart
- Place Orders
- View Orders

---

# Security Flow

JWT Token
↓
JwtFilter
↓
Spring Security
↓
Role Authorization
↓
Protected APIs

---

# Future Improvements

- Payment Gateway Integration
- Email Notifications
- Docker Deployment
- Cloud Deployment (AWS)

---

# Author

Naman Goel