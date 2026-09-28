# FROM eclipse-temurin:21-jre

# COPY target/colaboradores-0.0.1-SNAPSHOT.jar colaboradores.jar

# EXPOSE 8081

# ENTRYPOINT ["java", "-jar", "/colaboradores.jar"]

FROM maven:3.9.10-eclipse-temurin-21-alpine

WORKDIR /colaboradores

COPY pom.xml .
COPY src ./src

RUN mvn clean install package 

# COPY target/colaboradores-0.0.1-SNAPSHOT.jar colaboradores.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "target/colaboradores-0.0.1-SNAPSHOT.jar"]