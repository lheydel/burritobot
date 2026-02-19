# Build stage
FROM gradle:8.5-jdk21 AS build

WORKDIR /app
COPY build.gradle.kts settings.gradle.kts gradle.properties ./
COPY src ./src

RUN gradle shadowJar --no-daemon

# Run stage
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app
COPY --from=build /app/build/libs/burritobot.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
