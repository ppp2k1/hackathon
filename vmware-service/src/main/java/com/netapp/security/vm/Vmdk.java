package com.netapp.security.vm;

public class Vmdk {

    private String vmdkName;
    private String vmdkFilePath;
    private String datastoreName;

    public Vmdk(String vmdkName, String vmdkFilePath, String datastoreName) {
        this.vmdkName = vmdkName;
        this.vmdkFilePath = vmdkFilePath;
        this.datastoreName = datastoreName;
    }

    public String getVmdkName() {
        return vmdkName;
    }

    public String getVmdkFilePath() {
        return vmdkFilePath;
    }

    public String getDatastoreName() {
        return datastoreName;
    }

    public void setVmdkName(String vmdkName) {
        this.vmdkName = vmdkName;
    }

    public void setVmdkFilePath(String vmdkFilePath) {
        this.vmdkFilePath = vmdkFilePath;
    }

    public void setDatastoreName(String datastoreName) {
        this.datastoreName = datastoreName;
    }
}
