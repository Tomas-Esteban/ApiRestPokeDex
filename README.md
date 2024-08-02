# ApiRestPokeDex

Este proyecto es una API REST desarrollada en Java utilizando Spring Boot. La API se conecta con la PokeAPI para obtener datos sobre Pokémon y permite realizar operaciones CRUD sobre esta información.

## Descripción

La API proporciona una interfaz para interactuar con la PokeAPI, utilizando Spring Boot para crear un servicio RESTful. El proyecto incluye integración con una base de datos MySQL, validación, mapeo de objetos con ModelMapper y documentación con Swagger.

## Requisitos

- Java 11
- Maven
- MySQL
- Spring Boot 2.7.0

## Dependencias

El proyecto utiliza las siguientes dependencias:

- `spring-boot-starter-web`: Para crear aplicaciones web y servicios RESTful.
- `spring-boot-starter-data-jpa`: Para la integración con JPA y Hibernate.
- `spring-boot-starter-validation`: Para la validación de datos.
- `springfox-boot-starter`: Para la generación de documentación Swagger.
- `modelmapper`: Para el mapeo de objetos entre entidades y DTOs.
- `guava`: Biblioteca de Google con utilidades adicionales.
- `mysql-connector-java`: Conector JDBC para MySQL.
- `h2`: Base de datos en memoria para pruebas.
- `lombok`: Biblioteca para reducir el código repetitivo.
- `spring-boot-starter-test`: Para pruebas unitarias y de integración.
- `spring-boot-starter-webflux`: Para soporte de programación reactiva.
- `gson`: Para trabajar con JSON.

## Configuración

### `application.properties`

```properties
# SERVER
server.port=8080
server.error.include-message=always

# DATASOURCE
spring.jpa.hibernate.ddl-auto=create-drop
spring.datasource.initialization-mode=always
spring.datasource.platform=mysql
spring.datasource.url=jdbc:mysql://127.0.0.1:3306/pokedex
spring.datasource.username=root
spring.datasource.password=pass

# JPA
spring.jpa.show-sql=true

# LOGGING
logging.level.root=ERROR
logging.level.org.springframework=INFO

# SPRING
spring.output.ansi.enabled=ALWAYS
spring.mvc.pathmatch.matching-strategy=ant-path-matcher

# POKEAPI URL
pokeapi.base.url=https://pokeapi.co/api/v2/
```

## Estructura del Proyecto
- `src/main/java/com/tomasesteban/pokeapi`: Contiene el código fuente de la aplicación.
- `src/main/resources`: Contiene los archivos de configuración, incluyendo `application.properties`.
- `src/test/java`: Contiene las pruebas unitarias y de integración.


## Cómo Ejecutar el Proyecto
1. Clona el repositorio:
    git clone https://github.com/tu_usuario/ApiRestPokeDex.git
2. Navega al directorio del proyecto:
    cd ApiRestPokeDex
3. Ejecuta el proyecto utilizando Maven:
    ./mvnw spring-boot:run
   o, si estás en Windows:
    mvnw.cmd spring-boot:run


# Documentación

La API está documentada con Swagger. Puedes acceder a la documentación a través de:





