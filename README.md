# Spring_Security_BankOfAmerica
SpringSecurity and CURD operations with SpringBoot and Kafka All together


# 🏦 Banking Security Application - Spring Boot

A Spring Boot Banking Application demonstrating different authentication and authorization mechanisms using **Spring Security**.

This project covers:

1. Basic Authentication
2. JWT Token Authentication
3. OAuth 2.0 Token Authentication
4. Role-Based Authorization
5. Permission-Based Authorization
6. Authentication and Authorization Flow
7. REST API testing using Postman

---

# 📌 Project Overview

This project is a Banking REST API developed using:

- Java
- Spring Boot
- Spring Security
- Spring Data JPA
- MySQL / H2
- JWT
- OAuth 2.0
- Maven
- REST APIs
- Postman

The main purpose of this project is to understand how Spring Security works with different authentication mechanisms.

---

# 🏗️ Application Architecture

```text
                    ┌─────────────────┐
                    │     Client      │
                    │    Postman      │
                    └────────┬────────┘
                             │
                             │ HTTP Request
                             ▼
                  ┌──────────────────────┐
                  │   Spring Security    │
                  │       Filter Chain   │
                  └──────────┬───────────┘
                             │
                ┌────────────┼─────────────┐
                │            │             │
                ▼            ▼             ▼
          Basic Auth       JWT Auth     OAuth 2.0
                │            │             │
                └────────────┼─────────────┘
                             │
                             ▼
                    ┌────────────────┐
                    │ Authentication │
                    │   & Validation │
                    └───────┬────────┘
                            │
                            ▼
                    ┌────────────────┐
                    │ Authorization  │
                    │ Role/Permission│
                    └───────┬────────┘
                            │
                            ▼
                    ┌────────────────┐
                    │ Bank Controller│
                    └───────┬────────┘
                            │
                            ▼
                    ┌────────────────┐
                    │ Bank Service    │
                    └───────┬────────┘
                            │
                            ▼
                    ┌────────────────┐
                    │ Repository      │
                    └───────┬────────┘
                            │
                            ▼
                    ┌────────────────┐
                    │ Database        │
                    └────────────────┘

******************************************** 3 steps to generate TOKEN *****************************************
## JWT Token Generation & Dynamic User Creation

### 1. Generate JWT Token

**POST URL:**

http://localhost:8080/authenticate

**Body:**

```json
{
    "username": "admin",
    "password": "admin123"
}

2. Create Admin

POST URL:

http://localhost:8080/api/users/admin/create

Body:
{
    "username": "admin1",
    "password": "admin123",
    "role": "ADMIN"
}

3. Admin Creates User

After generating the Admin JWT token, the Admin can create Users dynamically.

POST URL:

http://localhost:8080/api/users/admin/create

Body:

{
    "username": "chand",
    "password": "chand123",
    "role": "USER"
}




### Simple flow

```text
1. POST /authenticate
        ↓
   Generate JWT Token
        ↓
2. POST /api/users/admin/create
        ↓
   Create ADMIN
        ↓
3. POST /api/users/admin/create
        ↓
   ADMIN creates USER dynamically


All postman collection link is --->  https://chandududekula3-4865395.postman.co/workspace/chandu-dudekula's-Workspace~48010726-7e8c-40cd-8933-66ec9b98cb9e/collection/51093433-677f81ea-b075-45ab-b03c-99da186f2d5c?action=share&source=copy-link&creator=51093433

All URLS---->  POST
http://localhost:8080/authenticate

{
  "username": "admin",
  "password": "admin123"
  }

http://localhost:8080/authenticate   --- POST 
{
  "username": "user",
  "password": "user123"
}

http://localhost:8080/api/bank/accounts ---> POST  ( ADMIN)
{
    "customerName": "Chandu",
    "accountType": "SAVINGS",
    "branch": "Bangalore",
    "balance": 50000
}

http://localhost:8080/api/bank/accounts  --> GET  ( ADMIN)

http://localhost:8080/api/bank/accounts/1 ---- PUT  ( ADMIN)

{
    "customerName": "Chandu",
    "accountType": "CURRENT",
    "branch": "Hyderabad",
    "balance": 75000
}

http://localhost:8080/api/bank/accounts/1 --- DELETE ( ADMIN)
 http://localhost:8080/api/bank/accounts    --- GET   ( USER)

http://localhost:8080/api/bank/accounts/1   ---> POST   ( USER)

{
    "customerName": "Chandu",
    "accountType": "SAVINGS",
    "branch": "Bangalore",
    "balance": 50000
}

http://localhost:8080/api/bank/accounts/1   ---> PUT ( USER)

{
    "customerName": "Chandu",
    "accountType": "CURRENT",
    "branch": "Hyderabad",
    "balance": 75000
}

http://localhost:8080/api/bank/accounts/1  --- DELETE ( USER)

http://localhost:8080/api/bank/testing ---GET   ( USER)

http://localhost:8080/api/users/register  ---- ADMIN Register   POST
{
    "username": "chand",
    "password": "chand123",
    "role": "USER"
}

http://localhost:8080/api/users/admin/create ---- POST  Admin create 

{
    "username":"chand" ,
    "password":"chand123",
    "role":"USER"
}
