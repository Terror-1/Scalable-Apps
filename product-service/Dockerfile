FROM openjdk:18
WORKDIR /app
COPY ./target/product-service-0.0.1-SNAPSHOT.jar /app
COPY src/main/resources/test.jpg /app

EXPOSE 8070
CMD ["java", "-jar", "product-service-0.0.1-SNAPSHOT.jar"]