FROM eclipse-temurin:17-jdk-alpine
COPY target/project-flashcards-quick-web-saver-backend-0.0.1.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar", "-Dspring.profiles.active=postgres"]
