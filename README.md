# Mini Java Backend Project: Study Task Tracker

This mini project is a simple **Task Tracker API** to help you practice Java + Spring Boot fundamentals. You'll build CRUD endpoints, validation, and basic in-memory storage.

## Learning goals
- Understand Spring Boot structure (controller, service, repository)
- Practice REST API design and validation
- Work with DTO mapping and domain models
- Explore error handling and HTTP status codes

## API overview
Base path: `/api/tasks`

| Method | Endpoint | Description |
| --- | --- | --- |
| POST | `/api/tasks` | Create a new task |
| GET | `/api/tasks` | List tasks |
| GET | `/api/tasks/{id}` | Fetch a task by id |
| PUT | `/api/tasks/{id}` | Update a task |
| DELETE | `/api/tasks/{id}` | Delete a task |

### Sample request
```bash
curl -X POST http://localhost:8080/api/tasks \
  -H "Content-Type: application/json" \
  -d '{"title": "Read Java docs", "description": "Practice streams", "status": "IN_PROGRESS"}'
```

## Run locally
```bash
mvn spring-boot:run
```

## Next enhancements (stretch goals)
- Add pagination and filtering (`status`, `createdAt`)
- Persist data with PostgreSQL or H2
- Add unit tests for the service layer
- Add Swagger/OpenAPI docs
- Add user accounts and authentication
