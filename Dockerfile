# Build stage
FROM eclipse-temurin:21-jdk AS build

WORKDIR /app
COPY gradlew ./
COPY gradle ./gradle
COPY build.gradle.kts settings.gradle.kts gradle.properties ./
COPY src ./src

RUN chmod +x gradlew && ./gradlew shadowJar --no-daemon

# Run stage
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app
COPY --from=build /app/build/libs/burritobot.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
