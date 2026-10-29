# Architecture overview

GreenCorridor uses a **modular monolith** with a separate SUMO simulation engine and independent clients (React dashboard, Flutter driver app).

## Principles

- Do not start with microservices; split modules later only if justified.
- SUMO is the source of simulated vehicle movement and traffic-signal behavior.
- The Spring Boot backend coordinates emergency priorities and talks to SUMO through a dedicated adapter.
- Clients fetch live data from the backend; no hardcoded operational statistics.

## Communication

1. **REST** — normal CRUD and trip lifecycle operations
2. **WebSocket** — live ambulance positions and signal states
3. **SUMO adapter** — simulation control and validated priority commands

Priority requests must be validated against simulation safety rules. On routing or simulation failure, report the failure and use only a defined, tested fallback.
