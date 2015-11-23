package com.inatec.pgw.indepotance.storage.impl;

import com.inatec.pgw.indepotance.domain.Transaction;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.impl.FutureFactoryImpl;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

/**
 * Created by Sergey on 18.11.2015.
 */
public class RedisClusterStorage extends AbstractStorage {
    JedisCluster jedisCluster;


    public RedisClusterStorage() {
        Set<HostAndPort> jedisClusterNodes = new HashSet<>();

        //Jedis Cluster will attempt to discover cluster nodes automatically
        jedisClusterNodes.add(new HostAndPort("192.168.163.135", 6379));
        jedisClusterNodes.add(new HostAndPort("192.168.163.136", 6379));
        jedisClusterNodes.add(new HostAndPort("192.168.163.137", 6379));
        jedisCluster = new JedisCluster(jedisClusterNodes);
    }

    @Override
    public Transaction get(String transactionKey) {
        String id = jedisCluster.get(transactionKey);
        if (id != null) {
            Transaction transaction = new Transaction();
            transaction.setTransactionID(Long.parseLong(id));
        }

        return null;
    }

    @Override
    public void put(String transactionKey, Transaction transaction) {
        try {
            jedisCluster.set(transactionKey, String.valueOf(transaction.getTransactionID()));
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    @Override
    public boolean exists(String transactionKey) {
        return jedisCluster.exists(transactionKey);
    }

    @Override
    public long getUsedMemory() {
        CountDownLatch latch = new CountDownLatch(1);
        final Future<String> future = new FutureFactoryImpl().future();

        HttpClient client = Vertx.vertx().createHttpClient();
        client.get(8888, "192.168.163.135", "/info")
                .handler(response -> {
                    response.handler(body -> {
                        future.complete(new String(body.getBytes()));
                        client.close();
                        latch.countDown();
                    });
                })
                .end();

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return Long.valueOf(future.result());
    }
}
