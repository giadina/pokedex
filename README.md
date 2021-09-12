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
6. To see your application health enter url `http://localhost:8081/healthcheck`

### Docker

The application can be dockerized also. If you want to run it this way, you will need docker running on your laptop.
1. `cd pokedex-api`
2. build the image running `docker build -t pokedex-api .`
3. run the image with `docker run –p 8080:8080 xxxx`, where xxx=container_id generated from step 1 or the image tag
(pokedex-api in this case)
4. test the application calling the container ip and port 8080. You can look for the host ip with
`docker exec container_id cat /etc/hosts`.


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

Swagger
---

The endpoints are documented using Swagger.  
You can check it here: `http://localhost:8080/swagger.json`

If you want to play with them use [Swagger UI](http://localhost:8080/swagger-ui/):
- in the search box type `/swagger.json`
- you will see the two available endpoints (e.g. `http://localhost:8080/swagger-ui/#/pokemon/retrieveBasicPokemonInfo`)

If you want to download the yaml file go to `http://localhost:8080/swagger.yaml`
