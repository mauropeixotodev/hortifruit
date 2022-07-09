FROM openjdk:11-jdk-alpine

WORKDIR "/opt/hortifruit"

COPY . /opt/hortifruit

RUN apk add maven

WORKDIR /opt/hortifruit

RUN mvn clean package

CMD [ "java", "-Dspring.profiles.active=dev", "-jar",  "0.0.1-SNAPSHOT.jar"]
EXPOSE 8080