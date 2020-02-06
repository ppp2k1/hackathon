package com.netapp.monitoring.model;

import java.util.List;

public class Cluster {
    private boolean telnetEnabled;

    private boolean sshCiphersSecured;

    private boolean asupHttpsConfigured;

    private boolean fipsEnabled;

    private boolean loginBannerConfigured;

    public Cluster() {
    }

    public boolean isTelnetEnabled() {
        return telnetEnabled;
    }

    public void setTelnetEnabled(boolean telnetEnabled) {
        this.telnetEnabled = telnetEnabled;
    }

    public boolean isSshCiphersSecured() {
        return sshCiphersSecured;
    }

    public void setSshCiphersSecured(boolean sshCiphersSecured) {
        this.sshCiphersSecured = sshCiphersSecured;
    }

    public boolean isAsupHttpsConfigured() {
        return asupHttpsConfigured;
    }

    public void setAsupHttpsConfigured(boolean asupHttpsConfigured) {
        this.asupHttpsConfigured = asupHttpsConfigured;
    }

    public boolean isFipsEnabled() {
        return fipsEnabled;
    }

    public void setFipsEnabled(boolean fipsEnabled) {
        this.fipsEnabled = fipsEnabled;
    }

    public boolean isLoginBannerConfigured() {
        return loginBannerConfigured;
    }

    public void setLoginBannerConfigured(boolean loginBannerConfigured) {
        this.loginBannerConfigured = loginBannerConfigured;
    }
}
