# Employee Management System

## About the Project

The Employee Management System is a REST API application built using Spring Boot, Spring Data JPA, Hibernate, and MySQL.
It provides APIs to manage employee information and demonstrates the fundamentals of backend application development.

## Technologies Used

- Java
- Spring Boot
- Spring Data JPA
- Hibernate
- MySQL
- Spring Security
- REST API
- Maven
- Postman
- Eclipse IDE

## Features

- Create new employee records
- Retrieve employee details by ID
- Retrieve all employees
- Update employee information
- Delete employee records
- Validate employee input
- Handle duplicate email addresses
- Handle exceptions with meaningful responses
- Support pagination and sorting
- Persist employee data in MySQL

## API Endpoints

| Method | Endpoint                       | Description                  |
| ------ | ------------------------------ | ---------------------------- |
| POST   | `/api/employees`               | Create an employee           |
| GET    | `/api/employees/all`           | Retrieve all employees       |
| GET    | `/api/employees/{id}`          | Get an employee by ID        |
| PUT    | `/api/employees/{id}`          | Update an employee           |
| DELETE | `/api/employees/{id}`          | Delete an employee           |
| GET    | `/api/employees?page=0&size=5` | Retrieve paginated employees |

*Note: Update the endpoints if your implementation differs.*

## Setup and Installation

1. Install Java and Eclipse IDE.
2. Install MySQL and create a database for the application.
3. Clone or download this repository.
4. Open the project in Eclipse as an existing Maven project.
5. Configure the database URL, username, password, and other required settings in `application.properties`.
6. Run the application using `EmployeeManagementSystemApplication.java`.
7. Test the REST API endpoints using Postman.

## Example Employee JSON

```json
{
  "firstName": "Ravi",
  "lastName": "Kumar",
  "email": "ravi@example.com",
  "department": "IT",
  "salary": 35000
}
```

## Learning Outcomes

- Building REST APIs with Spring Boot
- Implementing CRUD operations
- Connecting Spring Boot to MySQL
- Using Spring Data JPA and Hibernate
- Applying validation and exception handling
- Implementing pagination and sorting
- Testing APIs using Postman

## Author

Yagna Sree Vaddemani
