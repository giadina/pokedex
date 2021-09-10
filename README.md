# Pokedex REST API service

This repository contains REST endpoints to fetch funny Pokemon information

How to start the Pokedex REST API service application
---

1. `cd pokedex-api`
2. Run `mvn clean install` to build your application
3. Start application with `java -jar target/pokedex-api-1.0.0.jar server config.yml`
4. To check that your application is running enter url `http://localhost:8080`

Health Check
---

To see your applications health enter url `http://localhost:8081/healthcheck`
