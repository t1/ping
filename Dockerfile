FROM openjdk:11
LABEL maintainer=https://github.com/t1 license=Apache-2.0 name='' build-date='' vendor=''

ENV JBOSS_HOME /opt/wildfly

USER root
RUN addgroup --system --gid 1000 wildfly \
    && adduser --system --home $JBOSS_HOME --uid 1000 -gid 1000 wildfly

USER wildfly
WORKDIR $JBOSS_HOME
#------------------------------------
ENV WILDFLY_VERSION 25.0.1.Final
#------------------------------------
RUN curl -L -O https://github.com/wildfly/wildfly/releases/download/$WILDFLY_VERSION/wildfly-$WILDFLY_VERSION.tar.gz && \
    tar xf wildfly-$WILDFLY_VERSION.tar.gz && \
    mv wildfly-$WILDFLY_VERSION/* . && \
    rm wildfly-$WILDFLY_VERSION.tar.gz

# Ensure signals are forwarded to the JVM process correctly for graceful shutdown
ENV LAUNCH_JBOSS_IN_BACKGROUND true

#      app  dbg  adm
EXPOSE 8080 8787 9990

ENTRYPOINT ["/opt/wildfly/bin/standalone.sh"]
CMD ["--debug", "--read-only-server-config=standalone.xml", "-b=0.0.0.0", "-bmanagement=0.0.0.0"]

COPY target/ping.war ${JBOSS_HOME}/standalone/deployments/