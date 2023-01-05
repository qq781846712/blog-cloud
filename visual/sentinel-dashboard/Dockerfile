FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER Blank

RUN mkdir -p /opt/sentinel-dashboard/logs \
    /opt/skywalking/agent

WORKDIR /opt/sentinel-dashboard

EXPOSE 8718

ADD ./target/sentinel-dashboard.jar ./app.jar

ENTRYPOINT ["java", \
            "-Djava.security.egd=file:/dev/./urandom", \
#            "-Dskywalking.agent.service_name=sentinel-dashboard", \
#            "-javaagent:/opt/skywalking/agent/skywalking-agent.jar", \
            "-jar", "app.jar"]
