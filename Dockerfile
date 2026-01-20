# Use OpenJDK 21
FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

# Copy Maven wrapper and pom.xml first
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Make mvnw executable
RUN chmod +x ./mvnw

# Download dependencies offline
RUN ./mvnw dependency:go-offline

# Copy the rest of the source code
COPY src ./src

# Package the app
RUN ./mvnw package -DskipTests

# Expose port
EXPOSE 8080

# Run the app
CMD ["java", "-jar", "target/portfolio-0.0.1-SNAPSHOT.jar"]
