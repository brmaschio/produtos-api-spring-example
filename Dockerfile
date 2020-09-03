# Gerar JAR
FROM maven:3.6.3-jdk-8 as maven

WORKDIR /usr/app

COPY ./pom.xml ./pom.xml
RUN mvn dependency:go-offline -B

COPY ./src ./src
ADD src/main/resources/application-prod.properties src/main/resources/application.properties

RUN mvn clean
RUN mvn install -DskipTests

# Execultar Api
FROM openjdk:8u171-jre-alpine

RUN apk add bash mysql-client

WORKDIR /usr/app

COPY --from=maven /usr/app/target/produtos-api.jar ./

