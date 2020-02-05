package com.netapp.monitoring;

public class SecurityUnixAttributes {
    private String groupId;
    private long permissions;
    private String userId;

    public SecurityUnixAttributes() {
    }

    public SecurityUnixAttributes(String groupId, long permissions, String userId) {
        this.groupId = groupId;
        this.permissions = permissions;
        this.userId = userId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public long getPermissions() {
        return permissions;
    }

    public void setPermissions(long permissions) {
        this.permissions = permissions;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
