FROM adoptopenjdk/openjdk11:alpine

ARG SPRING_PROFILES_ACTIVE=prod

ENV DATABASE_URL=jdbc:postgresql://hortifruits.com:5432/hortifruitdev
ENV DATABASE_USERNAME=hortifruitdev
ENV DATABASE_PASSWORD=WMrHQv9Y
ENV SPRING_PROFILES_ACTIVE=prod

COPY . /opt/hortifruit

WORKDIR /opt/hortifruit

RUN apk add maven

RUN mvn clean package -Pprod

ENTRYPOINT [ "java", "-jar", "/opt/hortifruit/target/hortfruit-online-0.0.1-SNAPSHOT.jar"]

EXPOSE 8080