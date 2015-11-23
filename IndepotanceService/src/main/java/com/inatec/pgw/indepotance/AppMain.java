package com.inatec.pgw.indepotance;

import com.inatec.pgw.indepotance.domain.Transaction;
import com.inatec.pgw.indepotance.storage.KeyProvider;
import com.inatec.pgw.indepotance.storage.Storage;
import com.inatec.pgw.indepotance.storage.impl.*;
import com.inatec.pgw.indepotance.testing.TransactionGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.DecimalFormat;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Sergey on 02.11.2015.
 */
public class AppMain {
    private static Logger csvLogger = LogManager.getLogger("csv");

    public static void main(String [] args) {
        final Storage storage = new RedisClusterStorage();
        final KeyProvider keyProvider = new SimpleKeyProvider();

        csvLogger.debug("Thread | Threads | Key provider | Storage | # | Memory Used [MB] | Insert time [ms] | Read time [ms] | Lookup time [ms]");

        long start = System.currentTimeMillis();

        final int threadsCount = 5;
//        final int threadsCount = 30;
//        final int transactionsCount = 100000;
        final int transactionsCount = 1000000;
//        final int transactionsCount = 43200000;

        ExecutorService executor = Executors.newFixedThreadPool(threadsCount);

        for (int t=0; t<threadsCount; t++) {
            executor.submit(new ExecutionThread(
                    t,
                    threadsCount,
                    transactionsCount/threadsCount,
                    keyProvider,
                    storage,
                    t==0,
                    (transactionsCount/threadsCount)/1000));
        }

        executor.shutdown();

        while (!executor.isTerminated()) {
            try {
                Thread.currentThread().sleep(15*1000);
                System.out.println("Check for termination");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        long finish = System.currentTimeMillis();

        System.out.println("Finished in " + (finish - start) + "ms");
        csvLogger.debug("Finished in " + (finish - start) + "ms");
    }
}
