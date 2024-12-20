
# ESD Project Backend

This repository contains the backend implementation for the ESD project. It is built using **Spring Boot** and follows a layered architecture with components such as controllers, services, repositories, and exception handling.

## Project Structure

### Configuration
- **`CorsConfig`**: Manages Cross-Origin Resource Sharing (CORS) configuration to allow or restrict access to API endpoints.
- **`SecurityConfig`**: Configures security, including JWT authentication and request authorization.

### Controller Layer
Controllers act as the entry point for handling client requests and orchestrating service calls:
- **`EmployeeController`**: Handles login API for employees.
- **`OrganizationController`**: Manages APIs for organizations and HRs, including creation, retrieval, updates, and deletions.

### DTO (Data Transfer Objects)
DTOs define the payloads for requests and responses:
- **`AddHrRequest`**: Used for adding HRs to an organization.
- **`HrResponse`**: Represents HR details in the response.
- **`LoginRequest`**: Captures employee login data (e.g., username and password).
- **`OrganizationRequest`**: Represents data required for creating an organization.
- **`SearchResponse`**: Aggregates the response for organization and HR data.
- **`UpdateOrganizationRequest`**: Encapsulates details for updating an organization or HR.

### Entity Layer
Entities define the database models:
- **`Credentials`**: Stores login credentials for employees.
- **`Department`**: Represents departments within the organization.
- **`Employee`**: Defines employee details.
- **`HR`**: Represents HR staff linked to organizations.
- **`Organization`**: The main entity representing an organization.

### Exceptions
Custom exceptions and global exception handling:
- **`EntityNotFoundException`**: Thrown when a requested entity is not found (e.g., organization or HR).
- **`InvalidRequestException`**: Used for validation errors or malformed requests.
- **`GlobalExceptionHandler`**: Handles exceptions globally and sends structured error responses to the client.

### Helper Utilities
Utility classes used across the application:
- **`EncryptionService`**: Provides encryption and decryption services (e.g., for secure password storage).
- **`JWTHelper`**: Manages the generation and validation of JSON Web Tokens (JWT) for authentication.
- **`RequestInterceptor`**: Intercepts HTTP requests to add required headers or perform preprocessing.

### Mapper
- **`OrganizationMapper`**: Converts DTOs to entities and vice versa, simplifying transformations between layers.

### Repository Layer
Repositories handle database interactions:
- **`CredentialsRepo`**: Performs CRUD operations for credentials.
- **`EmployeeRepo`**: Manages employee-related database queries.
- **`HrRepo`**: Handles queries related to HR entities.
- **`OrganizationRepo`**: Manages organization-related queries.

### Service Layer
Services contain the business logic and call repositories for data:
- **`EmployeeService`**: Handles employee-specific operations (e.g., authentication, profile updates).
- **`OrganizationService`**: Manages organization and HR operations (e.g., create, update, delete, and search).

### Application Entry Point
- **`EsdProjectApplication`**: The main entry point for starting the Spring Boot application.

---

## Application Flow

### 1. API Request Handling
Client requests are directed to **controllers**, which validate input and pass data to the **services**.

### 2. Service Logic
The **service layer** implements business logic, processes the request, and interacts with the **repositories** to fetch or persist data.

### 3. Data Transformation
Data is transformed between **DTOs** and **entities** using the **OrganizationMapper**.

### 4. Database Operations
Repositories interact with the database to retrieve or store data for entities like `HR`, `Employee`, or `Organization`.

### 5. Exception Handling
Errors like invalid input or missing entities are handled by the **GlobalExceptionHandler**, returning meaningful error messages and HTTP statuses to the client.

### 6. Security
Requests are authenticated and authorized using **JWT**, with security configurations in **SecurityConfig**.

---

## Setup and Running

### Prerequisites
- **Java 17+**
- **Maven**
- **MySQL** or any configured database

## Key Endpoints

### Employee Endpoints
| Method | Endpoint              | Description              |
|--------|-----------------------|--------------------------|
| POST   | `/employee/login`     | Authenticate an employee |

### Organization Endpoints
| Method | Endpoint                                   | Description                           |
|--------|-------------------------------------------|---------------------------------------|
| POST   | `/organizations`                          | Create a new organization            |
| GET    | `/organizations/search_organization`      | Search for an organization by name   |
| PATCH  | `/organizations/{organizationId}`         | Update an organization               |
| DELETE | `/organizations/{organizationId}`         | Delete an organization and its HRs   |

---

## Error Responses

### Entity Not Found
- **Status**: `404 NOT FOUND`
- **Example**: 
  ```json
  {
    "error": "EntityNotFoundException",
    "message": "Organization not found with name: XYZ"
  }
  ```

### Invalid Request
- **Status**: `400 BAD REQUEST`
- **Example**:
  ```json
  {
    "error": "InvalidRequestException",
    "message": "Invalid input data"
  }
  ```

---

## Features
- Secure APIs with JWT-based authentication.
- Clear separation of concerns with modular design.
- Custom exceptions and global error handling.
- Support for creating and managing organizations and their HRs.
