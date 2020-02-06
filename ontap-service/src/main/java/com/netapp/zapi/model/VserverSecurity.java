package com.netapp.zapi.model;

public class VserverSecurity {

    private boolean loginBannerConfigured;

    private boolean sshCiphersSecured;

    private boolean cifsLdapSigned;

    private boolean CifsLdapSealed;

    private boolean nfsKerberoseEnabled;

    private boolean iscsiChapEnabled;

    public boolean isLoginBannerConfigured() {
        return loginBannerConfigured;
    }

    public void setLoginBannerConfigured(boolean loginBannerConfigured) {
        this.loginBannerConfigured = loginBannerConfigured;
    }

    public boolean isSshCiphersSecured() {
        return sshCiphersSecured;
    }

    public void setSshCiphersSecured(boolean sshCiphersSecured) {
        this.sshCiphersSecured = sshCiphersSecured;
    }

    public boolean isCifsLdapSigned() {
        return cifsLdapSigned;
    }

    public void setCifsLdapSigned(boolean cifsLdapSigned) {
        this.cifsLdapSigned = cifsLdapSigned;
    }

    public boolean isCifsLdapSealed() {
        return CifsLdapSealed;
    }

    public void setCifsLdapSealed(boolean cifsLdapSealed) {
        CifsLdapSealed = cifsLdapSealed;
    }

    public boolean isNfsKerberoseEnabled() {
        return nfsKerberoseEnabled;
    }

    public void setNfsKerberoseEnabled(boolean nfsKerberoseEnabled) {
        this.nfsKerberoseEnabled = nfsKerberoseEnabled;
    }

    public boolean isIscsiChapEnabled() {
        return iscsiChapEnabled;
    }

    public void setIscsiChapEnabled(boolean iscsiChapEnabled) {
        this.iscsiChapEnabled = iscsiChapEnabled;
    }
}
