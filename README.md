# File Management System

A full-stack file management application built using Spring Boot and Angular.

This project is created as part of a technical assignment to demonstrate core backend and frontend fundamentals.

---

## 📋 Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Tech Stack](#tech-stack)
- [Getting Started](#getting-started)
- [API Documentation](#api-documentation)
- [Project Structure](#project-structure)
- [Screenshots](#screenshots)
- [Future Enhancements](#future-enhancements)
- [License](#license)

---

## 🎯 Overview

A secure web application that allows users to upload, manage, and download their personal files. Built with Spring Boot and Angular, this project demonstrates clean architecture, JWT authentication, and RESTful API design.

**Key Features:**
- Secure user authentication with JWT
- File upload, download, and deletion
- User-specific file access (complete data isolation)
- Clean layered architecture (Controller → Service → Repository)
- Responsive modern UI

---

## ✨ Features

### Core Functionality
- ✅ User registration and login
- ✅ JWT-based authentication
- ✅ File upload (up to 10MB)
- ✅ File download with original filenames
- ✅ File deletion
- ✅ View all uploaded files with metadata

### Security
- 🔐 BCrypt password encryption
- 🔐 JWT token validation on every request
- 🔐 User-specific data access control
- 🔐 CORS configuration
- 🔐 Stateless session management

### Additional Features
- 📁 Automatic file type detection
- 📊 Human-readable file size display
- 🎨 Clean and responsive UI
- ⚡ Real-time error handling

---

## 🛠️ Tech Stack

### Backend
- **Java 17**
- **Spring Boot 3.2.0**
- **Spring Security** (JWT Authentication)
- **Spring Data JPA** (Database ORM)
- **PostgreSQL** (Database)
- **Maven** (Build Tool)
- **Lombok** (Code Generation)

### Frontend
- **Angular 17**
- **TypeScript**
- **RxJS** (Reactive Programming)
- **HTML5 & CSS3**

---

## 🚀 Getting Started

### Prerequisites

- Java 17+
- Node.js 18+ and npm
- PostgreSQL 12+
- Maven 3.6+
- Angular CLI: `npm install -g @angular/cli`

### Installation

#### 1. Clone the Repository
```bash
git clone https://github.com/yourusername/file-management-system.git
cd file-management-system
```

#### 2. Setup Database
```sql
CREATE DATABASE filemanager_db;
```

#### 3. Configure Backend
Update `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/filemanager_db
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD

jwt.secret=mySecretKeyForJWTTokenGenerationAndValidation123456789
jwt.expiration=86400000

file.upload-dir=./uploads
```

#### 4. Run Backend
```bash
cd file-management-backend
mvn clean install
mvn spring-boot:run
```
Backend runs on: **http://localhost:8081**

#### 5. Run Frontend
```bash
cd file-management-frontend
npm install
ng serve
```
Frontend runs on: **http://localhost:4200**

### Usage
1. Open browser: `http://localhost:4200`
2. Register a new account
3. Login with your credentials
4. Upload, view, download, or delete files

---

## 📚 API Documentation

### Base URL
```
http://localhost:8081/api
```

### Authentication Endpoints

#### Register User
```http
POST /api/auth/register
Content-Type: application/json

{
  "username": "john",
  "email": "john@example.com",
  "password": "password123"
}
```

**Response:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9...",
  "username": "john",
  "email": "john@example.com",
  "message": "Registration successful"
}
```

#### Login User
```http
POST /api/auth/login
Content-Type: application/json

{
  "username": "john",
  "password": "password123"
}
```

**Response:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9...",
  "username": "john",
  "email": "john@example.com",
  "message": "Login successful"
}
```

---

### File Endpoints (Protected)

**Note:** All file endpoints require JWT token in the `Authorization` header:
```
Authorization: Bearer <your-jwt-token>
```

#### Upload File
```http
POST /api/files
Content-Type: multipart/form-data
Authorization: Bearer <token>

file: <binary-file-data>
```

**Response:**
```json
{
  "id": 1,
  "filename": "document.pdf",
  "filesize": 2048576,
  "filetype": "application/pdf",
  "uploadedAt": "2024-02-04T10:30:00"
}
```

#### Get All Files
```http
GET /api/files
Authorization: Bearer <token>
```

**Response:**
```json
[
  {
    "id": 1,
    "filename": "document.pdf",
    "filesize": 2048576,
    "filetype": "application/pdf",
    "uploadedAt": "2024-02-04T10:30:00"
  }
]
```

#### Download File
```http
GET /api/files/{id}
Authorization: Bearer <token>
```

**Response:** Binary file data

#### Delete File
```http
DELETE /api/files/{id}
Authorization: Bearer <token>
```

**Response:**
```json
{
  "success": true,
  "message": "File deleted successfully"
}
```

---

## 📂 Project Structure

### Backend
```
file-management-backend/
├── src/main/java/com/filemanagement/
│   ├── controller/          # REST Controllers
│   ├── service/             # Business Logic
│   ├── repository/          # Data Access Layer
│   ├── model/               # Entity Classes
│   ├── dto/                 # Data Transfer Objects
│   ├── security/            # JWT & Security Config
│   └── FileManagementApplication.java
├── src/main/resources/
│   └── application.properties
├── uploads/                 # File Storage
└── pom.xml
```

### Frontend
```
file-management-frontend/
├── src/app/
│   ├── components/          # UI Components
│   │   ├── login/
│   │   ├── register/
│   │   └── dashboard/
│   ├── services/            # HTTP Services
│   ├── guards/              # Route Guards
│   ├── interceptors/        # HTTP Interceptors
│   ├── models/              # TypeScript Interfaces
│   ├── app.routes.ts
│   └── app.config.ts
├── src/environments/
└── package.json
```

---

## 🔮 Future Enhancements

- [ ] File sharing between users
- [ ] Folder organization
- [ ] File search and filtering
- [ ] File preview (images, PDFs)
- [ ] Cloud storage integration (AWS S3)
- [ ] Multi-file upload with drag-and-drop
- [ ] User profile management
- [ ] Email verification
- [ ] Password reset functionality
- [ ] File versioning

---

## 📄 License

This project is licensed under the MIT License.

---

## 👤 Author

**Kishorekumar J**

Created as part of a technical assignment to demonstrate full-stack development skills.

---

**⭐ If you found this project helpful, please give it a star!**
