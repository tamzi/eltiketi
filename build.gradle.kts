import org.jetbrains.kotlin.gradle.dsl.JvmTarget
plugins {
    id("java-base")
    kotlin("jvm") version "2.1.20" apply false
    kotlin("plugin.spring") version "2.1.20" apply false
    kotlin("plugin.jpa") version "2.1.20" apply false
    id("org.springframework.boot") version "3.4.4" apply false
    id("io.spring.dependency-management") version "1.1.7" apply false
}

group = "org.tamzi.eltiketi"
version = "0.0.1"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

allprojects {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")

    group = "org.tamzi.eltiketi"
    version = "0.0.1"

    // Apply Java toolchain settings from the root project
    apply(plugin = "java") // Applying java plugin to subprojects to enable Java compilation etc.
    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(21))
        }
    }


    tasks.withType<Test> {
        useJUnitPlatform()
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        compilerOptions {
            freeCompilerArgs.add("-Xjsr305=strict")
            jvmTarget.set(JvmTarget.JVM_21)
        }
    }
}
