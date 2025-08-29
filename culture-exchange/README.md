# Culture Exchange Platform

A dynamic Spring Boot web application for cultural exchange, allowing users to share and discover cultural content from around the world.

## Features

- **User Authentication**: Secure login and registration system
- **Cultural Posts**: Create and view posts about different cultures
- **Database**: H2 (development) and PostgreSQL (production) support
- **Security**: Spring Security with role-based access control
- **Modern UI**: Bootstrap-based responsive design with Thymeleaf templates

## Tech Stack

- **Backend**: Spring Boot 3.3.3, Java 21
- **Database**: H2 (dev), PostgreSQL (prod), Flyway migrations
- **Security**: Spring Security, BCrypt password hashing
- **Frontend**: Thymeleaf, Bootstrap 5.3.3
- **Build Tool**: Maven

## Quick Start

### Prerequisites
- Java 21+
- Maven 3.6+

### Development (H2 Database)
```bash
# Clone and navigate to project
cd culture-exchange

# Build the project
mvn clean package

# Run with H2 database (default)
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

### Production (PostgreSQL)
```bash
# Set environment variables
export DATABASE_URL=jdbc:postgresql://localhost:5432/culturedb
export DATABASE_USERNAME=postgres
export DATABASE_PASSWORD=your_password

# Run with PostgreSQL profile
mvn spring-boot:run -Dspring-boot.run.profiles=postgres
```

## Database Schema

The application uses Flyway migrations to manage database schema:

- **Users**: Authentication and user management
- **Posts**: Cultural content with title, content, country
- **Comments**: User interactions on posts
- **Tags**: Categorization system
- **Likes**: Post engagement tracking

## API Endpoints

- `GET /` - Home page (requires authentication)
- `GET /login` - Login page
- `GET /register` - Registration page
- `GET /posts/new` - Create new post (requires authentication)
- `GET /posts/{id}` - View post details
- `POST /posts` - Create new post
- `POST /register` - User registration

## Security

- All content requires authentication
- Password hashing with BCrypt
- Role-based access control (USER, ADMIN)
- CSRF protection enabled

## Development

### H2 Console
Access the H2 database console at `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:culturedb`
- Username: `sa`
- Password: (empty)

### Adding New Features
1. Create domain entities in `com.culture.exchange.domain`
2. Add repositories in `com.culture.exchange.repository`
3. Implement services in `com.culture.exchange.service`
4. Create controllers in `com.culture.exchange.web`
5. Add Thymeleaf templates in `src/main/resources/templates`

## License

This project is open source and available under the MIT License.