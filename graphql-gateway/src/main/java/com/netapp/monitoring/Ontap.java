package com.netapp.monitoring;

public class Ontap {

    private String ip;
    private String path;
    private Boolean Encrypt;
    private AntivirusAttributes volumeAntivirusAttributes;
    private MirrorAttributes volumeMirrorAttributes;
    private SecurityAttributes volumeSecurityAttributes;

    public Ontap() {
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Boolean getEncrypt() {
        return Encrypt;
    }

    public void setEncrypt(Boolean encrypt) {
        Encrypt = encrypt;
    }

    public AntivirusAttributes getVolumeAntivirusAttributes() {
        return volumeAntivirusAttributes;
    }

    public void setVolumeAntivirusAttributes(AntivirusAttributes volumeAntivirusAttributes) {
        this.volumeAntivirusAttributes = volumeAntivirusAttributes;
    }

    public MirrorAttributes getVolumeMirrorAttributes() {
        return volumeMirrorAttributes;
    }

    public void setVolumeMirrorAttributes(MirrorAttributes volumeMirrorAttributes) {
        this.volumeMirrorAttributes = volumeMirrorAttributes;
    }

    public SecurityAttributes getVolumeSecurityAttributes() {
        return volumeSecurityAttributes;
    }

    public void setVolumeSecurityAttributes(SecurityAttributes volumeSecurityAttributes) {
        this.volumeSecurityAttributes = volumeSecurityAttributes;
    }
}
