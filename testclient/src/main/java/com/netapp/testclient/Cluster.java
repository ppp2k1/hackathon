package com.netapp.testclient;

public class Cluster {
	String name;
	int id;
	public Cluster() {
		this.name = "default";
		this.id = 1;
	}
	public Cluster(String name, int id) {
		super();
		this.name = name;
		this.id = id;
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
	
}
