# SPRING-SWAGGER

This project contains a basic example with Spring JPA and Swagger.

## Pre requirements

- Gradle 5.4.1 or higher
- Java 12 or higher

## First step

###  Create Gradle Wrappers

Execute the following command, this should have created the folders ".gradle, gradle, gradlew, gradlew.bat"

```sh
gradle wrapper
```

## Run Application
In order to run the application execute the following command:

Linux or Mac:
```sh
gradlew bootRun
```

```Windows
gradlew.bat bootRun
```

## Access to the endpoints

- Swagger: http:localhost:8080/swagger-ui.html
- Endpoints: http:localhost:8080/api/*