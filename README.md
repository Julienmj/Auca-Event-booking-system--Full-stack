# AUCA Campus Event Booking System

A full-stack web application for AUCA students to browse and register for campus events.

---

## Project Structure

```
├── backend/       # Spring Boot REST API (Java 23)
└── frontend/      # Coming soon
```

---

## Backend

### Tech Stack
- Java 23
- Spring Boot 4.0.6
- Spring Data JPA
- MySQL
- Lombok
- Springdoc OpenAPI (Swagger UI)

### Setup

1. Create the database in MySQL:
   ```sql
   CREATE DATABASE eventbooking_db;
   ```

2. Update credentials in `backend/src/main/resources/application.properties` if needed:
   ```properties
   spring.datasource.username=root
   spring.datasource.password=
   ```

3. Run the application:
   ```bash
   cd backend
   ./mvnw spring-boot:run
   ```

4. Swagger UI available at:
   ```
   http://localhost:8080/swagger-ui.html
   ```

### Folder Structure

```
backend/src/main/java/eventbooking/
├── model/
│   ├── Event.java
│   └── Registration.java
├── repository/
│   ├── EventRepository.java
│   └── RegistrationRepository.java
├── service/
│   ├── EventService.java
│   └── RegistrationService.java
└── controller/
    ├── EventController.java
    └── RegistrationController.java
```

### Data Models

**Event**
| Field | Type | Notes |
|-------|------|-------|
| eventId | Long | Auto-increment PK |
| title | String | |
| location | String | |
| date | String | |
| availableSeats | int | Decremented on registration |

**Registration**
| Field | Type | Notes |
|-------|------|-------|
| regId | Long | Auto-increment PK |
| name | String | Student name |
| eventId | Long | FK reference to Event |

### API Endpoints

| Method | Endpoint | Status | Description |
|--------|----------|--------|-------------|
| GET | `/api/events` | 200 OK | Returns all events |
| POST | `/api/events` | 201 Created | Creates a new event |
| POST | `/api/registrations` | 201 Created | Registers a student for an event |

#### POST /api/registrations — Validation Rules
- Returns `400 Bad Request` if the event does not exist
- Returns `400 Bad Request` if `availableSeats` is 0
- On success, decrements `availableSeats` by 1 and saves the registration

---

## Branch Strategy

| Branch | Purpose |
|--------|---------|
| `main` | Stable, production-ready code |
| `dev` | Active development — backend + frontend work in progress |

---

## Progress

- [x] Backend — Spring Boot REST API
- [ ] Frontend — Coming soon
