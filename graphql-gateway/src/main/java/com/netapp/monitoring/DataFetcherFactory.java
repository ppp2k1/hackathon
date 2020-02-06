package com.netapp.monitoring;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netapp.monitoring.model.*;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@Component
public class DataFetcherFactory {

    public RestTemplate getRestTemplate(){
        return  new RestTemplate();
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

                Datastore ds = vmWare.getDatastore();

                Ontap ontap = getOntap(ds.getRemoteHost(), ds.getRemotePath());
                ontap.setIp(ds.getRemoteHost());
                config.setOntap(ontap);
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
        ResponseEntity<String> response = getRestTemplate().exchange("http://vmware-service:9999/vmware/config?vmName="+vmName,
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

    public Ontap getOntap(String host, String path){
        Ontap ontap = null;
        try {
            RestTemplate rt = getRestTemplate();

            String configPath= host+":"+path;
            ResponseEntity<String> res = rt.getForEntity("http://zapi-service:5555//ontap/cofig?path="+configPath, String.class);
            if(res.getStatusCode() != HttpStatus.OK) {
                return null;
            }
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(res.getBody());
            ontap = converJSONtoOntap(node);

            String vserverName=ontap.getVolume().getVolumeIdAttributes().getOwningVserverName();
            res = rt.getForEntity("http://zapi-service:5555//ontap/vserver?name="+vserverName, String.class);
            if(res.getStatusCode() != HttpStatus.OK) {
                return null;
            }
            mapper = new ObjectMapper();
            Vserver vserver = mapper.readValue(res.getBody(), Vserver.class);
            ontap.setVserver(vserver);

            String clusterName=host;
            res = rt.getForEntity("http://zapi-service:5555//ontap/cluster?name="+clusterName, String.class);
            if(res.getStatusCode() != HttpStatus.OK) {
                return null;
            }
            mapper = new ObjectMapper();
            Cluster cluster = mapper.readValue(res.getBody(), Cluster.class);
            ontap.setCluster(cluster);

        } catch (Exception e){
            e.printStackTrace();
        }
        return ontap;
    }

    private Ontap converJSONtoOntap(JsonNode node){
        Ontap ontap = new Ontap();
        JsonNode res = node.get(0);

        Volume volume = new Volume();

        boolean encrypt = res.get("encrypt").asBoolean();
        volume.setSoftwareEncrypt(encrypt);
        //TODO Dummy Data
        volume.setHardwareEncrypt(false);

        JsonNode antivirus = res.get("volumeAntivirusAttributes");
        String onAccessPolicy = antivirus.get("onAccessPolicy").asText();
        volume.setVolumeAntivirusAttributes(new AntivirusAttributes(onAccessPolicy));

        JsonNode exportAttributesNode = res.get("volumeExportAttributes");
        String policy = exportAttributesNode.get("policy").asText();
        volume.setVolumeExportAttributes(new ExportAttributes(policy));

        JsonNode securityAttrsNode = res.get("volumeSecurityAttributes");
        String style = securityAttrsNode.get("style").asText();

        JsonNode unixAttrsNode = securityAttrsNode.get("volumeSecurityUnixAttributes");
        SecurityUnixAttributes unixAttributes = new SecurityUnixAttributes();
        unixAttributes.setGroupId(unixAttrsNode.get("groupId").asText());
        unixAttributes.setPermissions(unixAttrsNode.get("permissions").asLong());
        unixAttributes.setUserId(unixAttrsNode.get("userId").asText());

        SecurityAttributes securityAttributes = new SecurityAttributes(style, unixAttributes);
        volume.setVolumeSecurityAttributes(securityAttributes);

        JsonNode idAttrsNode = res.get("volumeIdAttributes");
        IdAttributes idAttributes = new IdAttributes();
        idAttributes.setName(idAttrsNode.get("name").asText());
        idAttributes.setUuid(idAttrsNode.get("uuid").asText());
        idAttributes.setNode(idAttrsNode.get("node").asText());
        idAttributes.setJunctionPath(idAttrsNode.get("junctionPath").asText());
        idAttributes.setType(idAttrsNode.get("type").asText());
        idAttributes.setStyle(idAttrsNode.get("style").asText());
        idAttributes.setStyleExtended(idAttrsNode.get("styleExtended").asText());
        idAttributes.setOwningVserverName(idAttrsNode.get("owningVserverName").asText());
        idAttributes.setContainingAggregateName(idAttrsNode.get("containingAggregateName").asText());
        idAttributes.setContainingAggregateUuid(idAttrsNode.get("containingAggregateUuid").asText());
        ArrayList<String> aggrList = new ArrayList<>();
        JsonNode aggrlistNode = idAttrsNode.get("aggrList");
        aggrlistNode.forEach(a->aggrList.add(a.asText()));
        idAttributes.setAggrList(aggrList);
        volume.setVolumeIdAttributes(idAttributes);

        ontap.setVolume(volume);
        return ontap;
    }
}
