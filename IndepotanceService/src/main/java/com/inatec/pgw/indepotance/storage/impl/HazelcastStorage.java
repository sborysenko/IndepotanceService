package com.inatec.pgw.indepotance.storage.impl;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import com.inatec.pgw.indepotance.domain.Transaction;
import com.inatec.pgw.indepotance.storage.Storage;

import java.util.Map;

/**
 * Created by Sergey on 06.11.2015.
 */
public class HazelcastStorage implements Storage {
    public static final String TRANSACTIONS_MAP = "transactions";
    HazelcastInstance client;

    public HazelcastStorage() {
        ClientConfig clientConfig = new ClientConfig();
        clientConfig.getNetworkConfig().setSmartRouting(true);
        clientConfig.getNetworkConfig().addAddress("127.0.0.1:5701");
        client = HazelcastClient.newHazelcastClient(clientConfig);
    }

    @Override
    public Transaction get(String transactionKey) {
        return getMap().get(transactionKey);
    }

    @Override
    public void put(String transactionKey, Transaction transaction) {
        getMap().put(transactionKey, transaction);
    }

    private Map<String, Transaction> getMap() {
        return client.getMap(TRANSACTIONS_MAP);
    }
}
