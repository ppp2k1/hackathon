package com.netapp.zapi.controller;

import com.netapp.common.util.StringUtils;
import com.netapp.ontap.api.autosupport.AutosupportConfigInfo;
import com.netapp.ontap.api.cifs.CifsSecurity;
import com.netapp.ontap.api.iscsi.IscsiSecurityEntryInfo;
import com.netapp.ontap.api.kerberos.KerberosConfigInfo;
import com.netapp.ontap.api.security.SecurityConfigInfo;
import com.netapp.ontap.api.security.SecurityProtocolInfo;
import com.netapp.ontap.api.security.SecuritySshInfo;
import com.netapp.ontap.api.vserver.VserverLoginBannerInfo;
import com.netapp.zapi.model.ClusterSecurity;
import com.netapp.zapi.model.GenericZapiRequest;
import com.netapp.zapi.model.VserverSecurity;
import com.netapp.zapi.service.ZapiExecutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ZapiController {

    @Autowired
    private ZapiExecutorService zapiExecutorService;

    @PostMapping("/execute/zapi")
    public Object getTest(@RequestBody GenericZapiRequest genericZapiRequest) {
        Object o = zapiExecutorService.executeZapi(genericZapiRequest);
        return ResponseEntity.ok(o);
    }

    @GetMapping("/ontap/config")
    public Object getNfsShare(@RequestParam String path){
        String[] arr = path.split(":");
        String cl = arr[0];
        String junctionPath = arr[1];
        GenericZapiRequest genericZapiRequest = new GenericZapiRequest();
        genericZapiRequest.setZapiRequest("volume-get-iter");
        Map<String, Object> queryParam = new HashMap<>();
        Map<String, Object> junctionpathQuery = new HashMap<>();
        junctionpathQuery.put("junctionPath", junctionPath);

        queryParam.put("volumeIdAttributes",  junctionpathQuery);
        genericZapiRequest.setQueryParams(queryParam);
        Object o = zapiExecutorService.executeZapi(genericZapiRequest);
        return ResponseEntity.ok(o);
    }

    @GetMapping("/ontap/vserver")
    public Object getVserver(@RequestParam String name){

        GenericZapiRequest genericZapiRequest = new GenericZapiRequest();
        genericZapiRequest.setZapiRequest("vserver-login-banner-get-iter");
        genericZapiRequest.setVserverTunnel(name);
        Object o = zapiExecutorService.executeZapi(genericZapiRequest);

        VserverSecurity vserverSecurity = new VserverSecurity();
        if(o instanceof List && !((List) o).isEmpty()){
            VserverLoginBannerInfo child = (VserverLoginBannerInfo) ((List)o).get(0);
            vserverSecurity.setLoginBannerConfigured(!StringUtils.isEmpty(child.getMessage()));
        } else{
            vserverSecurity.setLoginBannerConfigured(false);
        }

        genericZapiRequest.setZapiRequest("security-ssh-get-iter");
        genericZapiRequest.setVserverTunnel(name);
        Object ssh = zapiExecutorService.executeZapi(genericZapiRequest);

        if(ssh instanceof List && !((List) ssh).isEmpty()){
            SecuritySshInfo child = (SecuritySshInfo) ((List)ssh).get(0);

            int cbc_cipherCount = (int) child.getCiphers().stream().filter(a -> a.contains("cbc")).count();
            vserverSecurity.setSshCiphersSecured(cbc_cipherCount < 1);
        } else{
            vserverSecurity.setSshCiphersSecured(false);
        }

        //cifs

        genericZapiRequest.setZapiRequest("cifs-security-get-iter");
        genericZapiRequest.setVserverTunnel(name);
        Object cifs = zapiExecutorService.executeZapi(genericZapiRequest);

        if(cifs instanceof List && !((List) cifs).isEmpty()){
            CifsSecurity child = (CifsSecurity) ((List)cifs).get(0);
            if(child.getSessionSecurityForAdLdap() != null) {
                boolean seal = child.getSessionSecurityForAdLdap().equalsIgnoreCase("SEAL");
                boolean sign = (seal || child.getSessionSecurityForAdLdap().equalsIgnoreCase("SIGN"));
                vserverSecurity.setCifsLdapSigned(sign);
                vserverSecurity.setCifsLdapSealed(seal);
            }
        }


        //nfs
        genericZapiRequest.setZapiRequest("kerberos-config-get-iter");
        genericZapiRequest.setVserverTunnel(name);
        Object nfs = zapiExecutorService.executeZapi(genericZapiRequest);

        if(nfs instanceof List && !((List) nfs).isEmpty()){
            KerberosConfigInfo child = (KerberosConfigInfo) ((List)nfs).get(0);
            vserverSecurity.setNfsKerberoseEnabled(child.isKerberosEnabled());
        }

        //iscsi

        genericZapiRequest.setZapiRequest("iscsi-initiator-auth-get-iter");
        genericZapiRequest.setVserverTunnel(name);
        Object iscsi = zapiExecutorService.executeZapi(genericZapiRequest);

        if(iscsi instanceof List && !((List) iscsi).isEmpty()){
            IscsiSecurityEntryInfo child = (IscsiSecurityEntryInfo) ((List)iscsi).get(0);
            vserverSecurity.setIscsiChapEnabled(child.getAuthType().equalsIgnoreCase("CHAP"));

        }

        return ResponseEntity.ok(vserverSecurity);
    }

    @GetMapping("/ontap/cluster")
    public Object getCluster(@RequestParam String name){

        //telnet
        GenericZapiRequest genericZapiRequest = new GenericZapiRequest();
        genericZapiRequest.setZapiRequest("security-protocol-get");
        Map<String, Object> input = new HashMap<>();
        input.put("application", "telnet");
        genericZapiRequest.setInputParams(input);
        Object o = zapiExecutorService.executeZapi(genericZapiRequest);

        ClusterSecurity clusterSecurity = new ClusterSecurity();
        if(o instanceof List && !((List) o).isEmpty()){
            SecurityProtocolInfo child = (SecurityProtocolInfo) ((List)o).get(0);
            clusterSecurity.setTelnetEnabled(child.isEnabled());
        } else{
            clusterSecurity.setTelnetEnabled(false);
        }


        //fips

        genericZapiRequest.setZapiRequest("security-config-get");
        input.clear();
        input.put("zapiInterface", "ssl");
        genericZapiRequest.setInputParams(input);

        Object fips = zapiExecutorService.executeZapi(genericZapiRequest);

        if(fips instanceof List && !((List) fips).isEmpty()){
            SecurityConfigInfo child = (SecurityConfigInfo) ((List)fips).get(0);
            clusterSecurity.setFipsEnabled(child.isFipsEnabled());
        } else{
            clusterSecurity.setFipsEnabled(false);
        }


        //asup https
        genericZapiRequest.setZapiRequest("autosupport-config-get-iter");
        input.clear();

        Object asup = zapiExecutorService.executeZapi(genericZapiRequest);

        if(asup instanceof List && !((List) asup).isEmpty()){
            AutosupportConfigInfo child = (AutosupportConfigInfo) ((List)asup).get(0);
            clusterSecurity.setAsupHttpsConfigured(child.getTransport().equalsIgnoreCase("https"));
        } else{
            clusterSecurity.setAsupHttpsConfigured(false);
        }

        //ssh ciphers

        genericZapiRequest.setZapiRequest("security-ssh-get-iter");
        genericZapiRequest.setVserverTunnel("mvaqa-fas3270-01-02");
        Object ssh = zapiExecutorService.executeZapi(genericZapiRequest);

        if(ssh instanceof List && !((List) ssh).isEmpty()){
            SecuritySshInfo child = (SecuritySshInfo) ((List)ssh).get(0);

            int cbc_cipherCount = (int) child.getCiphers().stream().filter(a -> a.contains("cbc")).count();
            clusterSecurity.setSshCiphersSecured(cbc_cipherCount < 1);
        } else{
            clusterSecurity.setSshCiphersSecured(false);
        }

        //login banner

        genericZapiRequest.setZapiRequest("vserver-login-banner-get-iter");
        Object banner = zapiExecutorService.executeZapi(genericZapiRequest);

        if(banner instanceof List && !((List) banner).isEmpty()){
            VserverLoginBannerInfo child = (VserverLoginBannerInfo) ((List)banner).get(0);
            clusterSecurity.setLoginBannerConfigured(!StringUtils.isEmpty(child.getMessage()));
        } else{
            clusterSecurity.setLoginBannerConfigured(false);
        }

        return ResponseEntity.ok(clusterSecurity);
    }

}
