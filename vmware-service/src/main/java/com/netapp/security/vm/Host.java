package com.netapp.security.vm;

public class Host {
    private String ipAdd;
    private String firewallOn;
    private String firewallLoaded;
    private String firewallDefaultAction;

    public String getIpAdd() {
        return ipAdd;
    }

    public void setIpAdd(String ipAdd) {
        this.ipAdd = ipAdd;
    }

    public void setFirewallOn(String firewallOn) {
        this.firewallOn = firewallOn;
    }

    public void setFirewallLoaded(String firewallLoaded) {
        this.firewallLoaded = firewallLoaded;
    }

    public String getFirewallOn() {
        return firewallOn;
    }

    public void setFirewallDefaultAction(String firewallDefaultAction) {
        this.firewallDefaultAction = firewallDefaultAction;
    }

    public String getFirewallDefaultAction() {
        return firewallDefaultAction;
    }

    public Host(String ipAdd, String firewallOn, String firewallLoaded, String firewallDefaultAction) {
        this.ipAdd = ipAdd;
        this.firewallOn = firewallOn;
        this.firewallLoaded = firewallLoaded;
        this.firewallDefaultAction = firewallDefaultAction;
    }

    public String getFirewallLoaded() {
        return firewallLoaded;
    }

}
