FROM jdk8/wildfly:10

ENV JAVA_HOME /usr/lib/jvm/java
ENV JRE_HOME /usr/lib/jvm/java/jre
ENV JBOSS_HOME /opt/jboss/wildfly
ENV DRIVER_DS postgresql-42.2.5.jar

ENV NAME_DS r34
ENV DB_HOST 172.17.0.1
ENV DB_NAME r34
ENV DB_USER r34
ENV DB_PASSWD 1234

USER root

COPY lib/${DRIVER_DS} /tmp/

RUN /bin/sh -c '$JBOSS_HOME/bin/standalone.sh &' && \
  sleep 2 && \
  cd /tmp && \
  $JBOSS_HOME/bin/jboss-cli.sh --connect --command="module add --name=org.postgresql --resources=/tmp/${DRIVER_DS} --dependencies=javax.api,javax.transaction.api" && \
  $JBOSS_HOME/bin/jboss-cli.sh --connect --command="/subsystem=datasources/jdbc-driver=postgresql:add(driver-name="postgresql",driver-module-name="org.postgresql",driver-class-name=org.postgresql.Driver)" && \
  $JBOSS_HOME/bin/jboss-cli.sh --connect --command="data-source add --jndi-name=java:jboss/datasources/$NAME_DS --name=$NAME_DS --connection-url=jdbc:postgresql://${DB_HOST}:5432/${DB_NAME} --min-pool-size=1 --max-pool-size=20 --idle-timeout-minutes=3 --driver-name=postgresql --user-name=${DB_USER} --password=${DB_PASSWD}" && \
  $JBOSS_HOME/bin/jboss-cli.sh --connect --command=:shutdown && \
  rm -rf $JBOSS_HOME/standalone/configuration/standalone_xml_history/ $JBOSS_HOME/standalone/log/* /tmp/*

EXPOSE 8080

COPY resistencia-ws/target/resistencia-ws-0.0.1-SNAPSHOT.war $JBOSS_HOME/standalone/deployments/

CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0", "-Djboss.http.port=8080"]


