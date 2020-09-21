FROM openjdk:8u212-jdk-alpine
RUN apk update
RUN apk add curl
RUN apk add busybox-extras
RUN apk add tini
RUN apk add tcpdump

COPY ./localtime /etc/localtime
RUN echo 'Asia/Shanghai' >/etc/timezone

COPY --from=hengyunabc/arthas:latest /opt/arthas /opt/arthas
ENTRYPOINT ["tini"]

VOLUME /tmp
ADD {{ project }}-web/target/{{ project }}-web-1.0-SNAPSHOT.jar /opt/app.jar
EXPOSE 8080
EXPOSE 18080
CMD ["/bin/sh", "-c", "/usr/bin/java $JAVA_OPTS -jar /opt/app.jar"]