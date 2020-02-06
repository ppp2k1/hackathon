package com.netapp.monitoring.model;

public class Volume {
    private Boolean softwareEncrypt;
    private Boolean hardwareEncrypt;
    private AntivirusAttributes volumeAntivirusAttributes;
    private ExportAttributes volumeExportAttributes;
    private SecurityAttributes volumeSecurityAttributes;
    private IdAttributes volumeIdAttributes;

    public Boolean getSoftwareEncrypt() {
        return softwareEncrypt;
    }

    public void setSoftwareEncrypt(Boolean softwareEncrypt) {
        this.softwareEncrypt = softwareEncrypt;
    }

    public Boolean getHardwareEncrypt() {
        return hardwareEncrypt;
    }

    public void setHardwareEncrypt(Boolean hardwareEncrypt) {
        this.hardwareEncrypt = hardwareEncrypt;
    }

    public AntivirusAttributes getVolumeAntivirusAttributes() {
        return volumeAntivirusAttributes;
    }

    public void setVolumeAntivirusAttributes(AntivirusAttributes volumeAntivirusAttributes) {
        this.volumeAntivirusAttributes = volumeAntivirusAttributes;
    }

    public ExportAttributes getVolumeExportAttributes() {
        return volumeExportAttributes;
    }

    public void setVolumeExportAttributes(ExportAttributes volumeExportAttributes) {
        this.volumeExportAttributes = volumeExportAttributes;
    }

    public SecurityAttributes getVolumeSecurityAttributes() {
        return volumeSecurityAttributes;
    }

    public void setVolumeSecurityAttributes(SecurityAttributes volumeSecurityAttributes) {
        this.volumeSecurityAttributes = volumeSecurityAttributes;
    }

    public IdAttributes getVolumeIdAttributes() {
        return volumeIdAttributes;
    }

    public void setVolumeIdAttributes(IdAttributes volumeIdAttributes) {
        this.volumeIdAttributes = volumeIdAttributes;
    }
}
