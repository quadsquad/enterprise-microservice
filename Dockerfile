FROM openjdk:8
EXPOSE 8098
ADD target/EntrepriseMicroService.jar EntrepriseMicroService.jar
ENTRYPOINT ["java", "-jar", "EntrepriseMicroService.jar"]