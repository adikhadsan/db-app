FROM tomcat:8.5.47-jdk8-openjdk
WORKDIR /usr/local/tomcat/webapps/
ARG file_name
RUN echo ${file_name}
COPY ./target/${file_name}*.war .
#COPY ./demo-0.0.1-SNAPSHOT.war .
#ADD ./*.war ./crud.war
#EXPOSE 8081
