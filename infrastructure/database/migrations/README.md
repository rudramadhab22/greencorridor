# Database migrations

Flyway migrations for MySQL 8.

Canonical runtime location for the Spring Boot app:

`backend/src/main/resources/db/migration/`

Keep mirrored copies or generation scripts here under `infrastructure/database/migrations` as needed for ops documentation.

## Planned core tables

| Table | Purpose |
| --- | --- |
| users | Accounts and user details |
| roles | User roles |
| user_roles | User-role assignments |
| ambulances | Ambulance registration and status |
| hospitals | Hospital details and geographic locations |
| emergency_trips | Trip lifecycle, origin, destination, and status |
| ambulance_locations | Time-stamped GPS locations |
| intersections | Simulated intersection locations and configuration |
| traffic_signals | Simulated signal configurations and states |
| signal_priority_events | Priority requests, decisions, and outcomes |
| simulation_scenarios | Configurable traffic scenarios |
| simulation_runs | Individual simulation executions |
| trip_metrics | Journey time, waiting time, stops, and comparisons |
| audit_logs | Security-sensitive and operational actions |
