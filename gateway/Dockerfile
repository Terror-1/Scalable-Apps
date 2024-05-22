FROM openjdk:22
WORKDIR /app
COPY ./target/gateway-0.0.1-SNAPSHOT.jar /app
EXPOSE 8222
CMD ["java", "-jar", "gateway-0.0.1-SNAPSHOT.jar"]