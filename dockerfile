FROM adoptopenjdk/openjdk11:alpine
COPY . /hortifruit
WORKDIR /hortifruit

RUN apk add maven
RUN mvn clean package

ENTRYPOINT [ "java", "-jar", "/hortifruit/target/hortfruit-online-0.0.1-SNAPSHOT.jar"]

EXPOSE 8080