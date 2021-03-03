FROM openjdk:11
EXPOSE 8080
ADD target/FarmWorkSpringBoot-0.0.1-SNAPSHOT.war docker-demo.war
ENTRYPOINT ["java","-jar","docker-demo.war"]