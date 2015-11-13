package com.inatec.pgw.indepotance.hazelcast;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.client.config.ClientNetworkConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.Member;

import java.rmi.Naming;
import java.util.Map;
import java.util.Set;

/**
 * Created by Sergey on 06.11.2015.
 */
public class Client {
    public static void main(String [] args) throws Exception {
        ClientConfig clientConfig = new ClientConfig();
        clientConfig.getNetworkConfig().addAddress("127.0.0.1:5701");
        HazelcastInstance client = HazelcastClient.newHazelcastClient(clientConfig);

        MemoryService service = (MemoryService) Naming.lookup("rmi://localhost/MemoryService");
        System.out.println(service.getUsedMemory());
    }
}
