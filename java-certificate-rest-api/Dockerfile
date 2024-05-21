# Stage 1: Build the Spring Boot JAR file
FROM maven AS builder
WORKDIR /app

# Copy the project files and build the JAR file
COPY pom.xml .
COPY src src
RUN mvn clean install
RUN mvn package spring-boot:repackage

# Stage 2: Create the final image with the built JAR file
FROM openjdk:21-jdk
WORKDIR /app

# Copy the built JAR file from the builder stage
COPY --from=builder /app/target/*.jar app.jar

# Port exposure
EXPOSE 8001

# Define the command to run your Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
