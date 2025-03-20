# Spring Boot OAuth2 Authentication

## Overview

This project showcases how to implement **OAuth2 authentication** in a Spring Boot application. It allows users to log in using their existing accounts from OAuth2 providers (e.g., Google, GitHub) and provides secure API access with role-based authorization.

---

## Features

- 🔐 **OAuth2 Login**: Users can authenticate via third-party providers like Google or GitHub.
- 🎯 **Role-Based Authorization**: Access to endpoints is restricted based on user roles.
- 📂 **User Data Storage**: User details are stored in a database for persistence.
- 🛡️ **Stateless Security**: Utilizes OAuth2 tokens for secure, stateless authentication.

---

## Technology Stack

- **Spring Boot 3**
- **Spring Security**
- **Spring OAuth2 Client**
- **MySQL Database (or any supported RDBMS)**
- **Spring Data JPA**

---

## Prerequisites

Before starting, ensure you have the following installed:

1. **Java 17** or later
2. **Maven**
3. A valid account on the [Google Developer Console](https://console.developers.google.com/) or any other OAuth2 provider
4. A running instance of MySQL (or another database)

---

## Setup Instructions

### Step 1: Clone the Repository
Clone this repository to your local machine:
git clone <repository-url>
cd <project-directory>

### Step 2: Add Dependencies
Add the following dependency to your `pom.xml`:
<dependency> <groupId>org.springframework.boot</groupId> <artifactId>spring-boot-starter-oauth2-client</artifactId> </dependency> ```
Alternatively, for Gradle:

implementation 'org.springframework.boot:spring-boot-starter-oauth2-client'

### Step 3: Configure OAuth2 Client
Update your application.yml file with the OAuth2 client details:

text
spring:
  security:
    oauth2:
      client:
        registration:
          google:
            client-id: YOUR_GOOGLE_CLIENT_ID
            client-secret: YOUR_GOOGLE_CLIENT_SECRET
            redirect-uri: "{baseUrl}/login/oauth2/code/google"
            scope: openid, profile, email
        provider:
          google:
            authorization-uri: https://accounts.google.com/o/oauth2/v2/auth
            token-uri: https://www.googleapis.com/oauth2/v4/token
            user-info-uri: https://www.googleapis.com/oauth2/v3/userinfo

            
### Step 4: Configure Security
Create a SecurityConfig class to customize security settings:

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/public/**").permitAll()
                .anyRequest().authenticated()
        )
        .oauth2Login();
        return http.build();
    }
}

### Step 5: Run the Application
Start the application using Maven:

mvn spring-boot:run
Visit http://localhost:8080 to access the application. You will see a login page with an option to authenticate via Google.

API Endpoints
Public Endpoints
Method	Endpoint	Description
GET	/public/info	Publicly accessible info
Protected Endpoints (OAuth2 Login Required)
Method	Endpoint	Description
GET	/user/profile	Retrieve user profile
How OAuth2 Works
The user clicks "Login with Google."

They are redirected to the Google OAuth consent screen.

Upon successful authentication, the user is redirected back to the application.

The application retrieves user details from the OAuth provider and establishes an authenticated session.

License
This project is licensed under the MIT License.

Author
Mohamed Jameer N

Contribution Guidelines
Contributions are welcome! Please submit pull requests for any enhancements or bug fixes.

Happy Coding! 🚀
