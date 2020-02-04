package com.netapp.monitoring;

public class VmWare {
    String id;
    String ip;
    String name;
    Boolean firewall;
    String path;

    public VmWare(){

    }

    public VmWare(String id, String ip, String name, Boolean firewall, String path) {
        this.id = id;
        this.ip = ip;
        this.name = name;
        this.firewall = firewall;
        this.path = path;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getFirewall() {
        return firewall;
    }

    public void setFirewall(Boolean firewall) {
        this.firewall = firewall;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
