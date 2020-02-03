package com.netapp.monitoring;

import com.netapp.monitoring.Cluster;
import com.netapp.monitoring.Vserver;
import graphql.schema.DataFetcher;
import org.springframework.stereotype.Component;

@Component
public class DataFetcherFactory {

    public DataFetcher getClusterByKeyDataFetcher() {

        return dataFetchingEnvironment -> {
            String key = dataFetchingEnvironment.getArgument("key");
            Cluster cluster = new Cluster();
            cluster.setKey("key");
            return cluster;
        };

    }

    public DataFetcher getVserversForClusterDataFetcher() {

        return dataFetchingEnvironment -> {
            Cluster cluster = dataFetchingEnvironment.getSource();
            String key = cluster.getKey();
            return new Vserver("vserver-1","vserver-1",null);

        };

    }
}
