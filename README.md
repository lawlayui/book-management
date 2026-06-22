# Book Management
This project was developed for personal learning purposes, specifically regarding transaction management and relationships between databases. It has two main domains: users and books.

## Learning Objectives
[] study transaction issues
[] learn how the spring authentication system works
[] building database relations
[x] learn spring configuration
[x] learn how to receive and manage requests
[x] learn polymorphism patterns to avoid rewriting simple logic.

## Features
* Authentication using JWT
* Management of books and users

## Prerequisites
* java 17
* spring 4.0.6

## API Documentation
| Endpoint Name | Method | Field JSON | Function |
| :--- | :--- | :--- | :--- |
| `/auth/register`| `POST` | name, email, password | create new user with role user |
| `/auth/login` | `POST` | email, password | verify and get token |
| `/api/users/?size= && page=` | `GET` | | get all users with pageable |
| `/api/users/` | `POST` | name, email, password | create new user with role admin |