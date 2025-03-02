FROM maven:3.9.9-eclipse-temurin-17-alpine AS build
WORKDIR /app

COPY pom.xml .

RUN mvn dependency:go-offline

COPY src ./src/

RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

ENV DB_HOST=postgres \
    DB_PORT=5432 \
    DB_NAME=subscription \
    DB_USERNAME=postgres \
    DB_PASSWORD=postgres

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]