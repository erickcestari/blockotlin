FROM gradle:8.10.2-jdk23 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle shadowJar --no-daemon

FROM openjdk:23-jdk-oracle
EXPOSE 8081:8081
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/*all.jar /app/blockotlin.jar
ENTRYPOINT ["java","-jar","/app/blockotlin.jar"]