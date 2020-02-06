FROM centos
RUN yum -y install java
ADD ./build/libs/*.jar /.
EXPOSE 5555
ENTRYPOINT java -jar ontap-service-0.0.1-SNAPSHOT.jar
