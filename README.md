# eltiketi

Ticket booking system with Kotlin spring boot and microservices


### Gradle commands:
 Because we are caching, be sure to run the clean command first to fix any caching issues.

* Run `./gradlew clean` to clean all build outputs.
* Run `./gradlew bootRun` to run the application.
* Run `./gradlew build` to only build the application.
* Run `./gradlew check` to run all checks, including tests.
* Run `./gradlew --refresh-dependencies` force a refresh of dependencies.
* Run `./gradlew clean build --refresh-dependencies` to clean, build, and refresh dependencies.


To run a specific service:
* Run `./gradlew :inventoryservice:bootRun` to run the inventory service

