package com.netapp.security.vm;

public class Host {
    private String ipAdd;
    private String firewall_on;
    private String firewall_loaded;
    private String firewall_default_action;

    public String getIpAdd() {
        return ipAdd;
    }

    public void setIpAdd(String ipAdd) {
        this.ipAdd = ipAdd;
    }

    public void setFirewall_on(String firewall_on) {
        this.firewall_on = firewall_on;
    }

    public void setFirewall_loaded(String firewall_loaded) {
        this.firewall_loaded = firewall_loaded;
    }

    public String getFirewall_on() {
        return firewall_on;
    }

    public void setFirewall_default_action(String firewall_default_action) {
        this.firewall_default_action = firewall_default_action;
    }

    public String getFirewall_default_action() {
        return firewall_default_action;
    }

    public Host(String ipAdd, String firewall_on, String firewall_loaded, String firewall_default_action) {
        this.ipAdd = ipAdd;
        this.firewall_on = firewall_on;
        this.firewall_loaded = firewall_loaded;
        this.firewall_default_action = firewall_default_action;
    }

    public String getFirewall_loaded() {
        return firewall_loaded;
    }

}
