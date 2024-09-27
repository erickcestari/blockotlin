# kotlin-ktor-rest-api
A Modern Kotlin-Ktor RESTful API example. Connects to a PostgreSQL database and uses Exposed framework for database
operations. Focused on Software Architecture, SOLID Principles, dependency injection, testable code and feature based
development.

- Ktor Framework
- Kotlin
- Dependency Injection -> Koin
- Authorization -> JWT
- Database -> PostgreSQL
- ORM SQL Framework -> Exposed
- Ktor Client -> For External API calls
- Build Tool -> Gradle
- Server -> netty
- Docker support for containerization
- POSTMAN Collection for testing API
- Gradle 7, JDK (Amazon Coretto 11)

# How to run

## Prerequisites

1. **Java JDK**: Ensure you have Java JDK version 8 or higher installed.

2. **Gradle**: https://gradle.org/install

## Steps to Run the Project

1. **Clone the Repository**:
    ```sh
    git clone https://github.com/erickcestari/blockotlin
    cd blockotlin
    ```

2. **Generate Gradle Wrapper**:
    ```sh
    gradle wrapper
    ```

3. **Run the Project**:
    On Windows:
    ```sh
    gradlew.bat run
    ```
    On macOS/Linux:
    ```sh
    ./gradlew run
    ```

This will start the Ktor server, and you can access the API at `http://localhost:8081`.

# Architecture
![github (6)](https://user-images.githubusercontent.com/86873858/131125468-99d372c5-2b55-473b-9f12-0fbd2c7e9bf7.png)

# Database Credentials (Please add your PostgreSQL credentials here to connect to your database)
resources/application.conf

```
    database {
        exampleDatabaseUrl="jdbc:postgresql://localhost:5432/$YOUR_DB_NAME"
        exampleDatabaseDriver="org.postgresql.Driver"
        exampleDatabaseUser="$YOUR_USERNAME"
        exampleDatabasePassword="$YOUR_PASSWORD"
    }
```
# Features
## - Health Check

**GET /public-api/v1/healthCheck**</br>
&nbsp; Just returns a simple object</br>
## - Authentication

**POST /public-api/v1/authentication/createUser**</br>
&nbsp; Registers a user to the db</br>
**POST /public-api/v1/authentication/login**</br>
&nbsp; Returns a jwt token if success</br>
**GET /api/v1/authentication/userInfo** (Needs Authorization token from login as Authorization header : "Bearer
$token")</br>
&nbsp; Returns the user information</br>
## - Star Wars

**GET /api/v1/star-wars/movie** (Needs Authorization token from login as Authorization header : "Bearer $token")</br>
&nbsp; An External API call example. Returns a movie information from an external api
