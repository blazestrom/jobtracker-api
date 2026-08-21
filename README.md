# JobTracker API

A production-ready, highly optimized, and containerized RESTful API built with **Spring Boot** and **PostgreSQL** to manage and track job applications.

---

## 🚀 Key Features

* **JWT & Role-Based Access Control (RBAC):** Centralized JWT stateless authentication with `USER` and `ADMIN` roles enforced at the controller layer via `@PreAuthorize`.
* **Database Performance Tuning:**
  * **Composite Column Indexing:** Fast lookups utilizing a composite index on `(status, user_id)` in PostgreSQL.
  * **N+1 Bottleneck Resolution:** Eager loading of lazy associations using JPQL `JOIN FETCH` queries to optimize fetch performance.
* **Heap Memory Protection:** Server-side page size capping in paginated endpoints (`Math.min(size, 100)`) to safeguard against heap memory overhead.
* **Centralized Architecture:** Automatic DTO mapping, automated bean validation (JSR-380), and centralized global exception handling (`@RestControllerAdvice`).
* **Containerized Deployment:** Dockerized multi-stage builds and multi-container coordination using Docker Compose.

---

## 🛠️ Tech Stack
* **Language:** Java 17
* **Framework:** Spring Boot 4.x, Spring Security (JWT)
* **Database:** PostgreSQL
* **ORM:** Hibernate / Spring Data JPA
* **Tools:** Docker, Docker Compose, Maven, JUnit 5, Mockito

---

## 🐳 Docker Deployment & Setup

Ensure you have **Docker** and **Docker Compose** installed on your system.

1. **Clone the repository:**
   ```bash
   git clone <repository-url>
   cd jobtracker
   ```

2. **Spin up the services:**
   Run the following command in the root folder containing the `docker-compose.yml` file:
   ```bash
   docker compose up --build -d
   ```
   This will:
   * Build the Spring Boot JAR inside a temporary Maven compilation container.
   * Start a PostgreSQL database container with persistent storage volumes.
   * Start the Spring Boot application container after the database healthcheck passes.
   * Expose the application at `http://localhost:8080`.

3. **Check container logs:**
   ```bash
   docker compose logs -f
   ```

4. **Stop the services:**
   ```bash
   docker compose down
   ```

---

## 📋 API Endpoints

### Authentication & Users
| Method | Endpoint | Access | Description |
|---|---|---|---|
| `POST` | `/users` | Public | Register a new user (receives `ROLE_USER` by default) |
| `POST` | `/users/login` | Public | Authenticate user and retrieve JWT token |
| `GET` | `/users` | Authenticated | Retrieve all users |
| `GET` | `/users/{id}` | Authenticated | Retrieve user profile by ID |

### Job Applications
| Method | Endpoint | Access | Description |
|---|---|---|---|
| `POST` | `/applications` | Authenticated | Create a new job application |
| `GET` | `/applications` | **ROLE_ADMIN Only** | Retrieve all applications across all users |
| `GET` | `/applications/{id}` | Authenticated | Retrieve application by ID |
| `PUT` | `/applications/{id}` | Authenticated | Update application details |
| `PATCH` | `/applications/{id}/status` | Authenticated | Update application status (APPLIED, INTERVIEW, etc.) |
| `DELETE` | `/applications/{id}` | Authenticated | Delete a job application |
| `GET` | `/applications/paginated` | Authenticated | Get sorted, paginated applications (size capped at 100) |
| `GET` | `/applications/stats/{userId}` | Authenticated | Get dashboard stats (Total, Applied, Interview, Offer, Rejected) |

---

## 🧪 Testing

To run the automated JUnit test suite:
```bash
mvn clean test
```
