FROM centos
RUN yum -y install java
ADD ./build/libs/*.jar /.
EXPOSE 9999
ENTRYPOINT java -jar vmware-service-1.0-SNAPSHOT.jar