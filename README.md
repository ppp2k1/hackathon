# hackathon

Order of initiaition of services:
service-registery -> testservice -> testclient

verify eureka:
Open Eureka monitor: http://<deployed_host_IP>:8761/

verify testservice(registery):
Run: curl http://<deployed_host_IP>:8081/cluster
Expected: {"id":5,"name":"HackathonDemo"}
&
there should be an entry called ONTAP-SERVICE in eureka UI.

verify testclient(discovery):
Run: curl http://<deployed_host_IP>:8082/vmware
Expected: {"id":3,"name":"HackthonVMware","cluster":{"name":"HackathonDemo","id":5}}
&
there should be an entry called VMWARE-SERVICE in eureka UI.


To make an existing service discoverable 
1) add dependency of eureka from testclient/build.gradle
2) copy properties from application.properties of testclient
3) Add RestTemplate part in case your service is consuming other service or to be lazy add it always.

For API gateway there can be some differences as per reading but for other bussiness services this should be fine.

**To build Docker Images** 
`gradlew.bat :buildDockerImages`

**To run Docker Image**s
`docker-compose up`

