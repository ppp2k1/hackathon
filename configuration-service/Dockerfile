FROM centos
RUN yum -y install java
ADD ./build/libs/*.jar /.
EXPOSE 8888
ENTRYPOINT java -jar configuration-service-1.0.0.jar