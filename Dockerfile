FROM maven:3.5.3-jdk-8 as maven
RUN mkdir --parents /usr/src/app
WORKDIR /usr/src/app

ADD pom.xml /usr/src/app/
RUN mvn verify clean --fail-never

ADD . /usr/src/app
RUN mvn install


FROM tomcat:9-jre8
ENV JAVA_OPTS -Dspring.profiles.active=docker
COPY --from=maven /usr/src/app/target/XVulB-*.war /usr/local/tomcat/webapps/ROOT.war
