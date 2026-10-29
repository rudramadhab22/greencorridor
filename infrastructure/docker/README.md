# Docker

Root compose stack: `docker-compose.yml`

## Services

| Service | Port | Purpose |
| --- | --- | --- |
| `backend` | `8080` | Spring Boot API |
| `frontend` | `5173` | React + Vite dashboard (dev server) |

- **Database:** your **local MySQL** on the host (`DB_HOST` / `DB_PORT` in `.env`). No MySQL Docker image.
- **Mobile (Flutter):** not part of this stack.

Backend reaches local MySQL via `host.docker.internal` (mapped to the host gateway on Linux).

## Run

Ensure local MySQL is running and `.env` has your DB credentials, then:

```bash
cp .env.example .env
# edit .env — use your local MySQL user/password

docker compose up --build
```

- API: http://localhost:8080  
- Swagger: http://localhost:8080/swagger-ui.html  
- Dashboard: http://localhost:5173  

Stop:

```bash
docker compose down
```

Dockerfiles:

- `Dockerfile.backend`
- `Dockerfile.frontend`
