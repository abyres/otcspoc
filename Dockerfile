FROM anapsix/alpine-java:8

RUN mkdir -p /opt/abyres

COPY server/target/otcs-server-0.0.1-SNAPSHOT.jar /opt/abyres/

ENTRYPOINT ["/usr/bin/java"]
CMD ["-jar", "/opt/abyres/otcs-server-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080
