package com.netapp.security.vm;

public class Datastore {

    private String type;
    private String name;
    private String url;
    private String remoteHost;
    private String remotePath;

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getRemoteHost() {
        return remoteHost;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Datastore(String type, String name, String url, String remoteHost, String remotePath) {
        this.type = type;
        this.name = name;
        this.url = url;
        this.remoteHost = remoteHost;
        this.remotePath = remotePath;
    }

    public void setRemoteHost(String remoteHost) {
        this.remoteHost = remoteHost;
    }

    public void setRemotePath(String remotePath) {
        this.remotePath = remotePath;
    }

    public String getRemotePath() {
        return remotePath;
    }



}
