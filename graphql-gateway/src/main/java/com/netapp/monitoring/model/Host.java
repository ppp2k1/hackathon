package com.netapp.monitoring.model;

public class Host {
    private String ipAdd;
    private String firewallOn;
    private String firewallLoaded;
    private String firewallDefaultAction;

    public Host() {
    }

    public String getIpAdd() {
        return ipAdd;
    }

    public void setIpAdd(String ipAdd) {
        this.ipAdd = ipAdd;
    }

    public String getFirewallOn() {
        return firewallOn;
    }

    public void setFirewallOn(String firewallOn) {
        this.firewallOn = firewallOn;
    }

    public String getFirewallLoaded() {
        return firewallLoaded;
    }

    public void setFirewallLoaded(String firewallLoaded) {
        this.firewallLoaded = firewallLoaded;
    }

    public String getFirewallDefaultAction() {
        return firewallDefaultAction;
    }

    public void setFirewallDefaultAction(String firewallDefaultAction) {
        this.firewallDefaultAction = firewallDefaultAction;
    }
}
