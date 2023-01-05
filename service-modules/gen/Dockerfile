FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER Blank

RUN mkdir -p /opt/gen/logs

WORKDIR /opt/gen

ENV SERVER_PORT=9202

EXPOSE ${SERVER_PORT}

ADD ./target/gen.jar ./app.jar

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-Dserver.port=${SERVER_PORT}", "-jar", "app.jar"]
