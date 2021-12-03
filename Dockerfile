FROM openjdk:17-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} alchomist.jar
ENTRYPOINT ["java","-jar","/alchomist.jar"]
