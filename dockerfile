FROM openjdk:8-jdk-alpine
ARG JAR_FILE=build/libs/*.jar
ENTRYPOINT ["java","-jar","/alchomist-0.0.1-SNAPSHOT.jar"]
