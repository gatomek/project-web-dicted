FROM eclipse-temurin:17-jdk-alpine
COPY target/web-dicted-1.0.0.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]
