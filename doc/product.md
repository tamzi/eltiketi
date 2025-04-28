# 🎟️ User Journey on elTiketi

### Step by step flow
**Step 1: User Initiates Purchase**
User opens the app and selects desired tickets (event, ticket type, quantity).

**Step 2: User Authenticates via Keycloak**
Client redirects user to Keycloak; user logs in.

→ Keycloak authenticates user and returns JWT.

**Step 3: Ticket Reservation Request**
User confirms reservation in UI.

→ App sends request (JWT, event details) to API Gateway.

**Step 4: API Gateway Validates JWT**
API Gateway verifies JWT via Keycloak (token introspection).

**Step 5: API Gateway Forwards Request to Booking Service**
API Gateway sends reservation request (user/event details) to Booking Service.

**Step 6: Booking Service Checks Inventory**
Booking Service queries Inventory Service for ticket availability.

**Step 7: Inventory Service Confirms Availability**
Inventory Service confirms available stock, returns confirmation to Booking Service.

**Step 8: Booking Service Temporarily Reserves Tickets**
Booking Service temporarily holds tickets (e.g., 15 min) in the database.

→ Emits a BookingCreated event to Kafka.

**Step 9: Client Initiates Payment**
User completes payment via integrated Payment Gateway (e.g., Stripe Checkout).

**Step 10: Payment Gateway Confirms Payment**
Payment Gateway confirms successful payment back to client.

**Step 11: Client Confirms Successful Payment to API Gateway**
Client app sends payment success confirmation to API Gateway.

**Step 12: API Gateway Forwards Order Request to Order Service**
API Gateway sends confirmation request (booking ID, user details) to Order Service.

**Step 13: Order Service Finalizes Order**
Order Service verifies reservation, marks order as CONFIRMED in database.

→ Emits an OrderCreated event to Kafka.

**Step 14: Inventory Service Adjusts Inventory**
Inventory Service listens to OrderCreated event from Kafka.

→ Updates available ticket count in database.

**Step 15: Notification Service Sends Confirmation**
Notification Service listens to OrderCreated event from Kafka.

→ Sends email/SMS notification with secure e-ticket (QR code) to user.

**Step 16: User Receives Ticket**
User receives confirmed e-ticket notification via email or SMS.



## Data Flow: Ticket Purchase


```mermaid
sequenceDiagram
    %% User Journey for Ticket Purchase
    %% This diagram illustrates the flow of events during a ticket purchase process in the elTiketi system.

    actor User
    participant ClientApp
    participant Keycloak
    participant API_Gateway
    participant BookingService
    participant InventoryService
    participant PaymentGateway
    participant OrderService
    participant Kafka
    participant NotificationService
    participant PostgreSQL

%% Step 1
    User->>ClientApp: 1. Select event tickets

%% Step 2
    ClientApp->>Keycloak: 2. Authenticate User
    Keycloak-->>ClientApp: JWT Token issued

%% Step 3
    ClientApp->>API_Gateway: 3. Request reservation (JWT)

%% Step 4
    API_Gateway->>Keycloak: 4. Validate JWT
    Keycloak-->>API_Gateway: JWT validation success

%% Step 5
    API_Gateway->>BookingService: 5. Forward reservation request

%% Step 6
    BookingService->>InventoryService: 6. Check ticket availability

%% Step 7
    InventoryService-->>BookingService: 7. Confirm availability

%% Step 8
    BookingService->>PostgreSQL: 8. Temporarily reserve tickets
    BookingService->>Kafka: Emit BookingCreated event

%% Step 9
    ClientApp->>PaymentGateway: 9. Initiate payment

%% Step 10
    PaymentGateway-->>ClientApp: 10. Payment success confirmation

%% Step 11
    ClientApp->>API_Gateway: 11. Confirm payment success

%% Step 12
    API_Gateway->>OrderService: 12. Forward order confirmation request

%% Step 13
    OrderService->>PostgreSQL: 13. Mark order CONFIRMED
    OrderService->>Kafka: Emit OrderCreated event

%% Step 14
    Kafka->>InventoryService: 14. Consume OrderCreated event
    InventoryService->>PostgreSQL: Update available ticket count

%% Step 15
    Kafka->>NotificationService: 15. Consume OrderCreated event
    NotificationService->>User: Send confirmation & QR code via Email/SMS

%% Step 16
    User->>User: 16. Receive confirmed e-ticket

```
