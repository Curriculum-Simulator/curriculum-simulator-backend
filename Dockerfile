FROM openjdk:15-jdk-alpine as builder
MAINTAINER Ayoub Lahyaoui
WORKDIR /app
COPY . .
RUN ./mvnw clean package

FROM openjdk:15-jdk-alpine
WORKDIR /app
COPY --from=builder /app/target/curriculum-0.0.1-SNAPSHOT.jar curriculum.jar
ENTRYPOINT ["java","-jar","/app/curriculum.jar"]
