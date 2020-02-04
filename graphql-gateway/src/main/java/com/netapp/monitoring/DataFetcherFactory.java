package com.netapp.monitoring;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.netapp.monitoring.Cluster;
import com.netapp.monitoring.Vserver;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@Component
public class DataFetcherFactory {


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
        RestTemplate template = new RestTemplate();
        ResponseEntity<String> response = template.getForEntity("https://api.github.com/users/umasree-v", String.class);
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
                getClusterByName();
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
                VmWare vmWare = new VmWare();
                vmWare.setName(vmName);
                String path = "/uma";
                vmWare.setPath(path);
                config.setOntap(getOntap("/u"));
                return vmWare;
            }
        };
    }

    public Ontap getOntap(String path){
        Ontap ontap = new Ontap();
        ontap.setPath(path);
        return ontap;
    }

    public DataFetcher getOntapByPath(){
        return new DataFetcher() {
            @Override
            public Object get(DataFetchingEnvironment environment) throws Exception {
                SecurityConfig config = environment.getSource();
                String path = config.getVmWare().getPath();
                Ontap ontap = new Ontap();
                ontap.setPath(path);
                return ontap;
            }
        };
    }
}
