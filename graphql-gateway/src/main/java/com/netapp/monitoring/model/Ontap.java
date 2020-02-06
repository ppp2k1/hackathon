package com.netapp.monitoring.model;

public class Ontap {

    private String ip;
    private Volume volume;
    private Vserver vserver;
    private Cluster cluster;

    public Ontap() {
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Volume getVolume() {
        return volume;
    }

    public void setVolume(Volume volume) {
        this.volume = volume;
    }

    public Vserver getVserver() {
        return vserver;
    }

    public void setVserver(Vserver vserver) {
        this.vserver = vserver;
    }

    public Cluster getCluster() {
        return cluster;
    }

    public void setCluster(Cluster cluster) {
        this.cluster = cluster;
    }
}
