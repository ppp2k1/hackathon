package com.netapp.zapi.zapi;

import com.netapp.autozapi.client.ZapiRunnerFactory;
import com.netapp.autozapi.client.ZapiTarget;
import com.netapp.autozapi.client.ZapiTarget.Protocol;
import com.netapp.zapi.pkik.EmptySSLFactory;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;


/**
 * Factory for {@link ZAPIConnection} objects.
 * <p/>
 * The {@link ZAPIConnection} objects returned by this factory are
 * flow controlled per filer (in the current jvm).
 * A configuration option for the maximim number of concurrent api calls per filer
 * is used to limit the concurrent api calls to a filer.
 */
@ManagedBean
public class ZAPIConnectionFactory {

    private ZapiRunnerFactory zapiRunnerFactory;

    @PostConstruct
    public void init() {
        /*
         * Wildfly sets by default different XML implementation.
         * Implementing the solution suggested in  https://developer.jboss.org/thread/195719
         * [ Look for Ron Han's solution ]
         */


        this.zapiRunnerFactory = new ZapiRunnerFactory(100);
    }

    /**
     * Creates a {@link ZAPIConnection} for the given parameters.
     */
    public ZAPIConnection createZAPIConnection(String hostName, String userName, String password, int port, Protocol protocol) {
        try {
            return new ZAPIConnection(
                    zapiRunnerFactory.createZapiRunner(ZapiTarget.builder()
                            .withTargetType(ZapiTarget.TargetType.FILER)
                            .withHost(hostName)
                            .withPort(port)
                            .withUserName(userName)
                            .withPassword(password)
                            .useProtocol(protocol)
                            .withSslFactory(EmptySSLFactory.getEmptySslFactory())
                            .withHostnameVerifier(new EmptySSLFactory.AcceptAllHostnameVerifier())
                            .build())
                            .withReadTimeout(300000)
                            .withConnectTimeout(30000), 1000);
        }catch (Exception ex){
            System.out.println("Error");
            ex.printStackTrace();
        }
        return null;
    }

}
