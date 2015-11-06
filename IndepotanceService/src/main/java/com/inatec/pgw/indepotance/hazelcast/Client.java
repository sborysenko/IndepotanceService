package com.inatec.pgw.indepotance.hazelcast;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.client.config.ClientNetworkConfig;
import com.hazelcast.core.HazelcastInstance;

import java.util.Map;

/**
 * Created by Sergey on 06.11.2015.
 */
public class Client {
    public static void main(String [] args) {
        ClientConfig clientConfig = new ClientConfig();
        clientConfig.getNetworkConfig().addAddress("127.0.0.1:5701");
        HazelcastInstance client = HazelcastClient.newHazelcastClient(clientConfig);
        Map map = client.getMap("customers");
        System.out.println("Map Size:" + map.size());
    }
}
