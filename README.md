# Subscription Management Service

## Requirements
- JDK 17
- Spring Boot 3.4.3
- Maven
- Docker and Docker Compose

## Configuration
Before running the application, you need to configure the `.env` file with environment variables.
Create an `.env` file in the project root with template from `.env.example` file.

## Running the application

### Docker

Linux

```bash
docker compose up -d
```

Windows

```bash
docker-compose up -d
```

## Testing

### Postman

You can use the Postman collection file `subscription.postman_collection.json` in `doc` folder to test the API.
