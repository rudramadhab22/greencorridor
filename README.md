# GreenCorridor

**GreenCorridor — Smart Emergency Traffic Coordination Platform**

> Every Second Matters. Every Signal Counts.

An open-source emergency traffic coordination platform that simulates intelligent traffic-signal prioritization to help ambulances reach hospitals faster.

> **Note:** The project initially operates entirely in a simulated environment (SUMO). No live traffic infrastructure is controlled.

## Architecture

Modular monolith with a separate simulation engine and independent clients:

| Layer | Technology |
| --- | --- |
| Backend | Java 21 + Spring Boot |
| Security | Spring Security + JWT |
| Persistence | Spring Data JPA + Hibernate |
| Database | MySQL 8 (local; credentials via `.env`) |
| Migrations | Flyway |
| Web dashboard | React + Vite |
| Mobile app | Flutter |
| Mapping | OpenStreetMap + Leaflet |
| Routing | OSRM |
| Traffic simulation | Eclipse SUMO |
| Real-time | Spring WebSocket |
| API docs | OpenAPI / Swagger |

```
                 GREENCORRIDOR PLATFORM
                          |
             +------------+------------+
             |                         |
             v                         v
     React Web Dashboard        Flutter Android App
     Admin / Operator /         Ambulance Driver
     Analyst
                       |
                       v
             Spring Boot Backend (Java 21)
                       |
      +----------------+----------------+
      |                |                |
 Authentication    Emergency       Traffic Coordination
 & Authorization   Management      & Route Management
                       |
             +---------+---------+
             |                   |
          MySQL +            WebSocket
                             Live Updates

       EXTERNAL / SUPPORTING ENGINES
       +----------------------------+
       | OSRM: Route Calculation    |
       | SUMO: Traffic Simulation   |
       | OpenStreetMap: Map Data    |
       +----------------------------+
```

## Repository layout

```
greencorridor/
├── backend/           # Spring Boot modular monolith
├── frontend/          # React + Vite operator dashboard
├── mobile/            # Flutter ambulance driver app
├── simulation/        # SUMO scenarios and configuration
├── infrastructure/    # Docker and database migrations
├── docs/              # Architecture, API, and testing docs
├── docker-compose.yml
└── README.md
```

### Backend modules

| Module | Responsibility |
| --- | --- |
| `auth` | Login, JWT authentication, role-based authorization |
| `user` | User administration and permissions |
| `ambulance` | Ambulance registration and assignment |
| `hospital` | Hospital records and locations |
| `emergency` | Start, monitor, update, and complete emergency trips |
| `routing` | Route calculation, destination changes, and ETA |
| `traffic` | Intersections, simulated signals, and priority rules |
| `simulation` | SUMO integration, scenarios, and simulation lifecycle |
| `analytics` | Journey comparisons, waiting time, and time saved |
| `notification` | Emergency alerts and important status notifications |
| `common` | Shared configuration, error handling, security, and auditing |

## Communication patterns

| Pattern | Technology | Example |
| --- | --- | --- |
| Normal requests | REST APIs | Register an ambulance or start an emergency |
| Live updates | WebSocket | Ambulance positions and simulated signal states |
| Simulation control | SUMO adapter | Advance simulation and apply validated priority commands |

## Getting started

```bash
# Clone
git clone https://github.com/rudramadhab22/greencorridor.git
cd greencorridor

# Local secrets (never commit .env)
cp .env.example .env
# edit .env with your values (DB + JWT_SECRET)

# Run backend + frontend with Docker against your local MySQL (mobile not included)
# Ensure local MySQL is running first
docker compose up --build
```

- Backend API: http://localhost:8080  
- Swagger UI: http://localhost:8080/swagger-ui.html  
- Frontend: http://localhost:5173  
- Database: local MySQL (not a Docker DB image)

See `infrastructure/docker/README.md` for details.

## License

MIT — see [LICENSE](LICENSE).
