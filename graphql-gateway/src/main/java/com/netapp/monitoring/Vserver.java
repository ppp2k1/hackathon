package com.netapp.monitoring;

import java.util.List;

public class Vserver {
    private String name;
    private String key;
    private List<Volume> volumes;

    public Vserver(String name, String key, List<Volume> volumes) {
        this.name = name;
        this.key = key;
        this.volumes = volumes;
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

    public List<Volume> getVolumes() {
        return volumes;
    }

    public void setVolumes(List<Volume> volumes) {
        this.volumes = volumes;
    }
}
