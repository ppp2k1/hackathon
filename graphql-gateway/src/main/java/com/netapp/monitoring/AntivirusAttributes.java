package com.netapp.monitoring;

public class AntivirusAttributes {
    private String onAccessPolicy;

    public AntivirusAttributes(String onAccessPolicy) {
        this.onAccessPolicy = onAccessPolicy;
    }

    public String getOnAccessPolicy() {
        return onAccessPolicy;
    }

    public void setOnAccessPolicy(String onAccessPolicy) {
        this.onAccessPolicy = onAccessPolicy;
    }
}
