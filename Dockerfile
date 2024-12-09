FROM ubuntu:latest AS build

RUN apt-get update
RUN apt-get install openjdk-17-jdk -y
COPY . .

RUN apt-get install maven -y
RUN mvn clean install -DskipTests

FROM openjdk:17-slim

EXPOSE 8080

COPY --from=build /target/github-gateway-0.0.1.jar app.jar

CMD [ "java", "-jar", "app.jar" ]
