package com.netapp.zapi.pkik;

import javax.net.ssl.*;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class EmptySSLFactory implements X509TrustManager {

    private static final X509Certificate[] ACCEPTED_ISSUERS = new X509Certificate[0];

    @Override
    public X509Certificate[] getAcceptedIssuers() {
        return ACCEPTED_ISSUERS;
    }

    @Override
    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        // accept everything by not throwing an exception
    }

    @Override
    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        // accept everything by not throwing an exception
    }

    public static SSLSocketFactory getEmptySslFactory() throws KeyManagementException, NoSuchAlgorithmException {
        SSLContext sslContext = SSLContext.getInstance("SSL");
        sslContext.init(null, new TrustManager[]{new EmptySSLFactory()}, null);
        return sslContext.getSocketFactory();
    }

    public static class AcceptAllHostnameVerifier implements HostnameVerifier {
        @Override
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }

    }
}
