# Aircraft-Maintenance-System-Spring-Boot Login Page with JWT Authentication and Authorization

## Overview

This Spring Boot project demonstrates the implementation of a secure login page with authentication and authorization using JSON Web Tokens (JWT) and `java.security`. The application provides a robust foundation for user authentication and access control in a web application.

## Features

- **User Authentication:** Secure user login with JWT-based authentication.
- **Authorization:** Role-based access control for different user roles.
- **JWT Generation:** Use of `java.security` for generating and validating JWT tokens.
- **Spring Boot Security:** Integration with Spring Security for enhanced security features.
- **Login Page:** A simple and customizable login page for user authentication.

## Technologies Used

- Spring Boot
- Spring Security
- JSON Web Tokens (JWT)
- Java Security

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 17 or higher
- Gradle

### Installation

1. Clone the repository: `git clone https://github.com/BonifaceNoel/Aircraft-Maintenance-System-Login-Page.git`
2. Navigate to the project directory: `cd Aircraft-Maintenance-System-Login-Page`
3. Build the project: `./mvnw clean install`
4. Run the application: `./mvnw spring-boot:run`

Make sure you have Java and Maven installed on your machine.

## Configuration

- The application is configured to allow cross-origin requests from `http://localhost:5173`.
- The H2 database console is accessible at `http://localhost:8095/h2-console` with MySQL URL `jdbc:mysql://localhost:3306/aircraft_maintenance_system`.

## Contributing

If you would like to contribute to the development of this project, please follow the [contribution guidelines](CONTRIBUTING.md).

## License

This project is licensed under the [MIT License](LICENSE).

## Author

[Boniface Noel]

Feel free to reach out if you have any questions or feedback!

