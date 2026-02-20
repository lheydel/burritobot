# Build stage
FROM eclipse-temurin:21-jdk AS build

WORKDIR /app

# Copy gradle wrapper first (cached unless wrapper changes)
COPY gradlew ./
COPY gradle ./gradle
RUN chmod +x gradlew

# Copy build files and download dependencies (cached unless deps change)
COPY build.gradle.kts settings.gradle.kts gradle.properties ./
RUN ./gradlew dependencies --no-daemon

# Copy source and build (only this runs on code changes)
COPY src ./src
RUN ./gradlew shadowJar --no-daemon

# Run stage
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app
COPY --from=build /app/build/libs/burritobot.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
