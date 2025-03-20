Spring Boot OAuth2 Authentication

This is a Spring Boot E-commerce project that implements OAuth2 authentication using Google. It also supports JWT authentication for securing API requests.

Features

🔐 OAuth2 Login with Google

🔑 JWT Authentication

🎯 Role-Based Authorization (Admin/User)

📂 User Data Storage in Database

🛡️ Stateless API Security using JWT

Tech Stack

Spring Boot 3

Spring Security

Spring OAuth2 Client

JWT (JSON Web Token)

MySQL Database

Spring Data JPA

Prerequisites

Before running the project, ensure you have the following installed:

Java 17 or later

Maven

MySQL Database

Google Developer Console Account (for OAuth2 credentials)

Setup Instructions

1️⃣ Clone the Repository

2️⃣ Configure Google OAuth2 Client

Go to Google Developer Console → Create a project.

Enable OAuth2 and generate client-id and client-secret.

Update application.yml:

3️⃣ Configure MySQL Database

Update application.yml with your database credentials:

4️⃣ Run the Application

API Endpoints

🔹 Public Endpoints

Method

Endpoint

Description

POST

/register

Register a user

POST

/login

Authenticate user

GET

/oauth2/authorize

OAuth2 Login

🔹 Protected Endpoints (JWT Required)

Method

Endpoint

Role

Description

GET

/user/profile

USER

Get user profile

GET

/admin/dashboard

ADMIN

Admin dashboard

How OAuth2 Works

User clicks "Login with Google".

Redirected to Google OAuth2 consent screen.

On success, user details are stored in the database.

OAuth2 User receives JWT token for authentication.

License

This project is licensed under the MIT License.

Author

Your Name - GitHub Profile

Contribution

Pull requests are welcome! Feel free to contribute to the project.

Happy Coding! 🚀

