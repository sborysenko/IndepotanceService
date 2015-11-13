package com.inatec.pgw.indepotance.storage.impl;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.inatec.pgw.indepotance.domain.Transaction;
import com.inatec.pgw.indepotance.domain.TransactionWrapper;
import com.inatec.pgw.indepotance.storage.Storage;

import java.util.Date;
import java.util.Map;

/**
 * Created by Sergey on 13.11.2015.
 */
public class HazelcastTwoLevelStorage implements Storage {
    public static final String LEVEL_0_MAP = "level_0_map";
    public static final String LEVEL_1_MAP = "level_1_map";
    HazelcastInstance client;


    public HazelcastTwoLevelStorage() {
//        Config cfg = new Config();
//        HazelcastInstance instance = Hazelcast.newHazelcastInstance(cfg);

        ClientConfig clientConfig = new ClientConfig();
        clientConfig.getNetworkConfig().setSmartRouting(true);
        clientConfig.getNetworkConfig().addAddress("127.0.0.1:5701");
        client = HazelcastClient.newHazelcastClient(clientConfig);
    }

    @Override
    public Transaction get(String transactionKey) {
        TransactionWrapper wrapper =  getMap(LEVEL_0_MAP).get(transactionKey);
        if (wrapper == null) {
            wrapper = getMap(LEVEL_1_MAP).get(transactionKey);
        }
        return wrapper != null ? wrapper.getTransaction(): null;
    }

    @Override
    public void put(String transactionKey, Transaction transaction) {
        TransactionWrapper wrapper = new TransactionWrapper(null);
        wrapper.setTransactionID(transaction.getTransactionID());
        wrapper.setAddedTime(new Date());
        Map l0Map = getMap(LEVEL_0_MAP);
        if (l0Map.size() < 2000000) {
            getMap(LEVEL_0_MAP).put(transactionKey, wrapper);
        } else {
            getMap(LEVEL_1_MAP).put(transactionKey, wrapper);
        }

    }

    @Override
    public boolean exists(String transactionKey) {
        return get(transactionKey) != null;
    }

    @Override
    public long getUsedMemory() {
        com.sun.management.OperatingSystemMXBean bean =
                (com.sun.management.OperatingSystemMXBean)
                        java.lang.management.ManagementFactory.getOperatingSystemMXBean();
        long max = bean.getTotalPhysicalMemorySize();
        long free = bean.getFreePhysicalMemorySize();
        System.out.println("Used memory = " + (max - free));
        return max - free;
    }

    private Map<String, TransactionWrapper> getMap(String mapName) {
        return client.getMap(mapName);
    }

}
