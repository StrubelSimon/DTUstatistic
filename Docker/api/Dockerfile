FROM openjdk:22-jdk-slim

WORKDIR /app

COPY DTUstatistic.jar app.jar
COPY application.properties application.properties

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar", "--spring.config.location=file:/app/application.properties"]