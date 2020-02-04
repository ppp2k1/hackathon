FROM centos
RUN yum -y install java
ADD ./build/libs/*.jar /.
EXPOSE 7777
ENTRYPOINT java -jar graphql-gateway-1.0-SNAPSHOT.jar