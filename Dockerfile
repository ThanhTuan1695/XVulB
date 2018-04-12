FROM maven:3.5.3-jdk-8 as maven
RUN mkdir --parents /usr/src/app
WORKDIR /usr/src/app

ADD pom.xml /usr/src/app/
RUN mvn verify clean --fail-never

ADD . /usr/src/app
RUN mvn install


FROM java:8
COPY --from=maven /usr/src/app/target/XVulB-*.war /opt/app.war
CMD ["java","-jar","-Dspring.profiles.active=docker","/opt/app.war"]
