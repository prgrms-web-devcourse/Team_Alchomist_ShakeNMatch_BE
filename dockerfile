FROM openjdk:8-jdk-alpine
ARG JAR_FILE=build/libs/*.jar
ENTRYPOINT ["java","-jar","/actiontest.jar","address:8080"]
