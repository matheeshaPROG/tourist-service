# Tourist Service

## Project Purpose
The **Tourist Service** microservice is dedicated to managing the core personal data of tourists. It handles passport information, tracks entry and exit records at borders, and maintains emergency contact details. This service is crucial for border control and tourist tracking operations.

## Core Components
### Entities (Data Models)
- **`Tourist`**: Contains the primary biographical data of the tourist.
- **`Passport`**: Stores passport details linked to a tourist.
- **`EntryRecord` & `ExitRecord`**: Tracks the exact times and locations of border crossings.
- **`EmergencyContact`**: Stores emergency contact information for tourists.

### Controllers (API Endpoints)
- **`TouristController`**: Endpoints to manage tourist profiles.
- **`PassportController`**: Endpoints to manage passport data.
- **`EntryRecordController` & `ExitRecordController`**: Endpoints to log and retrieve border crossing events.
- **`EmergencyContactController`**: Endpoints to manage emergency contacts.

## Security Overview
Secured with **Spring Security** and **JWT**.
- Access to sensitive border records and passport details is strictly controlled via `SecurityConfig`.
- Only authorized systems or officers with valid JWTs can modify entry/exit records.

## Technologies Used
- Java 21
- Spring Boot 3.1.5
- Spring Data JPA
- Spring Security & JWT
- MySQL Connector
- SpringDoc OpenAPI (Swagger UI)

## Getting Started
1. Clone the repository.
2. Update `application.properties` with your MySQL database credentials.
3. Build the project using Maven: `./mvnw clean install`
4. Run the application: `./mvnw spring-boot:run`

## API Documentation
Once the application is running, access the Swagger UI at:
`http://localhost:<port>/swagger-ui.html`
