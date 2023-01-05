FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER Blank

RUN mkdir -p /opt/seata-server/logs \
    /opt/skywalking/agent

WORKDIR /opt/seata-server

ENV TZ=PRC
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

EXPOSE 7091
EXPOSE 8091

ADD ./target/seata-server.jar ./app.jar

ENTRYPOINT ["java", \
            "-Djava.security.egd=file:/dev/./urandom", \
#            "-Dskywalking.agent.service_name=seata-server", \
#            "-Dskywalking.plugin.seata.server=true", \
#            "-javaagent:/opt/skywalking/agent/skywalking-agent.jar", \
            "-jar", "app.jar"]
