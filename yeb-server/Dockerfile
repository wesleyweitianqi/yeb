FROM Java:8
MAINTAINER wesley
VOLUME /tmp
ADD yeb-server-0.0.1-SNAPSHOT.jar yeb-server
RUN bash -c "touch /yeb-server.jar"
ENTRYPOINT ["java", "Djava.security.egd=file:/dev/./urandom","-jar","/yeb-server.jar"]
EXPOSE 8083