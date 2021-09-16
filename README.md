# Pokedex REST API service

This repository contains REST endpoints to fetch funny Pokemon information

Developer Setup Guide
---

- JDK 8+
- [Maven Setup](https://maven.apache.org/install.html)

When installing mvn make sure to `export PATH=/opt/apache-maven-3.8.2/bin:$PATH` (this is the location where your bin
folder lives).

This project is developed using Dropwizard framework

How to start the Pokedex REST API service application
---

1. Clone this repository
2. `cd pokedex-api`
3. Run `mvn clean install` to build your application
4. Start application with `java -jar target/pokedex-api-1.0.0.jar server config.yml`
5. Run `http://localhost:8080/pokemon/tangela` or the other endpoint provided in `PokemonResource.java`

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


Monitoring
---

The two endpoints are collecting several metrics that can be used to monitor the health of the system, and compute SLIs/SLOs
such as server side Latency and Availability in case of a production service.  
For simplicity, I'm printing those metrics in the console every minute, but if I had a Graphite instance, I would report
them there and plot dashboards in Grafana.

You will find these annotations on each endpoint:
- @Timed: this will instantiate a timer that will report how long it takes for the request to complete. 
If you check the console while running the app, the reports can be found under this path:  
  - **Timers**:
  ```
  com.pokedex.resources.PokemonResource.pokemon_info.basic.metrics
    count = 2
    mean rate = 0,03 calls/second
    1-minute rate = 0,02 calls/second
    5-minute rate = 0,01 calls/second
    15-minute rate = 0,00 calls/second
    min = 163,82 milliseconds
    max = 790,99 milliseconds
    mean = 390,37 milliseconds
    stddev = 301,27 milliseconds
    median = 163,82 milliseconds
    75% <= 790,99 milliseconds
    95% <= 790,99 milliseconds
    98% <= 790,99 milliseconds
    99% <= 790,99 milliseconds
    99.9% <= 790,99 milliseconds
  
  com.pokedex.resources.PokemonResource.pokemon_info.translated.metrics
    count = 1
    mean rate = 0,02 calls/second
    1-minute rate = 0,02 calls/second
    5-minute rate = 0,00 calls/second
    15-minute rate = 0,00 calls/second
    min = 656,63 milliseconds
    max = 656,63 milliseconds
    mean = 656,63 milliseconds
    stddev = 0,00 milliseconds
    median = 656,63 milliseconds
    75% <= 656,63 milliseconds
    95% <= 656,63 milliseconds
    98% <= 656,63 milliseconds
    99% <= 656,63 milliseconds
    99.9% <= 656,63 milliseconds
  ```

- @ResponseMetered: this will record all HTTP response codes, the metrics can be found under the **Meters** section and
will look like this:
  ```
  com.pokedex.resources.PokemonResource.pokemon_info.basic.http_responses.2xx-responses
    count = 2
    mean rate = 0,03 events/second
    1-minute rate = 0,02 events/second
    5-minute rate = 0,01 events/second
    15-minute rate = 0,00 events/second
  ```
... same for other HTTP codes.
  

