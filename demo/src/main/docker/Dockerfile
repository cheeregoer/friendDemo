FROM openjdk:8-jdk-alpine
ENV LANG en_SG.UTF-8
ENV LANGUAGE en_SG:en
ENV LC_ALL en_SG.UTF-8

RUN apk add --no-cache --update curl
ADD demo-0.0.1-SNAPSHOT.jar app.jar

RUN sh -c 'touch /app.jar'
ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar
