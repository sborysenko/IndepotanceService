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
//    private static Logger logger = LogManager.getLogger(AppMain.class);
    private static Logger csvLogger = LogManager.getLogger("csv");
    static DecimalFormat decFormat = new DecimalFormat("#.##");
    static DecimalFormat memoryFormat = new DecimalFormat("#.###");


    public static void main(String [] args) {
        final Runtime runtime = Runtime.getRuntime();
        final Storage storage = new CompressedStorage();
        final KeyProvider keyProvider = new SimpleKeyProvider();

//        logger.debug("Key provider - {}; Storage - {}", keyProvider.getClass().getSimpleName(), storage.getClass().getSimpleName());
        csvLogger.debug("Key provider | Storage | # | Memory Used [MB] | Insert time [ms] | Read time [ms]");

        long start = System.currentTimeMillis();

        final int threadsCount = 1;
//        final int transactionsCount = 1000000;
//        final int transactionsCount = 43200000;
        final int transactionsCount = 1000;

        ExecutorService executor = Executors.newFixedThreadPool(threadsCount);

        for (int t=0; t<threadsCount; t++) {
            final TransactionGenerator generator = new TransactionGenerator();
            executor.submit(new Runnable() {
                public void run() {
                    for (int i = 0; i < (transactionsCount / threadsCount); i++) {
                        Transaction transaction = generator.generateTransaction();

                        long keyStartTime = System.nanoTime();
                        String key = keyProvider.getKey(transaction);
                        long putStartTime = System.nanoTime();
                        storage.put(key, transaction);
                        long finishTime = System.nanoTime();

                        long memory = (runtime.totalMemory() - runtime.freeMemory());

                        long startLoadTime = System.nanoTime();
                        key = keyProvider.getKey(transaction);
                        long keyFinished = System.nanoTime();
                        Transaction loaded = storage.get(key);
                        long finishLoadTime = System.nanoTime();

                        if (!loaded.equals(transaction)) {
                            throw new IllegalStateException("Transactions don't match!");
                        }
/*
                        logger.debug("{} - {} Kb; Put:['{}'ns + '{}'ns = '{}'ns] Read:['{}'ns + '{}'ns = '{}'ns]. Key - [{}] transaction [{}]",
                                i + 1,
                                memory / (1024),
                                putStartTime - keyStartTime,
                                finishTime - putStartTime,
                                finishTime - keyStartTime,

                                keyFinished - startLoadTime,
                                finishLoadTime - keyFinished,
                                finishLoadTime - startLoadTime,
                                key,
                                transaction);
*/
                        csvLogger.debug("{} | {} | {} | {} | {} | {}",
                                keyProvider.getClass().getSimpleName(),
                                storage.getClass().getSimpleName(),
                                i+1,
                                memoryFormat.format(((double)memory) / (1024*1024)),
                                decFormat.format((double)(finishTime-keyStartTime)/1000000),
                                decFormat.format((double)(finishLoadTime-startLoadTime)/1000000));
                    }
                    System.out.println("Tread finished");
                }
            });
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
//        logger.debug(
//                "Threads count = {}; Total transactions count = {}; Total time = {}ms",
//                threadsCount, transactionsCount, (finish-start));
    }

/*
    private static String printOutTransaction(Transaction transaction) {
        StringBuilder out = new StringBuilder();

        out.append("[");
        out.append(getPredefinedWidthString(6, transaction.getTransactionID())).append(" ");
        out.append(getPredefinedWidthString(2, transaction.getMerchantID())).append(" ");
        out.append(decFormat.format(transaction.getAmount())).append(" ");
        out.append(transaction.getTransactionCurrency()).append(" ");
        out.append(getPredefinedWidthString(30, transaction.getFirstName())).append(" ");
        out.append(getPredefinedWidthString(30, transaction.getLastName()));
        out.append("]");

        return out.toString();
    }

    private static String getPredefinedWidthString(int charsCount, String value) {
        StringBuilder out = new StringBuilder();

        out.append(value);
        while(out.length() <= charsCount) out.insert(0, " ");

        return out.toString();
    }
    private static String getPredefinedWidthString(int charsCount, long value) {
        StringBuilder out = new StringBuilder();

        out.append(value);
        while(out.length() <= charsCount) out.insert(0, " ");

        return out.toString();
    }
*/
}
