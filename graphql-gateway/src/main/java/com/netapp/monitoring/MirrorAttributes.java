package com.netapp.monitoring;

public class MirrorAttributes {
    private boolean dataProtectionMirror;
    private boolean loadSharingMirror;
    private boolean moveMirror;
    private boolean replicaVolume;
    private String snapmirrorSource;
    private boolean mirrorTransferInProgress;
    private long redirectSnapshotId;

    public MirrorAttributes() {
    }

    public MirrorAttributes(boolean dataProtectionMirror, boolean loadSharingMirror, boolean moveMirror, boolean replicaVolume,
                            String snapmirrorSource, boolean mirrorTransferInProgress, long redirectSnapshotId) {
        this.dataProtectionMirror = dataProtectionMirror;
        this.loadSharingMirror = loadSharingMirror;
        this.moveMirror = moveMirror;
        this.replicaVolume = replicaVolume;
        this.snapmirrorSource = snapmirrorSource;
        this.mirrorTransferInProgress = mirrorTransferInProgress;
        this.redirectSnapshotId = redirectSnapshotId;
    }

    public boolean isDataProtectionMirror() {
        return dataProtectionMirror;
    }

    public void setDataProtectionMirror(boolean dataProtectionMirror) {
        this.dataProtectionMirror = dataProtectionMirror;
    }

    public boolean isLoadSharingMirror() {
        return loadSharingMirror;
    }

    public void setLoadSharingMirror(boolean loadSharingMirror) {
        this.loadSharingMirror = loadSharingMirror;
    }

    public boolean isMoveMirror() {
        return moveMirror;
    }

    public void setMoveMirror(boolean moveMirror) {
        this.moveMirror = moveMirror;
    }

    public boolean isReplicaVolume() {
        return replicaVolume;
    }

    public void setReplicaVolume(boolean replicaVolume) {
        this.replicaVolume = replicaVolume;
    }

    public String getSnapmirrorSource() {
        return snapmirrorSource;
    }

    public void setSnapmirrorSource(String snapmirrorSource) {
        this.snapmirrorSource = snapmirrorSource;
    }

    public boolean isMirrorTransferInProgress() {
        return mirrorTransferInProgress;
    }

    public void setMirrorTransferInProgress(boolean mirrorTransferInProgress) {
        this.mirrorTransferInProgress = mirrorTransferInProgress;
    }

    public long getRedirectSnapshotId() {
        return redirectSnapshotId;
    }

    public void setRedirectSnapshotId(long redirectSnapshotId) {
        this.redirectSnapshotId = redirectSnapshotId;
    }
}
