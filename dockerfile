FROM adoptopenjdk/openjdk11:alpine

ARG SPRING_PROFILES_ACTIVE=prod
ARG DATABASE_URL=jdbc:postgresql://hortifruits.com:5432/hortifruitdev
ARG DATABASE_USERNAME=hortifruitdev
ARG DATABASE_PASSWORD=WMrHQv9Y

ENV SPRING_PROFILES_ACTIVE=prod

COPY . /opt/hortifruit

WORKDIR /opt/hortifruit

RUN apk add maven

RUN mvn clean package

ENTRYPOINT [ "java", "-jar", "/opt/hortifruit/target/hortfruit-online-0.0.1-SNAPSHOT.jar"]

EXPOSE 8080