# Medical Record System

## Overview

Medical Record System is a full-stack Spring Boot web application
designed to manage patient medical data, medical records, and
healthcare-related entities. The system follows a layered architecture
and exposes RESTful APIs for structured data manipulation while
supporting server-side rendering for web interaction.

This project demonstrates backend engineering practices including domain
modeling, persistence abstraction, and scalable service design.

------------------------------------------------------------------------

## Features

-   RESTful API for managing medical records
-   Patient data management
-   Layered architecture (Controller → Service → Repository → Entity)
-   Spring Boot MVC web support
-   JPA/Hibernate persistence
-   MySQL/relational database integration
-   Clean domain-driven structure
-   Extensible and modular backend design

------------------------------------------------------------------------

## Technology Stack

**Backend** - Java - Spring Boot - Spring MVC - Spring Data JPA -
Hibernate

**Database** - MySQL (or any compatible relational DB)

**Build Tool** - Maven

------------------------------------------------------------------------

## Architecture

The project follows a layered architecture:

Controller Layer\
Handles HTTP requests, validates input, and returns responses.

Service Layer\
Contains business logic and orchestration between repositories and
domain models.

Repository Layer\
Responsible for data persistence using Spring Data JPA.

Entity Layer\
Defines the domain model and database mapping.

------------------------------------------------------------------------

## Project Structure

    Medical-Record/
    │
    ├── src/main/java/
    │   ├── controller/       # REST and MVC controllers
    │   ├── service/          # Business logic
    │   ├── repository/       # Data access layer
    │   ├── model/entity/     # JPA entities
    │   └── MedicalRecordApplication.java
    │
    ├── src/main/resources/
    │   ├── application.properties
    │   ├── templates/
    │   └── static/
    │
    └── pom.xml

------------------------------------------------------------------------

## Installation

### Prerequisites

-   Java 17+
-   Maven
-   MySQL (or compatible database)

### Clone Repository

``` bash
git clone https://github.com/Thurnos/Medical-Record.git
cd Medical-Record
```

### Configure Database

Edit `application.properties`:

    spring.datasource.url=jdbc:mysql://localhost:3306/medical_record
    spring.datasource.username=YOUR_USERNAME
    spring.datasource.password=YOUR_PASSWORD
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true

### Build Project

``` bash
mvn clean install
```

### Run Application

``` bash
mvn spring-boot:run
```

Application runs on:

    http://localhost:8080

------------------------------------------------------------------------

## API Usage

Example endpoints (depending on implementation):

    GET     /patients
    GET     /patients/{id}
    POST    /patients
    PUT     /patients/{id}
    DELETE  /patients/{id}

All responses are returned in JSON format unless rendered via MVC
templates.

------------------------------------------------------------------------

## Database Design

The system uses relational mapping via JPA/Hibernate. Typical entities
may include:

-   Patient
-   MedicalRecord
-   Doctor
-   Appointment
-   Diagnosis
-   Treatment

Relationships: - One-to-Many (Patient → Medical Records) - Many-to-One
(Record → Doctor) - Optional relational extensions

------------------------------------------------------------------------

## Configuration

Key Spring Boot configuration:

-   `spring.jpa.hibernate.ddl-auto=update` -- auto schema management
-   `spring.jpa.show-sql=true` -- SQL debugging
-   `server.port=8080` -- default server port

------------------------------------------------------------------------

## Extensibility

The system is designed for easy expansion:

-   Authentication & Authorization (Spring Security, JWT)
-   Role-based access (Doctor/Admin/Patient)
-   Audit logging
-   File storage (medical scans, reports)
-   REST API documentation (Swagger/OpenAPI)
-   Microservice decomposition
-   Docker deployment
-   Cloud database integration

------------------------------------------------------------------------

## Build & Packaging

Generate runnable JAR:

``` bash
mvn package
java -jar target/medical-record.jar
```

------------------------------------------------------------------------

## Development Practices

-   Layered architecture
-   Separation of concerns
-   Dependency Injection
-   Repository abstraction
-   Domain-driven structure
-   Clean and maintainable codebase

------------------------------------------------------------------------

## Contributing

1.  Fork repository
2.  Create feature branch
3.  Commit changes
4.  Push branch
5.  Open Pull Request

------------------------------------------------------------------------

## License

This project is open-source and available under the MIT License.

------------------------------------------------------------------------

## Author

Developed as a backend medical record management system demonstrating
Spring Boot, JPA, and RESTful architecture design.
