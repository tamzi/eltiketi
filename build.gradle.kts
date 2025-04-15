plugins {
    kotlin("jvm") version "1.9.25"
    kotlin("plugin.spring") version "1.9.25"
    id("org.springframework.boot") version "3.4.4"
    id("io.spring.dependency-management") version "1.1.7"
    kotlin("plugin.jpa") version "1.9.25"
}

group = "org.tamzi.eltiketi"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
        withSourcesJar()
        withJavadocJar()
    }
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

sourceSets {
    main {
        resources {
            srcDirs(listOf("$rootDir/config/server/", "$rootDir/config/logs/", "$rootDir/config/api-contract/"))
        }
    }
}

repositories {
    mavenCentral()
    gradlePluginPortal()
}


tasks.withType<Test> {
    useJUnitPlatform()
}
