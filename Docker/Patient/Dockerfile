FROM openjdk:17

RUN mkdir -p /app

ENV APP_NAME=SHHCSensorAPI-0.0.1-SNAPSHOT.jar

COPY ${APP_NAME} /app/${APP_NAME}

EXPOSE 8080

ENTRYPOINT [ "java", "-jar", "/app/SHHCSensorAPI-0.0.1-SNAPSHOT.jar" ]