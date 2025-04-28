/**
 * Build configuration for the Inventory Service module.
 * This module handles inventory management for the Eltiketi platform.
 */

plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.spring)
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.spring.dependency) // Spring Dependency Management plugin for BOM support
    alias(libs.plugins.kotlin.jpa)        // Kotlin JPA plugin for JPA entity support
}

// Project coordinates
group = "org.tamzi.eltiketi"  // Group ID for Maven coordinates
version = "0.0.1"             // Project version

// Configure Java toolchain to ensure consistent Java version across environments
java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)  // Use Java 21
    }
}

// Configure dependency configurations
configurations {
    compileOnly {
        // Make annotationProcessor dependencies available to compileOnly configuration
        extendsFrom(configurations.annotationProcessor.get())
    }
}

// Configure repositories for dependency resolution
repositories {
    mavenCentral()  // Use Maven Central repository
}

// Declare dependencies
dependencies {
    // Implementation dependencies - available at compile time and runtime
    implementation(libs.boot.jpa)             // Spring Data JPA for database access
    implementation(libs.boot.web)             // Spring Web for REST API support
    implementation(libs.boot.actuator)        // Spring Actuator for monitoring and metrics
    implementation(libs.jackson.module.kotlin) // Jackson Kotlin module for JSON serialization
    implementation(libs.kotlin.reflect)       // Kotlin reflection for runtime type information
    implementation(libs.flyway.core)          // Flyway for database migrations
    implementation(libs.flyway.mysql)         // Flyway MySQL support
    
    // Compile-only dependencies - only needed at compile time
    compileOnly(libs.lombok)                  // Lombok for reducing boilerplate code
    
    // Runtime dependencies - only needed at runtime
    runtimeOnly(libs.mysql.connector)         // MySQL JDBC driver
    
    // Annotation processors - process annotations during compilation
    annotationProcessor(libs.lombok)          // Lombok annotation processor
    
    // Test dependencies - only used for testing
    testImplementation(libs.boot.test)        // Spring Boot Test for integration testing
    testImplementation(libs.kotlin.test.junit5) // Kotlin JUnit 5 integration
    testImplementation(libs.h2)               // H2 in-memory database for testing
    testRuntimeOnly(libs.junit.platform.launcher) // JUnit Platform Launcher for IDE support
}

// Configure Kotlin compiler options
kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")  // Strict null safety for JSR-305 annotations
    }
}

// Configure Kotlin all-open plugin for JPA entities
allOpen {
    // Make these annotations trigger all-open behavior
    annotation("jakarta.persistence.Entity")         // JPA entities
    annotation("jakarta.persistence.MappedSuperclass") // JPA mapped superclasses
    annotation("jakarta.persistence.Embeddable")     // JPA embeddables
}

// Configure test task to use JUnit Platform
tasks.withType<Test> {
    useJUnitPlatform()  // Use JUnit 5 for testing
}

// Configure dependency management for consistent dependency versions
dependencyManagement {
    imports {
        // Import Spring Boot BOM (Bill of Materials)
        mavenBom("org.springframework.boot:spring-boot-dependencies:${libs.versions.spring.boot.get()}")
    }
}
