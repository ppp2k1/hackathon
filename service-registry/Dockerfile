FROM centos
RUN yum -y install java
ADD ./build/libs/*.jar /.
EXPOSE 6666
ENTRYPOINT java -jar service-registry-1.0.0.jar