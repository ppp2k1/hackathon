package com.netapp.testservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClusterController {

	@GetMapping("/cluster")
	public Cluster getSingleCluster() {
		return new Cluster(5, "HackathonDemo");
	}
	
}
