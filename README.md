# Enlace - Microservices API Documentation

Enlace is a modular social platform designed to manage small groups (called "cells") and communities. This repository contains the **User API** which is a key microservice in the Enlace ecosystem.

- **User API**: Manages user registration, authentication, profile updates, and user relationships.

---

## 📦 Technologies

- Java 17+
- Quarkus (Reactive Stack)
- PostgreSQL 14
- Hibernate Reactive & Panache
- Flyway (Database migrations)
- Docker & Docker Compose
- Maven

---

## 📁 Project Structure

```bash
enlace/
├── user-api/         # User microservice
├── group-api/        # Group microservice
└── docker-compose.yml
```

---

## ▶️ Getting Started

### Prerequisites

- Docker & Docker Compose
- JDK 17+
- Maven 3.9+

### Running with Docker Compose

```bash
docker-compose up --build
```

This will start PostgreSQL and both services (if Dockerfiles are provided).

---

## 🔐 User API

### Base URL

```
http://localhost:8181/api/users
```

### Endpoints

| Method | Path                 | Description                    |
|--------|----------------------|--------------------------------|
| GET    | `/`                  | List all users                 |
| GET    | `/{userId}`          | Get user by ID                 |
| POST   | `/`                  | Create a new user              |
| PUT    | `/{userId}`          | Update user by ID              |
| DELETE | `/{userId}`          | Delete user by ID              |

---

## 🔧 Configuration

All service configuration can be managed via `application.yml`:

```yaml
quarkus:
  datasource:
    db-kind: postgresql
    username: enlace
    password: 3nl4c3
    jdbc:
      url: jdbc:postgresql://localhost:5433/user
  flyway:
    migrate-at-start: true
    locations: db/migration
```

---

## 📌 Notes

- Database scripts are located in `/db/migration`.
- The project uses **Flyway** to manage schema evolution.
- Both APIs follow RESTful principles and use JSON for communication.

---

## 📫 Contact

For questions or contributions, please contact the Enlace development team or open an issue.

---

## License

This project is licensed under the MIT License.
