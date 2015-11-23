package com.inatec.pgw.indepotance;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpClient;
import io.vertx.core.impl.FutureFactoryImpl;
import org.junit.Test;


import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

import static org.junit.Assert.*;

/**
 * Created by Sergey on 18.11.2015.
 */
public class MemoryInfoVerticleTest {

    @Test
    public void integrationTest() {
        CountDownLatch latch = new CountDownLatch(1);
        final Future<Long> future = new FutureFactoryImpl().future();
        HttpClient client = Vertx.vertx().createHttpClient();
        client.get(8888, "192.168.163.129", "/info")
                .handler(response -> {
                    response.handler(body -> {
                        future.complete(Long.parseLong(new String(body.getBytes())));
                        client.close();
                        latch.countDown();
                    });
                })
                .end();

        try {
            latch.await();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(future.result());
    }
}