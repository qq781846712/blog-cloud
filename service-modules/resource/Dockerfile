FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER Blank

RUN mkdir -p /opt/resource/logs \
    /opt/resource/temp \
    /opt/skywalking/agent

WORKDIR /opt/resource

ENV SERVER_PORT=9204

EXPOSE ${SERVER_PORT}

ADD ./target/resource.jar ./app.jar

ENTRYPOINT ["java", \
            "-Djava.security.egd=file:/dev/./urandom", \
            "-Dserver.port=${SERVER_PORT}", \
#            "-Dskywalking.agent.service_name=resource", \
#            "-javaagent:/opt/skywalking/agent/skywalking-agent.jar", \
            "-jar", "app.jar"]
