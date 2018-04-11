FROM maven:3.5.3-jdk-8 as maven
RUN mkdir --parents /usr/src/app
WORKDIR /usr/src/app

ADD pom.xml /usr/src/app/
RUN mvn verify clean --fail-never

ADD . /usr/src/app
RUN mvn install -Drun.jvmArguments="-Dspring.profiles.active=docker"


FROM java:8
COPY --from=maven /usr/src/app/target/XVulB-*.jar /opt/app.jar
CMD ["java","-jar","/opt/app.jar"]
