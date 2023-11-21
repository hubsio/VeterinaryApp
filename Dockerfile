FROM openjdk:11-jre-slim
COPY /build/libs/veterinaryapp-0.0.1-SNAPSHOT.jar veterinaryapp-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/veterinaryapp-0.0.1-SNAPSHOT.jar"]