# Seat Exam Assignment - Backend
This project is a web application created in Spring Boot which allow user create account
and add students to classroom. After that we can easily assign seats for exam using
generator. The project goal is easy generate random seats for students no matter which class
it is. 

## Some of the endpoints

| Endpoint                              | Method   | Request                                                            | Response         | Function                                  |
|---------------------------------------|----------|--------------------------------------------------------------------|------------------|-------------------------------------------|
| `/api/auth/register`                  | `POST`   | JSON BODY(user)                                                    | JSON (token JWT) | create new user                           |
| `/api/classrooms`                     | `GET`    | -                                                                  | JSON (list)      | Returns list of classroom                 |
| `/api/classrooms`                     | `POST`   | JSON BODY(classroom)                                               | JSON (classroom) | Creates new classroom                     |
| `/api/classrooms/{id}`                | `DELETE` | Path variable id                                                   | -                | Delete classroom                          |
| `/api/classrooms/{id}/generate-seats` | `POST`   | Path variable id,  <br/>JSON (student list), <br/>JSON (classroom) | JSON (list)      | Returns list of seats (row, column, user) |
| `/api/students`                       | `POST`   | Path variable id                                                   | JSON(student)    | Create student                            |
| `/api/students/id`                    | `PUT`    | Path variable id, <br/> JSON (student)                             | JSON(student)    | Update student data                       |


## Technologies

* Code: Java 17, Spring Boot 3, MySQL
* Tests: JUnit, AssertJ Core
* Other: Maven

## Solved Problems
During the development of this project I had to face of a bunch of problems. These are a few of them.

* Build code that is maximal encapsulated
* Validation requests with controller advice
* Authentication with JWT and Spring Security
* Stick to SOLID rules
* Organize code with conventional commits

## Pre requirements
1. Ensure that you have installed java onn your computer by
```shell
java --version
```
2. Ensure that you have installed docker onn your computer by
```shell
docker --version
```
3. Open docker service or docker-desktop
```shell
sudo service docker start 
```

## How to build the project on your own
1. Clone this repository
```shell
git clone https://github.com/FuuKowatty/SeatAssignment.git
```
2. Go to the folder with cloned repository
3. Build docker image
```shell
docker build -t seat-assignment .
```
4. Open database
```shell
docker run -d -p 3306:3306 seat-assignment 
```
5. Run the application
```shell
.\mvnw spring-boot:run
```