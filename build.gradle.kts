plugins {
    kotlin("jvm") version "2.3.10"
    kotlin("plugin.serialization") version "2.3.10"
    id("com.gradleup.shadow") version "9.0.1"
    application
}

group = "com.burritobot"
version = "2.0.0"

repositories {
    mavenCentral()
}

dependencies {
    // Kord - Discord
    implementation("dev.kord:kord-core:0.17.0")

    // Ktor - HTTP Client
    implementation("io.ktor:ktor-client-core:3.4.0")
    implementation("io.ktor:ktor-client-cio:3.4.0")
    implementation("io.ktor:ktor-client-content-negotiation:3.4.0")
    implementation("io.ktor:ktor-serialization-kotlinx-json:3.4.0")

    // Koin - DI
    implementation("io.insert-koin:koin-core:4.1.1")

    // Logging
    implementation("ch.qos.logback:logback-classic:1.5.32")

    // Testing
    testImplementation(kotlin("test"))
}

application {
    mainClass.set("com.burritobot.MainKt")
}

tasks.test {
    useJUnitPlatform()
}

tasks.shadowJar {
    archiveBaseName.set("burritobot")
    archiveClassifier.set("")
    archiveVersion.set("")
    mergeServiceFiles()
}

kotlin {
    jvmToolchain(21)
}
