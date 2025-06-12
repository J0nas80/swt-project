FROM maven:3.9.9-eclipse-temurin-24-alpine
WORKDIR /backend
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests
ARG JAR_FILE=./target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","./app.jar"]
