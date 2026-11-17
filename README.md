# MatriculasSpring

REST API built with Spring Boot to manage school enrollment data: students,
courses, subjects, enrollments, users and their details.

## Overview

The project exposes CRUD operations for:

- **Alumnos** (students)
- **Cursos** (courses)
- **Materias** (subjects, linked to courses through a many-to-many relation)
- **Matrículas** (enrollments of a student in a course)
- **Usuarios** (application users, with address details and JWT-based login)

It uses:

- Java 21
- Spring Boot 4.1.1
- Spring Web MVC
- Spring Data JPA / Hibernate
- Spring Security with JWT (`jjwt`)
- Bean Validation (Jakarta Validation)
- Lombok
- MySQL
- Maven (with the included wrapper)

## Project structure

```text
src/main/java/es/iesjuanbosco/matriculasspring/
├── controller/   REST controllers
├── DTO/          request/response objects
├── entity/       JPA entities
├── exception/    global exception handler and custom exceptions
├── repository/   Spring Data JPA repositories
├── service/      business logic
└── MatriculasSpringApplication.java
src/main/resources/application.properties   application and database configuration
```

## Prerequisites

- Java 21
- Maven or the included Maven wrapper (`./mvnw`)
- MySQL running on `localhost:3306`
- A database named `matriculas`

## Database configuration

Edit `src/main/resources/application.properties` and set your local MySQL
credentials. Do not commit real credentials or secrets to the repository.

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/matriculas
spring.datasource.username=root
spring.datasource.password=your_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
```

Create the database if it does not exist:

```sql
CREATE DATABASE matriculas;
```

## Run the application

Using the Maven wrapper:

```bash
./mvnw clean verify
./mvnw spring-boot:run
```

Or with Maven:

```bash
mvn clean verify
mvn spring-boot:run
```

The application starts on:

```text
http://localhost:8080
```

## API endpoints

### Authentication

- `POST /auth/register` — register a new user, returns the created user
- `POST /auth/login` — authenticate with email/password, returns a JWT token

### Alumnos

- `GET /alumnos` — list all students
- `GET /alumnos/{id}` — get one student by ID
- `POST /alumnos` — create a new student
- `PUT /alumnos/{id}` — update a student
- `DELETE /alumnos/{id}` — delete a student
- `PATCH /alumnos/{id}/importe-beca` — update the scholarship amount
- `GET /alumnos/{alumnoId}/matriculas` — list the enrollments of a student

### Cursos

- `GET /cursos` — list all courses
- `GET /cursos/{id}` — get one course by ID
- `POST /cursos` — create a new course
- `PUT /cursos/{id}` — update a course
- `DELETE /cursos/{id}` — delete a course
- `DELETE /cursos` — delete all courses
- `POST /cursos/{cursoId}/materias/{materiaId}` — add a subject to a course
- `DELETE /cursos/{cursoId}/materias/{materiaId}` — remove a subject from a course
- `GET /cursos/{cursoId}/matriculas` — list the enrollments of a course

### Materias

- `GET /materias` — list all subjects
- `GET /materias/{id}` — get one subject by ID
- `POST /materias` — create a new subject
- `PUT /materias/{id}` — update a subject
- `DELETE /materias/{id}` — delete a subject

### Matrículas

- `GET /matriculas` — list all enrollments
- `POST /matriculas` — create an enrollment from an existing student and course
- `DELETE /matriculas/{id}` — delete an enrollment
- `GET /matriculas/resumen` — summary with student name, course name and enrollment ID

### Usuarios

- `GET /usuarios` — list all users (without password)
- `GET /usuarios/buscar?nombre=...` — search users by name
- `POST /usuarios/{usuarioId}/detalle-usuario` — create the address details of a user
- `GET /usuarios/{usuarioId}/detalle-usuario` — get the address details of a user
- `DELETE /usuarios/{usuarioId}/detalle-usuario` — delete the address details of a user

### Detalles de usuario

- `GET /detalles-usuarios` — list all user details
- `GET /detalles-usuarios/{id}` — get user details by ID
- `POST /detalles-usuarios` — create user details
- `PUT /detalles-usuarios/{id}` — update user details
- `DELETE /detalles-usuarios/{id}` — delete user details

## Example payloads

### Register

```json
{
  "nombre": "Ana",
  "apellidos": "García López",
  "email": "ana@example.com",
  "password": "secret123",
  "fechaNacimiento": "2007-05-12"
}
```

### Login

```json
{
  "email": "ana@example.com",
  "password": "secret123"
}
```

Response:

```json
{
  "token": "<jwt>"
}
```

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

Valid `nivel` values: `ESO`, `BACHILLERATO`, `CFGS`, `CFGM`, `CFGB`.

### Materia

```json
{
  "nombre": "Programación",
  "abreviatura": "PROG"
}
```

### Matrícula

```json
{
  "alumno": { "id": 1 },
  "curso": { "id": 1 }
}
```

### Detalle de usuario

```json
{
  "calle": "Calle Mayor",
  "numero": "12",
  "poblacion": "Albacete",
  "provincia": "Albacete",
  "telefono": "612345678",
  "codigoPostal": "02001",
  "comentarios": "Sin observaciones"
}
```

## Notes

- JPA is configured with `ddl-auto=update`, so the schema is generated and
  updated automatically. Changes to entities may affect existing data.
- Validation errors are returned by `GlobalExceptionHandler` as `400 Bad Request`
  with a field-to-message map. Duplicate emails return `409 Conflict`.
- `/auth/login` issues a JWT signed with an in-memory key. A
  `SecurityFilterChain`/JWT resource-server configuration is not yet defined,
  so the token is currently issued but not enforced on the other endpoints.
- This project is intended as a teaching/demo example for Spring Boot + JPA +
  REST APIs.

## License

This project does not include a specific license declaration yet.
