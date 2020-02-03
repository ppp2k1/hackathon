FROM centos
RUN yum -y install java
ADD ./build/libs/*.jar /.
EXPOSE 8080
ENTRYPOINT java -jar graphql-gateway-0.0.1-SNAPSHOT.jar