# syntax=docker/dockerfile:1.4


# Build stage for the main application
FROM --platform=$BUILDPLATFORM maven:3.9.6-eclipse-temurin-21 AS builder
WORKDIR /workdir/server

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src

RUN mvn install

# Development environment stage
FROM builder AS dev-envs
RUN apt-get update && apt-get install -y --no-install-recommends git

RUN useradd -s /bin/bash -m vscode && groupadd docker && usermod -aG docker vscode

# Install Docker tools (cli, buildx, compose)
COPY --from=gloursdocker/docker / /
CMD ["mvn", "spring-boot:run"]

# Prepare production stage
FROM builder AS prepare-production
RUN mkdir -p target/dependency
WORKDIR /workdir/server/target/dependency
RUN jar -xf ../*.jar

# Final stage
FROM eclipse-temurin:21-alpine

EXPOSE 8070
VOLUME /tmp
ARG DEPENDENCY=/workdir/server/target/dependency
COPY --from=prepare-production ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=prepare-production ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=prepare-production ${DEPENDENCY}/BOOT-INF/classes /app
CMD ["sleep", "infinity"]

ENTRYPOINT ["java","-cp","app:app/lib/*","com.example.demo.ProductServiceApplication"]
