package com.inatec.pgw.indepotance.storage.impl;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.config.Config;
import com.hazelcast.config.NativeMemoryConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.memory.MemorySize;
import com.hazelcast.memory.MemoryUnit;
import com.inatec.pgw.indepotance.domain.Transaction;

import java.util.Map;

/**
 * Created by Sergey on 11.11.2015.
 */
public class EnterpriseHaselcastStorage extends AbstractStorage {
    public static final String TRANSACTIONS_MAP = "transactions";
    HazelcastInstance client;

    public EnterpriseHaselcastStorage() {
//        MemorySize memorySize = new MemorySize(9700, MemoryUnit.MEGABYTES);
//        NativeMemoryConfig nativeMemoryConfig =
//                new NativeMemoryConfig()
//                        .setAllocatorType(NativeMemoryConfig.MemoryAllocatorType.POOLED)
//                        .setSize(memorySize)
//                        .setEnabled(true)
//                        .setMinBlockSize(16)
//                        .setPageSize(1 << 20);
//
//        Config cfg = new Config();
//        cfg.setLicenseKey( "HazelcastEnterprise#10Nodes#HDMemory:10GB#amwOik5AJEKHc3rfRN0yu1FB12910091091125p1t4q100L9d000X0G0gx20" );
//        cfg.setNativeMemoryConfig(nativeMemoryConfig);
//        HazelcastInstance instance = Hazelcast.newHazelcastInstance(cfg);

        ClientConfig clientConfig = new ClientConfig();
        clientConfig.setLicenseKey( "HazelcastEnterprise#10Nodes#HDMemory:10GB#amwOik5AJEKHc3rfRN0yu1FB12910091091125p1t4q100L9d000X0G0gx20" );
        clientConfig.getNetworkConfig().setSmartRouting(true);
        clientConfig.getNetworkConfig().addAddress("192.168.1.42:5701");
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

    @Override
    public boolean exists(String transactionKey) {
        return getMap().containsKey(transactionKey);
    }

    private Map<String, Transaction> getMap() {
        return client.getMap(TRANSACTIONS_MAP);
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
}
