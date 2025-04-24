# eltiketi

Ticket booking system with Kotlin spring boot and microservices


# inventory Service
This service is responsible for managing the inventory of tickets. It provides endpoints for adding, updating, and deleting tickets.

# Booking Service
This service is responsible for handling ticket bookings. It provides endpoints for booking tickets and retrieving booking details.

# order Service
This service is responsible for managing the order of tickets. It provides endpoints for placing orders and retrieving order details.

# user Service
-> Will use Keycloak for authentication and authorization.


Gradle commands:
 Because we are caching, be sure to run the clean command first to fix any caching issues.

* Run `./gradlew clean` to clean all build outputs.
* Run `./gradlew bootRun` to run the application.
* Run `./gradlew build` to only build the application.
* Run `./gradlew check` to run all checks, including tests.


To run a specific service:
* Run `./gradlew :inventoryservice:bootRun` to run the inventory service

