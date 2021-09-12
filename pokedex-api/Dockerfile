FROM openjdk:8

COPY target/pokedex-api-1.0.0.jar /app/pokedex-api-1.0.0.jar
COPY config.yml /app/config.yml

WORKDIR /app

CMD ["java","-jar","pokedex-api-1.0.0.jar","server","config.yml"]

EXPOSE 8080