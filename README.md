# Library Management System

A backend application built using Spring Boot that provides RESTful APIs to manage a library system including Authors, Books, Users, and Loan operations and scalable architecture with future enhancements in mind.

## Features

- Manage Authors, Books, Users, and Loans
- Implemented Book Borrowing System with availability tracking
- Loan Management (borrow date, due date, return status, fine)
- Pagination & Sorting for efficient data handling
- DTO-based architecture for clean API design
- Exception handling for robust API responses

## Tech Stack

- Java
- Spring Boot
- Spring Data JPA (Hibernate)
- MySQL
- MapStruct
- Postman (API Testing)

## API Endpoints

### Author
  - **/authors** 
 
### Book
  - **/books** 
 
### Loan
  - **/loans**

### User
  - **/user**
 
## Setup Instructions
 - Clone the repository
 - Configure MySQL database in application.properties
 - Run the Spring Boot application
 - Use Postman to test APIs
