# ✈️ Airline Management System

A backend REST API system for airline flight and booking management built with **Java** and **Spring Boot**. Features JWT authentication, global exception handling, input validation, and is deployed on Railway.

🔗 **Live API:** https://airline-management-system-production-35c1.up.railway.app

---

## 💻 Tech Stack

- **Language:** Java 17
- **Framework:** Spring Boot 3.4
- **Security:** Spring Security + JWT
- **Database:** MySQL
- **ORM:** Spring Data JPA / Hibernate
- **Validation:** Jakarta Validation
- **Testing:** JUnit 5 + Mockito
- **Deployment:** Railway
- **Tools:** Maven, Postman, Spring Tool Suite

---

## 🔧 Features

- ✈️ Flight Search API (by source, destination, date)
- 🧍 Passenger Booking API (create, view, cancel)
- 🔐 JWT Authentication (register, login, protected routes)
- ❌ Global Exception Handling (custom error responses)
- ✅ Input Validation (email, phone, required fields)
- 🧪 Unit Tests with Mockito
- 🛫 Admin Panel (add/delete flights)
- 📋 Boarding Pass Generation

---

## 📡 API Endpoints

### Auth (Public)
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/auth/register` | Register new user |
| POST | `/api/auth/login` | Login and get JWT token |

### Flights (Public)
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/flights` | Get all flights |
| GET | `/api/flights/{id}` | Get flight by ID |
| GET | `/api/flights/search` | Search flights by source, destination, date |

### Bookings (Protected - JWT Required)
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/bookings` | Create a booking |
| GET | `/api/bookings` | Get all bookings |
| GET | `/api/bookings/{id}` | Get booking by ID |
| DELETE | `/api/bookings/{id}` | Cancel a booking |

---

## 🔐 Authentication

This API uses JWT (JSON Web Token) authentication.

**Step 1 - Register:**
```http
POST /api/auth/register
Content-Type: application/json

{
    "name": "John Doe",
    "email": "john@gmail.com",
    "password": "password123"
}
```

**Step 2 - Login:**
```http
POST /api/auth/login
Content-Type: application/json

{
    "email": "john@gmail.com",
    "password": "password123"
}
```

**Step 3 - Use token:**
```http
GET /api/bookings
Authorization: Bearer <your_token_here>
```

---

## ❌ Error Handling

All errors return consistent JSON responses:

```json
{
    "status": 404,
    "message": "Flight not found with ID: 999",
    "timestamp": "2026-05-30"
}
```

| Status Code | Meaning |
|-------------|---------|
| 200 | Success |
| 201 | Created |
| 400 | Bad Request (validation error) |
| 403 | Forbidden (no token) |
| 404 | Not Found |
| 500 | Internal Server Error |

---

## 🚀 Run Locally

1. Clone the repo:
```bash
git clone https://github.com/mahima638/airline-management-system.git
```

2. Create MySQL database:
```sql
CREATE DATABASE airlinemanagementsystem;
```

3. Update `application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/airlinemanagementsystem
spring.datasource.username=root
spring.datasource.password=yourpassword
```

4. Run the project in Spring Tool Suite

5. Test APIs at `http://localhost:8080`

---

## 🧪 Running Tests

```bash
mvn test
```

4 unit tests covering FlightService — get all, get by ID, not found exception, delete exception.

---

## 📂 Project Structuresrc/main/java
├── controller      → REST API controllers
├── service         → Business logic
├── repository      → Database operations
├── entity          → JPA entities
├── config          → JWT + Security config
├── exception       → Custom exceptions + global handler
└── dto             → Data transfer objects
---

## 👩‍💻 Developed By

**Mahima Rajpurohit**
📧 mahimarp2004@gmail.com
🔗 [GitHub](https://github.com/mahima638)