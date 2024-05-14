FROM maven:3-openjdk-17 AS build
COPY . .
RUN maven clean package -DskilTests

FROM openjdk:17.0.1-jdk-slim
COPY --from=build /target/clothify-1.0.0-SNAPSHOT.jar clothify.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","clothify.jar"]