# Use Eclipse Temurin Java 21 JDK (Alpine for smaller image)
FROM eclipse-temurin:21-jdk-alpine

# Set working directory inside container
WORKDIR /app

# Copy Maven wrapper and pom.xml first (for caching dependencies)
COPY mvnw pom.xml ./
COPY .mvn .mvn

# Download dependencies (offline mode)
RUN ./mvnw dependency:go-offline

# Copy source code
COPY src ./src

# Package the application (skip tests for faster build)
RUN ./mvnw clean package -DskipTests

# Expose port (Spring Boot default)
EXPOSE 8080

# Run the Spring Boot JAR
CMD ["java", "-jar", "target/portfolio-0.0.1-SNAPSHOT.jar"]
