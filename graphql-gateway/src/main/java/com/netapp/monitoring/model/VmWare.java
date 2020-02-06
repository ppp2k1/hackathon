package com.netapp.monitoring.model;

import java.util.List;

public class VmWare {
    Long id;
    String vmName;
    String ipAddress;
    public Host host;
    public Datastore datastore;
    public List<Vmdk> vmdk;

    public VmWare(){

    }

    public VmWare(Long id, String ipAddress, String name) {
        this.id = id;
        this.ipAddress = ipAddress;
        this.vmName = name;
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

    public Host getHost() {
        return host;
    }

    public void setHost(Host host) {
        this.host = host;
    }

    public Datastore getDatastore() {
        return datastore;
    }

    public void setDatastore(Datastore datastore) {
        this.datastore = datastore;
    }

    public List<Vmdk> getVmdk() {
        return vmdk;
    }

    public void setVmdk(List<Vmdk> vmdk) {
        this.vmdk = vmdk;
    }
}
