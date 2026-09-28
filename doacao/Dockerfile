# FROM eclipse-temurin:21-jre

# COPY target/doacao-0.0.1-SNAPSHOT.jar doacao.jar

# EXPOSE 8083

# ENTRYPOINT ["java", "-jar", "/doacao.jar"]

FROM maven:3.9.10-eclipse-temurin-21-alpine

WORKDIR /doacao

COPY pom.xml .
COPY src ./src

RUN mvn clean install package 

EXPOSE 8083

ENTRYPOINT ["java", "-jar", "target/doacao-0.0.1-SNAPSHOT.jar"]