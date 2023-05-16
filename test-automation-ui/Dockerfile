#FROM eclipse-temurin:17-jdk-jammy
#FROM selenium/node-chrome:beta
FROM markhobson/maven-chrome
WORKDIR ./practical-task-dilan-athukorala/task2/test-automation-task2

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
#RUN ./mvnw dependency:resolve

COPY src ./src
COPY resources ./resources
COPY screenshots ./screenshots

CMD ["./mvnw", "clean", "install"]
