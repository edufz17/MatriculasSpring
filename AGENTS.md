# AGENTS.md

## Descripción del proyecto

`MatriculasSpring` es una API REST educativa para gestionar alumnos y cursos.
Está construida con Java 21, Spring Boot, Spring Web MVC, Spring Data JPA,
Hibernate, Lombok y MySQL.

El paquete base es `es.iesjuanbosco.matriculasspring`.

## Estructura relevante

- `src/main/java/.../controller/`: endpoints HTTP de alumnos y cursos.
- `src/main/java/.../entity/`: entidades JPA (`Alumno` y `Curso`).
- `src/main/java/.../repository/`: interfaces `JpaRepository`.
- `src/main/resources/application.properties`: configuración de Spring y MySQL.
- `pom.xml`: dependencias y configuración de Maven.
- `README.md`: documentación de uso y endpoints.
- `target/`: salida generada; no editar ni incluir en commits.

La aplicación principal está en
`src/main/java/es/iesjuanbosco/matriculasspring/MatriculasSpringApplication.java`.

## Requisitos y configuración local

- Java 21.
- Maven o el Maven Wrapper incluido (`./mvnw`).
- MySQL accesible en `localhost:3306`.
- Base de datos `matriculas`.

Configura las credenciales únicamente en el entorno local o mediante un
mecanismo seguro. No añadas contraseñas, tokens ni otros secretos al repositorio.
Revisa `src/main/resources/application.properties` antes de ejecutar la
aplicación y confirma que la configuración de la base de datos es válida.

## Comandos habituales

Desde la raíz del repositorio:

```bash
# Compilar y ejecutar las comprobaciones del proyecto
./mvnw clean verify

# Ejecutar la aplicación
./mvnw spring-boot:run

# Empaquetar
./mvnw clean package
```

Si el wrapper no está disponible, se puede usar `mvn` con los mismos objetivos.
Antes de ejecutar la aplicación debe estar disponible la base de datos MySQL.

## Convenciones de implementación

- Mantener Java 21 y el paquete base existente.
- Seguir la separación actual entre controllers, entities y repositories.
- Usar Spring Data JPA para el acceso a datos; no introducir SQL manual sin una
  necesidad clara.
- Mantener los endpoints REST existentes (`/alumnos` y `/cursos`) y sus códigos
  HTTP salvo que el cambio solicitado requiera modificar el contrato.
- Usar inyección de dependencias por constructor en código nuevo; Lombok
  (`@RequiredArgsConstructor`) ya se utiliza en parte del proyecto.
- Mantener las entidades compatibles con JPA: constructor sin argumentos,
  identificador generado y anotaciones de persistencia coherentes.
- Reutilizar `ResponseEntity` y las respuestas `404 Not Found`, `201 Created` y
  `204 No Content` según el comportamiento actual.
- Mantener los nombres y formatos JSON actuales, incluidos `fechaNacimiento`,
  `importeBeca`, `abreviatura` y los valores del enum `Curso.Nivel`.
- Evitar cambios no relacionados y actualizar `README.md` si se modifica el
  contrato de la API o la forma de ejecutar el proyecto.

## Base de datos

La aplicación usa MySQL y `spring.jpa.hibernate.ddl-auto=update`. No asumir que
el esquema se reinicia en cada ejecución. Los cambios de entidades pueden
afectar a datos existentes, por lo que deben revisarse con cuidado.

## Validación de cambios

Para cambios de Java, controllers, entidades o repositories:

1. Ejecutar `./mvnw clean verify`.
2. Si se modifica la API, comprobar manualmente los endpoints afectados con la
   aplicación ejecutándose y una base de datos de desarrollo.
3. Revisar que no se hayan incluido `target/`, archivos del IDE o secretos.

Actualmente no hay una suite de tests de aplicación visible en
`src/test/`. Si se añade comportamiento no trivial, crear pruebas siguiendo las
dependencias y convenciones ya definidas en `pom.xml`.

## Control de cambios

- No modificar ni revertir cambios existentes que no estén relacionados con la
  tarea.
- No hacer commits de archivos generados (`target/`) ni de configuraciones
  locales con credenciales.
- Mantener los cambios pequeños, enfocados y coherentes con el estilo existente.


## Actualización AGENTS.md
- Mantener este archivo actualizado con la descripción del proyecto, estructura, requisitos y convenciones.