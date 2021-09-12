# Pokedex REST API service

This repository contains REST endpoints to fetch funny Pokemon information

Developer Setup Guide
---

- JDK 8+
- [Maven Setup](https://maven.apache.org/install.html)

When installing mvn make sure to `export PATH=/opt/apache-maven-3.8.2/bin:$PATH` (this is the location where your bin
folder lives)

How to start the Pokedex REST API service application
---

1. Clone this repository
2. `cd pokedex-api`
3. Run `mvn clean install` to build your application
4. Start application with `java -jar target/pokedex-api-1.0.0.jar server config.yml`
5. Run `http://localhost:8080/pokemon/tangela` or the other endpoint provided in `PokemonResource.java`
6. To see your applications health enter url `http://localhost:8081/healthcheck`

Project Structure
---
The project is organized following this structure:

- **models**: Representations of the data model such us `PokemonInfo`.
- **services**: Domain implementation; here is where the business logic is defined.
- **resources**: API endpoints can be found here; each endpoint is executed on a resource, in this case `PokemonResource`.
- **client**: Client code that accesses external HTTP services such us translations API and PokeAPI.
- **health**: Health Checks
- **MainApplication**: The application class.
- **MainConfiguration**: The configuration class.
