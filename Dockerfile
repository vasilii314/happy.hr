FROM gradle:7-jdk11-alpine AS build
WORKDIR /opt/app/back
COPY . .
RUN chmod +x ./gradlew clean build

FROM openjdk:11.0-jre

ARG JAVA_PARAM
ENV JAVA_PARAM $JAVA_PARAM
WORKDIR /opt/app/back
COPY --from=build /opt/build/libs/happynes-0.0.1-SNAPSHOT.jar /opt/app/back/app.jar
RUN chown nobody -R /opt/*
USER 65534
EXPOSE 8080
CMD ["sh", "-c", "java ${JAVA_PARAM} -jar /opt/app/back/app.jar"]
