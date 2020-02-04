package com.netapp.monitoring;

public class Ontap {

    String id;
    String ip;
    String path;
    Boolean hardwareEncrypted;
    Boolean softwareEncrypted;

    public Ontap() {
    }

    public Ontap(String id, String ip, String path, Boolean hardwareEncrypted, Boolean softwareEncrypted) {
        this.id = id;
        this.ip = ip;
        this.path = path;
        this.hardwareEncrypted = hardwareEncrypted;
        this.softwareEncrypted = softwareEncrypted;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Boolean getHardwareEncrypted() {
        return hardwareEncrypted;
    }

    public void setHardwareEncrypted(Boolean hardwareEncrypted) {
        this.hardwareEncrypted = hardwareEncrypted;
    }

    public Boolean getSoftwareEncrypted() {
        return softwareEncrypted;
    }

    public void setSoftwareEncrypted(Boolean softwareEncrypted) {
        this.softwareEncrypted = softwareEncrypted;
    }
}
