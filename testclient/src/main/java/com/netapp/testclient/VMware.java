package com.netapp.testclient;

public class VMware {
	int id;
	String name;
	Cluster cluster;
	public VMware(int id, String name, Cluster cluster) {
		super();
		this.id = id;
		this.name = name;
		this.cluster = cluster;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Cluster getCluster() {
		return cluster;
	}
	public void setCluster(Cluster cluster) {
		this.cluster = cluster;
	}
}
