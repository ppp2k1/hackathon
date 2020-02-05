package com.netapp.monitoring;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.netapp.monitoring.Cluster;
import com.netapp.monitoring.Vserver;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@Component
public class DataFetcherFactory {

    public RestTemplate getRestTemplate(){
        return  new RestTemplate();
    }

    public DataFetcher getClusterByKeyDataFetcher() {

        return dataFetchingEnvironment -> {
            String key = dataFetchingEnvironment.getArgument("key");
            Cluster cluster = new Cluster();
            cluster.setKey("key");
            cluster.setName("name");
            return cluster;
        };

    }

    public DataFetcher getVserversForClusterDataFetcher() {

        return dataFetchingEnvironment -> {
            Cluster cluster = dataFetchingEnvironment.getSource();
            String key = cluster.getKey();

            ArrayList<Vserver> vservers = new ArrayList<>();
            vservers.add(new Vserver("vserver-1","vserver-1",null));
            return vservers;

        };
    }

    public TestUser getClusterByName(){
        ResponseEntity<String> response = getRestTemplate().getForEntity("https://api.github.com/users/umasree-v", String.class);
        if(response.getStatusCode() != HttpStatus.OK){
            return null;
        }
        String res = response.getBody();
        TestUser user = null;
        try {
            user = new ObjectMapper().readValue(res, TestUser.class);
        } catch (Exception e){
            e.printStackTrace();
        }
        return  user;
    }

    public DataFetcher getSecurityConfigByName() {
        return new DataFetcher() {
            @Override
            public Object get(DataFetchingEnvironment environment) throws Exception {
                String name = environment.getArgument("name");
                SecurityConfig config = new SecurityConfig();
                config.setName(name);
                return config;
            }
        };
    }

    public DataFetcher getVmWareByVmName() {
        return new DataFetcher() {
            @Override
            public Object get(DataFetchingEnvironment environment) throws Exception {
                SecurityConfig config = environment.getSource();
                String vmName = config.getName();

                VmWare vmWare = getVmWareByAPI(vmName);
                vmWare.setVmName(vmName);
                String path = "/uma";
                //vmWare.setPath(path);
                config.setOntap(getOntap("/u"));
                return vmWare;
            }
        };
    }

    private VmWare getVmWareByAPI(String vmName) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ArrayList<MediaType> list = new ArrayList<>();
        list.add(MediaType.APPLICATION_JSON);
        headers.setAccept(list);
        HttpEntity<String> request =
                new HttpEntity<String>("", headers);
        ResponseEntity<String> response = getRestTemplate().exchange("http://localhost:9999/vmware/config?vmName="+vmName,
                HttpMethod.GET, request, String.class);
        if(response.getStatusCode() != HttpStatus.OK){
            return null;
        }
        String res = response.getBody();
        VmWare vmWare = null;
        try {
            vmWare = new ObjectMapper().readValue(res, VmWare.class);
        } catch (Exception e){
            e.printStackTrace();
        }
        return  vmWare;
    }

    public Ontap getOntap(String path){
        Ontap ontap = null;
        try {
            RestTemplate rt = getRestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            String jsonBody = "{\n" +
                    "    \"zapiRequest\":\"volume-get-iter\",\n" +
                    "    \"queryParams\":{\"volume-id-attributes\":{\"junction-path\":\"/Test_W1\"}}\n" +
                    "}";

            HttpEntity<String> request =
                    new HttpEntity<String>(jsonBody, headers);
            ResponseEntity<String> res = rt.postForEntity("http://localhost:5555/execute/zapi", request, String.class);
            if(res.getStatusCode() != HttpStatus.OK) {
                return null;
            }
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(res.getBody());

            ontap = converJSONtoOntap(node);

            ontap.setPath(path);
        } catch (Exception e){
            e.printStackTrace();
        }
        return ontap;
    }

    private Ontap converJSONtoOntap(JsonNode node){
        Ontap ontap = new Ontap();
        JsonNode res = node.get(0);

        boolean encrypt = res.get("encrypt").asBoolean();
        ontap.setEncrypt(encrypt);

        JsonNode antivirus = res.get("volumeAntivirusAttributes");
        String onAccessPolicy = antivirus.get("onAccessPolicy").asText();
        ontap.setVolumeAntivirusAttributes(new AntivirusAttributes(onAccessPolicy));

        JsonNode mirrorAttrsNode = res.get("volumeMirrorAttributes");
        MirrorAttributes mirrorAttributes = new MirrorAttributes();
        mirrorAttributes.setDataProtectionMirror(mirrorAttrsNode.get("dataProtectionMirror").asBoolean());
        mirrorAttributes.setLoadSharingMirror(mirrorAttrsNode.get("loadSharingMirror").asBoolean());
        mirrorAttributes.setMoveMirror(mirrorAttrsNode.get("moveMirror").asBoolean());
        mirrorAttributes.setReplicaVolume(mirrorAttrsNode.get("replicaVolume").asBoolean());
        mirrorAttributes.setSnapmirrorSource(mirrorAttrsNode.get("snapmirrorSource").asText());
        mirrorAttributes.setMirrorTransferInProgress(mirrorAttrsNode.get("mirrorTransferInProgress").asBoolean());
        mirrorAttributes.setRedirectSnapshotId(mirrorAttrsNode.get("redirectSnapshotId").asLong());
        ontap.setVolumeMirrorAttributes(mirrorAttributes);

        JsonNode securityAttrsNode = res.get("volumeSecurityAttributes");
        String style = securityAttrsNode.get("style").asText();

        JsonNode unixAttrsNode = securityAttrsNode.get("volumeSecurityUnixAttributes");
        SecurityUnixAttributes unixAttributes = new SecurityUnixAttributes();
        unixAttributes.setGroupId(unixAttrsNode.get("groupId").asText());
        unixAttributes.setPermissions(unixAttrsNode.get("permissions").asLong());
        unixAttributes.setUserId(unixAttrsNode.get("userId").asText());

        SecurityAttributes securityAttributes = new SecurityAttributes(style, unixAttributes);
        ontap.setVolumeSecurityAttributes(securityAttributes);

        return ontap;
    }
}
