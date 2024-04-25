# Spring Boot User Service

This Spring Boot service provides CRUD (Create, Read, Update, Delete) operations for managing users. It is implemented in Java 11 and uses H2 as an in-memory database. The service includes a controller, service, and repository layers.

## Introduction

The Spring Boot User Service is a RESTful API that allows you to perform CRUD operations on user entities. It is built with Spring Boot and Java 11, providing a lightweight and efficient solution for managing user data.

## Features

- Create, Read, Update, and Delete users
- Access H2 console to view and manage the in-memory database
- Documented endpoints using Springdoc OpenAPI
- Automatic population of sample data on startup

## Installation

To install and run the Spring Boot User Service locally, follow these steps:

1. Clone the repository to your local machine:

    ```bash
    git clone https://github.com/kaisKa/assessment-test.git
    ```

2. Navigate to the project directory:

    ```bash
    cd spring-boot-user-service
    ```

3. Build the project using Maven:

    ```bash
    mvn clean install
    ```

4. Run the application:

    ```bash
    mvn spring-boot:run
    ```

The application will start running on `localhost:8080`.

## Usage

Once the application is running, you can perform CRUD operations on users using the following endpoints:

- **Create User**: POST `/api/users`
- **List All User**: GET `/api/users`
- **Get User ById**: GET `/api/users/{id}`
- **Update User**: PUT `/api/users/{id}`
- **Delete User**: DELETE `/api/users/{id}`

You can access the H2 console to view and manage the in-memory database by navigating to `localhost:8080/h2-console` in your web browser.

The API endpoints are documented using Springdoc OpenAPI. You can access the Swagger UI for interactive API documentation by navigating to `localhost:8080/swagger-ui.html` in your web browser.

## Unit Tests

Unit tests are included for both the controller and service layers to ensure the correctness and reliability of the application. These tests cover various scenarios and edge cases to validate the behavior of the application components.


## Contributing

Contributions are welcome! Feel free to submit pull requests or open issues for any bugs or feature requests.

