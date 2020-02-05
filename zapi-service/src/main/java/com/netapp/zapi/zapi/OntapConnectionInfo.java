package com.netapp.zapi.zapi;

import com.netapp.autozapi.client.ZapiTarget.Protocol;

public class OntapConnectionInfo {

    private final String primaryHostName;
    private final String username;
    private final String password;
    private final int port;
    private final Protocol protocol;

    public OntapConnectionInfo(String primaryHostName, String username, String password, int port, Protocol protocol) {
        this.primaryHostName = primaryHostName;
        this.username = username;
        this.password = password;
        this.port = port;
        this.protocol = protocol;
    }

    public String getPrimaryHostName() {
        return primaryHostName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getPort() {
        return port;
    }

    public Protocol getProtocol() {
        return protocol;
    }
}
