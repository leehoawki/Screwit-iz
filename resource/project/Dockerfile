FROM frolvlad/alpine-oraclejdk8:slim
ADD ./{{ project }}-api/target/{{ project }}-api-1.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
EXPOSE 8080