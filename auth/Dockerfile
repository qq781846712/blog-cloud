FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER Blank

RUN mkdir -p /opt/auth/logs  \
    /opt/auth/temp  \
    /opt/skywalking/agent

WORKDIR /opt/auth

ENV SERVER_PORT=9210

EXPOSE ${SERVER_PORT}

ADD ./target/auth.jar ./app.jar

ENTRYPOINT ["java", \
            "-Djava.security.egd=file:/dev/./urandom", \
            "-Dserver.port=${SERVER_PORT}", \
#            "-Dskywalking.agent.service_name=auth", \
#            "-javaagent:/opt/skywalking/agent/skywalking-agent.jar", \
            "-jar", "app.jar"]
