FROM openjdk:17

WORKDIR /app

COPY target/cloud-file-storage-0.0.1-SNAPSHOT.jar .

EXPOSE 8080

CMD ["java", "-jar", "cloud-file-storage-0.0.1-SNAPSHOT.jar"]
