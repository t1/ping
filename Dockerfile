FROM rdohna/wildfly
LABEL maintainer=https://github.com/t1 license=Apache-2.0 name='' build-date='' vendor=''

COPY target/ping.war ${JBOSS_HOME}/standalone/deployments/
