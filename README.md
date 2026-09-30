# MatriculasSpring

Spring Boot REST API for managing school enrollment data for students and courses.

## Overview

This project exposes a simple CRUD API to manage:

- Alumnos (students)
- Cursos (courses)

It uses:

- Java 21
- Spring Boot 3/4
- Spring Web MVC
- Spring Data JPA
- Hibernate
- MySQL

## Project structure

- `src/main/java/es/iesjuanbosco/matriculasspring/controller` — REST controllers
- `src/main/java/es/iesjuanbosco/matriculasspring/entity` — JPA entities
- `src/main/java/es/iesjuanbosco/matriculasspring/repository` — repositories for database access
- `src/main/resources/application.properties` — application and database configuration

## Prerequisites

Before running the app, make sure you have:

- Java 21 installed
- Maven or the included Maven wrapper (`mvnw`)
- MySQL server running locally or in Docker
- A database named `matriculas`

## Database configuration

Edit the file `src/main/resources/application.properties` and set your MySQL credentials, for example:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/matriculas
spring.datasource.username=root
spring.datasource.password=your_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```

Create the database if it does not exist:

```sql
CREATE DATABASE matriculas;
```

## Run the application

Using the Maven wrapper:

```bash
./mvnw clean install
./mvnw spring-boot:run
```

Or with Maven:

```bash
mvn clean install
mvn spring-boot:run
```

The application starts on:

```text
http://localhost:8080
```

## API endpoints

### Alumnos

- `GET /alumnos` — list all students
- `GET /alumnos/{id}` — get one student by ID
- `POST /alumnos` — create a new student
- `PUT /alumnos/{id}` — update a student
- `DELETE /alumnos/{id}` — delete a student
- `PATCH /alumnos/{id}/importe-beca` — update the scholarship amount

### Cursos

- `GET /cursos` — list all courses
- `GET /cursos/{id}` — get one course by ID
- `POST /cursos` — create a new course
- `PUT /cursos/{id}` — update a course
- `DELETE /cursos/{id}` — delete a course
- `DELETE /cursos` — delete all courses
- `PATCH /cursos/{id}/abreviatura` — update short name/abbreviation

## Example payloads

### Alumno

```json
{
  "nombre": "Ana",
  "apellidos": "García López",
  "email": "ana@example.com",
  "fechaNacimiento": "2007-05-12",
  "dni": "12345678A",
  "telefono": "612345678",
  "importeBeca": 150.00
}
```

### Curso

```json
{
  "nombre": "Desarrollo de Aplicaciones Web",
  "abreviatura": "DAW",
  "nivel": "CFGS"
}
```

## Notes

- JPA is configured with `ddl-auto=update`, so the schema is generated automatically.
- This project is intended as a teaching/demo example for Spring Boot + JPA + REST APIs.

## License

This project does not include a specific license declaration yet.
