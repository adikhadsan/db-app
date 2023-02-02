FROM tomcat:9
WORKDIR /usr/local/tomcat/webapps/
#COPY ./demo-0.0.1-SNAPSHOT.war .
ADD ./*.war ./demo.war
#EXPOSE 8081
