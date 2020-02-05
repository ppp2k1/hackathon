package com.netapp.monitoring;

public class SecurityAttributes {
    private String style;
    private SecurityUnixAttributes volumeSecurityUnixAttributes;

    public SecurityAttributes(String style, SecurityUnixAttributes volumeSecurityUnixAttributes) {
        this.style = style;
        this.volumeSecurityUnixAttributes = volumeSecurityUnixAttributes;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public SecurityUnixAttributes getVolumeSecurityUnixAttributes() {
        return volumeSecurityUnixAttributes;
    }

    public void setVolumeSecurityUnixAttributes(SecurityUnixAttributes volumeSecurityUnixAttributes) {
        this.volumeSecurityUnixAttributes = volumeSecurityUnixAttributes;
    }
}
