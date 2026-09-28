# FROM eclipse-temurin:21-jre


# COPY target/ongs-0.0.1-SNAPSHOT.jar ongs.jar

# EXPOSE 8082

# ENTRYPOINT ["java", "-jar", "/ongs.jar"]

FROM maven:3.9.10-eclipse-temurin-21-alpine

WORKDIR /ongs

COPY pom.xml .
COPY src ./src

RUN mvn clean install package
# RUN mvn clean -U install -DskipTests

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "target/ongs-0.0.1-SNAPSHOT.jar"]
