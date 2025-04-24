rootProject.name = "eltiketi"

// Include subprojects here (e.g., "common", "admin-service", "api-gateway", etc.) for a multi-module mono repo project

listOf(
    "common",
    "config-service",
    "admin-service",
    "api-gateway",
    "inventory-service",
    "booking-service",
    "order-service",
    "notification-service"
).forEach { include(it) }

