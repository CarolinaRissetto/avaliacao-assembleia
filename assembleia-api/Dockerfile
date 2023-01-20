FROM maven:3.8.7-openjdk-18-slim AS build  
COPY src /usr/src/app/src   
COPY pom.xml /usr/src/app  
RUN mvn -q -f /usr/src/app/pom.xml clean package

FROM openjdk:18-slim-bullseye 
COPY --from=build /usr/src/app/target/assembleia-api-0.0.1-SNAPSHOT.jar /usr/app/assembleia-api-0.0.1-SNAPSHOT.jar  
EXPOSE 8080  
ENTRYPOINT ["java","-jar","/usr/app/assembleia-api-0.0.1-SNAPSHOT.jar"]  