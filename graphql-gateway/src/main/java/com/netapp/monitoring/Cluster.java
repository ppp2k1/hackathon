package com.netapp.monitoring;

import java.util.List;

public class Cluster {
    private String name;
    private String key;
    private List<Vserver> vservers;

    public Cluster() {
    }

    public Cluster(String name, String key, List<Vserver> vservers) {
        this.name = name;
        this.key = key;
        this.vservers = vservers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<Vserver> getVservers() {
        return vservers;
    }

    public void setVservers(List<Vserver> vservers) {
        this.vservers = vservers;
    }
}
