FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy build artifact
COPY DTUstatistic.jar app.jar
COPY application.properties application.properties

# Expose application port
EXPOSE 8080

# Start the application
CMD ["java", "-jar", "app.jar", "--spring.config.location=file:application.properties"]