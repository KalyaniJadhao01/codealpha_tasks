# URL Shortener System

A backend-based URL Shortener service built using Spring Boot, MySQL, and a simple HTML/CSS/JS frontend.  
This project demonstrates core backend development concepts including REST APIs, database design, and layered architecture.

---

## Features

- Create short URLs from long URLs
- Redirect short URLs to original URLs
- Track click count for each URL
- Store creation timestamp
- Analytics API for each URL
- Simple frontend interface

---

## Tech Stack

### Backend
- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA (Hibernate)
- MySQL

### Frontend
- HTML
- CSS
- JavaScript (Fetch API)

---

## Project Structure

controller → REST APIs  
service → Business logic  
repository → Database layer  
entity → Database models  
dto → Request/Response objects  
util → Helper classes

---

## API Endpoints

### Create Short URL

POST /api/urls

Request:
{
"url": "https://example.com"
}

Response:
{
"originalUrl": "https://example.com",
"shortCode": "abc123",
"shortUrl": "http://localhost:8080/abc123"
}

---

### Redirect URL

GET /{shortCode}
Redirects user to original URL.

---

### Analytics API

GET /api/urls/{shortCode}/analytics

Response:
{
"originalUrl": "https://example.com",
"shortCode": "abc123",
"clickCount": 5,
"createdAt": "2026-06-03T12:30:00"
}

---

## Database Table

Table: url_mapping

id → Primary Key  
originalUrl → Long URL  
shortCode → Unique short identifier  
createdAt → Timestamp  
clickCount → Number of redirects

---

## How to Run

1. Create database_ in MySQL:
   CREATE DATABASE url_shortener;

2. Update application.properties with MySQL credentials

3. Run project:
   mvn spring-boot:run

4. Open in browser:
   http://localhost:8080

---

## Learning Outcomes

- REST API development
- Spring Boot backend architecture
- Database integration with JPA
- DTO usage
- Exception handling
- Frontend + backend integration

---

## Future Improvements

- URL expiration feature
- Custom aliases
- Swagger API documentation
- Authentication system
- Redis caching
- Analytics dashboard

---

## Author
Name - Kalyani 
Backend learning project built to understand production-style Spring Boot development.