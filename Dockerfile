# Use official Maven image to build the application
FROM maven:3.9.4-eclipse-temurin-21 AS build

# Set working directory inside the container
WORKDIR /app

# Copy the pom.xml and Maven wrapper files
COPY pom.xml ./
COPY mvnw ./
COPY .mvn ./.mvn

# Download all dependencies to the Maven cache
RUN ./mvnw dependency:go-offline

# Copy the actual project files
COPY src ./src

# Build the application
RUN ./mvnw clean package -DskipTests

# Use a lightweight JDK image to run the application
FROM eclipse-temurin:21-jre

# Set working directory inside the container
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/target/bookhive-0.0.1-SNAPSHOT.jar ./app.jar

# Expose the port that the application runs on
EXPOSE 8686

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
