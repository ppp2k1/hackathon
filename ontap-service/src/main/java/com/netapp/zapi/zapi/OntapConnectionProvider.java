package com.netapp.zapi.zapi;

import com.netapp.autozapi.client.ZapiTarget.Protocol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;

import javax.annotation.ManagedBean;

@ManagedBean
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class OntapConnectionProvider {

    @Autowired
    private ZAPIConnectionFactory zapiConnectionFactory;

    private ZAPIConnection zapiConnection;
    private OntapConnectionInfo ontapConnectionInfo;

    OntapConnectionProvider() {
    }

    void initOntapConnectionInfo(OntapConnectionInfo ontapConnectionInfo) {
        this.ontapConnectionInfo = ontapConnectionInfo;
    }

    public ZAPIConnection getZAPIConnection(String vserverName) {
        return this.getZAPIConnection().withVserverName(vserverName);
    }

    public ZAPIConnection getZAPIConnection() {
        if (this.zapiConnection == null) {
            this.zapiConnection = this.zapiConnectionFactory.createZAPIConnection(
                    this.ontapConnectionInfo.getPrimaryHostName(),
                    this.ontapConnectionInfo.getUsername(),
                    this.ontapConnectionInfo.getPassword(),
                    this.ontapConnectionInfo.getPort(),
                    this.ontapConnectionInfo.getProtocol());
        }
        return this.zapiConnection;
    }

    /**
     * Get the API protocol to use for executing APIs against this cluster.
     */
    public Protocol getApiProtocol() {
        return this.ontapConnectionInfo.getProtocol();
    }
}

