FROM maven:3.8.2-adoptopenjdk-11 AS MAVEN_BUILD
COPY pom.xml /build/
COPY src /build/src/
WORKDIR /build/
RUN mvn package 

FROM adoptopenjdk/openjdk11:alpine-jre
WORKDIR /app
COPY --from=MAVEN_BUILD /build/target/performanalytics-api-1.0.jar /app/
ENTRYPOINT ["java", "-jar", "performanalytics-api-1.0.jar"]