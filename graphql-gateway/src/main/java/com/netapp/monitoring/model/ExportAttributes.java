package com.netapp.monitoring.model;

public class ExportAttributes {
    private String policy;

    public ExportAttributes(String policy) {
        this.policy = policy;
    }

    public String getPolicy() {
        return policy;
    }

    public void setPolicy(String policy) {
        this.policy = policy;
    }
}
