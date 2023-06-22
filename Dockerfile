FROM openjdk:15-jdk-alpine
MAINTAINER Ayoub Lahyaoui
COPY target/curriculum-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
