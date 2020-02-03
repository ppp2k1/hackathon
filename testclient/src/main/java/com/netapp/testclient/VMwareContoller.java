package com.netapp.testclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class VMwareContoller {

	@Autowired
	RestTemplate restTemplate;
	
	@GetMapping("/vmware")
	public VMware getVMWare() {
		Cluster cluster = restTemplate.getForObject("http://ontap-service/cluster", Cluster.class);
		return new VMware(3,"HackthonVMware",cluster);
	}
}
