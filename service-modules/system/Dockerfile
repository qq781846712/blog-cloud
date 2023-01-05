FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER Blank

RUN mkdir -p /opt/system/logs \
    /opt/system/temp \
    /opt/skywalking/agent

WORKDIR /opt/system

ENV SERVER_PORT=9201

EXPOSE ${SERVER_PORT}

ADD ./target/system.jar ./app.jar

ENTRYPOINT ["java", \
            "-Djava.security.egd=file:/dev/./urandom", \
            "-Dserver.port=${SERVER_PORT}", \
#            "-Dskywalking.agent.service_name=system", \
#            "-javaagent:/opt/skywalking/agent/skywalking-agent.jar", \
            "-jar", "app.jar"]
