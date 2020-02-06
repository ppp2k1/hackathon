package com.netapp.monitoring.model;

public class Vmdk {
    private String vmdkName;
    private String vmdkFilePath;
    private String datastoreName;

    public Vmdk() {
    }

    public String getVmdkName() {
        return vmdkName;
    }

    public void setVmdkName(String vmdkName) {
        this.vmdkName = vmdkName;
    }

    public String getVmdkFilePath() {
        return vmdkFilePath;
    }

    public void setVmdkFilePath(String vmdkFilePath) {
        this.vmdkFilePath = vmdkFilePath;
    }

    public String getDatastoreName() {
        return datastoreName;
    }

    public void setDatastoreName(String datastoreName) {
        this.datastoreName = datastoreName;
    }
}
