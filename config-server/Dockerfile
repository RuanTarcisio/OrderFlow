# build
FROM maven:3.8.8-amazoncorretto-21-al2023 AS build
WORKDIR /build

COPY . .

RUN mvn clean package -DskipTests

# run
FROM amazoncorretto:21.0.5
WORKDIR /app

COPY --from=build ./build/target/*.jar ./config-server.jar

EXPOSE 8071
ENV TZ='America/Sao_Paulo'

ENTRYPOINT ["java", "-jar", "config-server.jar"]


