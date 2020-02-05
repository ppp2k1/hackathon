package com.netapp.monitoring;

public class VmWare {
    Long id;
    String vmName;
    String ipAddress;
    String firewallOn;
    //String path;

    public VmWare(){

    }

    public VmWare(Long id, String ipAddress, String name, String firewall, String path) {
        this.id = id;
        this.ipAddress = ipAddress;
        this.vmName = name;
        this.firewallOn = firewall;
        //this.path = path;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getVmName() {
        return vmName;
    }

    public void setVmName(String name) {
        this.vmName = name;
    }

    public String getFirewallOn() {
        return firewallOn;
    }

    public void setFirewallOn(String firewallOn) {
        this.firewallOn = firewallOn;
    }

    /*public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
*/}
