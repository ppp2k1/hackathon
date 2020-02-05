package com.netapp.zapi.zapi;

import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.ManagedBean;

@ManagedBean
public class OntapConnectionProviderFactory {

    @Autowired
    private OntapConnectionProvider ontapConnectionProvider;


    public OntapConnectionProvider getOntapConnectionProvider(OntapConnectionInfo ontapConnectionInfo) {
        OntapConnectionProvider connectionProvider = this.ontapConnectionProvider;
        connectionProvider.initOntapConnectionInfo(ontapConnectionInfo);

        return connectionProvider;
    }

}

