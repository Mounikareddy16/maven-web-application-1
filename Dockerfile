FROM tomcat:11.0.18-jdk25-temurin-noble
COPY target/maven-web-application.war /usr/local/tomcat/webapps
