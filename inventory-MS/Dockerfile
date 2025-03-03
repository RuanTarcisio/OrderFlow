# build
FROM maven:3.8.8-amazoncorretto-21-al2023 AS build
WORKDIR /build

COPY . .

RUN mvn clean package -DskipTests

# run
FROM amazoncorretto:21.0.5
WORKDIR /app

COPY --from=build ./build/target/*.jar ./inventory.jar

# Copia o OpenTelemetry Agent diretamente do repositório Maven
COPY --from=build /root/.m2/repository/io/opentelemetry/javaagent/opentelemetry-javaagent/2.11.0/opentelemetry-javaagent-2.11.0.jar /app/libs/opentelemetry-javaagent-2.11.0.jar

ENV SPRING_PROFILES_ACTIVE='default'
ENV TZ='America/Sao_Paulo'

ENTRYPOINT ["java", "-jar", "inventory.jar"]
