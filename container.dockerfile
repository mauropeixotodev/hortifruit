FROM adoptopenjdk/openjdk11:alpine

ARG SPRING_PROFILES_ACTIVE=dev

COPY . /opt/hortifruit

WORKDIR /opt/hortifruit

RUN apk add maven

RUN mvn clean package

ENTRYPOINT [ "java", "-Dspring.profiles.active=dev", "-jar",  "/opt/hortifruit/target/hortfruit-online-0.0.1-SNAPSHOT.jar"]

EXPOSE 8080