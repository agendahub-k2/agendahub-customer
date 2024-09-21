FROM openjdk:21-jdk-slim

WORKDIR /app

COPY build/libs/agendahub-customer-*.jar /app/agendahub-customer.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/agendahub-customer.jar"]
