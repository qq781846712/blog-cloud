FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER Blank

RUN mkdir -p /opt/gateway/logs  \
    /opt/gateway/temp  \
    /opt/skywalking/agent

WORKDIR /opt/gateway

ENV SERVER_PORT=8080

EXPOSE ${SERVER_PORT}

ADD ./target/gateway.jar ./app.jar

ENTRYPOINT ["java", \
            "-Djava.security.egd=file:/dev/./urandom", \
            "-Dserver.port=${SERVER_PORT}", \
#            "-Dskywalking.agent.service_name=gateway", \
#            "-javaagent:/opt/skywalking/agent/skywalking-agent.jar", \
            "-jar", "app.jar"]
