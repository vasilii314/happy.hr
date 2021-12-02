FROM openjdk:11-slim
ARG JAR_FILE=build/libs/*.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]