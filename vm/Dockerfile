FROM centos
RUN yum -y install java
ADD ./build/libs/*.jar /.
EXPOSE 9999
ENTRYPOINT java -jar vm-0.0.1-SNAPSHOT.jar