# fulbito-api (Sprint 1)

## Requisitos
- Java 17
- Maven
- Docker

## Levantar MySQL local
```bash
docker compose up -d
mvn clean spring-boot:run -Dspring-boot.run.profiles=local
