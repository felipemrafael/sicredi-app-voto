FROM maven:3.8.4-openjdk-11-slim AS build

WORKDIR /app

COPY pom.xml .

COPY src ./src

RUN mvn clean package -DskipTests

FROM openjdk:11-jre-slim

WORKDIR /app

COPY --from=build /app/target/desafio-sicredi-pauta.jar .

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "desafio-sicredi-pauta.jar"]