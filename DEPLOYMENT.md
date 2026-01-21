# Production Deployment Guide

This guide provides instructions for deploying the Lost & Found application in a production-like environment.

## 1. Configuration Externalization

For security reasons, sensitive configuration should not be hardcoded in the `application.properties` file. Instead, use environment variables.

### 1.1. Database Credentials

Update your `application.properties` to reference environment variables for the database connection:

```properties
spring.datasource.url=${DB_URL:jdbc:postgresql://localhost:5432/lost_and_found}
spring.datasource.username=${DB_USERNAME:postgres}
spring.datasource.password=${DB_PASSWORD:password}
```

Then, set these environment variables in your deployment environment:
```bash
export DB_URL="jdbc:postgresql://your_production_db_host:5432/your_db_name"
export DB_USERNAME="your_db_user"
export DB_PASSWORD="your_db_password"
```

This example is for PostgreSQL. For MySQL, the `DB_URL` would look like `jdbc:mysql://your_production_db_host:3306/your_db_name`.

### 1.2. JWT Secret

Similarly, externalize the JWT secret. Update `application.properties`:

```properties
jwt.secret=${JWT_SECRET:MySecretKeyForJWTTokenGenerationKumbhMelaLostAndFoundSystem2024}
```

Then, set the environment variable with a strong, randomly generated secret:
```bash
export JWT_SECRET="your_super_strong_randomly_generated_secret_key"
```

## 2. Building the Application

Build the executable JAR file using Maven:
```bash
./mvnw clean package
```
The JAR file will be located in the `target/` directory (e.g., `target/lost_and_found-0.0.1-SNAPSHOT.jar`).

## 3. Running the Application with systemd

`systemd` is a system and service manager for Linux. Creating a service file allows the application to run as a background service and restart automatically.

1.  **Create a service file:**
    Create a file named `lost-and-found.service` in `/etc/systemd/system/`:
    ```ini
    [Unit]
    Description=Lost and Found Application
    After=syslog.target network.target

    [Service]
    User=your_app_user          # It's good practice to run as a non-root user
    Group=your_app_group
    
    # Set environment variables here or in a separate file
    Environment="DB_URL=..."
    Environment="DB_USERNAME=..."
    Environment="DB_PASSWORD=..."
    Environment="JWT_SECRET=..."

    ExecStart=java -jar /path/to/your/app/target/lost_and_found-0.0.1-SNAPSHOT.jar
    
    SuccessExitStatus=143
    Restart=on-failure
    RestartSec=10

    [Install]
    WantedBy=multi-user.target
    ```

2.  **Enable and start the service:**
    ```bash
    sudo systemctl enable lost-and-found.service
    sudo systemctl start lost-and-found.service
    sudo systemctl status lost-and-found.service
    ```

## 4. Nginx Reverse Proxy

Using Nginx as a reverse proxy provides benefits like load balancing, SSL termination, and serving static files directly.

### 4.1. Serving Static Frontend

It is more efficient to let Nginx serve the static frontend files (HTML, CSS, JS) directly.

### 4.2. Proxying API Requests

Nginx will forward API requests (e.g., `/api/`, `/auth/`) to the Spring Boot application running on `localhost:8080`. The `Authorization` header containing the JWT must be passed through.

**Example Nginx Configuration:**
Create a new configuration file in `/etc/nginx/sites-available/lost-and-found`:

```nginx
server {
    listen 80;
    server_name your_domain.com;

    # Location for serving static frontend files
    location / {
        root /path/to/your/app/src/main/resources/static;
        try_files $uri $uri/ /index.html;
    }

    # Location for API and Auth endpoints
    location ~ ^/(api|auth|admin)/ {
        proxy_pass http://localhost:8080;
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        proxy_set_header X-Forwarded-Proto $scheme;
        
        # Crucial for passing the JWT
        proxy_set_header Authorization $http_authorization;
        proxy_pass_header  Authorization;
    }
}
```

**Enable the site:**
```bash
sudo ln -s /etc/nginx/sites-available/lost-and-found /etc/nginx/sites-enabled/
sudo nginx -t # Test configuration
sudo systemctl restart nginx
```

This setup serves the frontend assets from the filesystem and forwards all API-related traffic to the Spring Boot backend, ensuring the JWT authentication token is correctly passed.
