FROM openjdk:11-slim
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} /app.jar
WORKDIR /
ENTRYPOINT ["java", "-jar", "/app.jar"]
