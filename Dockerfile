FROM openjdk:latest

COPY target/catererservice-0.0.1-SNAPSHOT.jar catererservice.jar

ENTRYPOINT ["java","-jar","catererservice.jar"]

EXPOSE 8080