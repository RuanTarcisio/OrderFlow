# build
FROM maven:3.8.8-amazoncorretto-21-al2023 AS build
WORKDIR /build

COPY . .

RUN mvn clean package -DskipTests

# run
FROM amazoncorretto:21.0.5
WORKDIR /app

COPY --from=build ./build/target/*.jar ./eureka.jar

EXPOSE 8761

ENV TZ='America/Sao_Paulo'

ENTRYPOINT ["java", "-jar", "eureka.jar"]
