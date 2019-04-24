FROM openjdk:11-jre-slim
EXPOSE 8080
ARG JAR_FILE=target/zzpj-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} zzpj.jar
ENTRYPOINT ["java", "-jar", "/zzpj.jar"]