FROM openjdk:11
EXPOSE 3000
ADD target/FarmWorkSpringBoot-0.0.1-SNAPSHOT.war docker-demo.war
ENTRYPOINT ["java","-jar","docker-demo.war"]