package com.inatec.pgw.indepotance;

import com.inatec.pgw.indepotance.domain.Transaction;
import com.inatec.pgw.indepotance.storage.KeyProvider;
import com.inatec.pgw.indepotance.storage.Storage;
import com.inatec.pgw.indepotance.testing.TransactionGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.DecimalFormat;

/**
 * Created by Sergey on 09.11.2015.
 */
public class ExecutionThread implements Runnable {
    private static Logger csvLogger = LogManager.getLogger("csv");
    static DecimalFormat decFormat = new DecimalFormat("#.####");
    static DecimalFormat memoryFormat = new DecimalFormat("#.###");

    int threadIndex;
    int threadsCount;
    int itemsCount;
    Storage storage;
    KeyProvider keyProvider;
    boolean log = false;
    int skip;
    TransactionGenerator generator = new TransactionGenerator();

    public ExecutionThread(int threadIndex, int threadsCount, int itemsCount, KeyProvider keyProvider, Storage storage, boolean log, int skip) {
        this.threadIndex = threadIndex;
        this.threadsCount = threadsCount;
        this.keyProvider = keyProvider;
        this.storage = storage;
        this.itemsCount = itemsCount;
        this.log = log;
        this.skip = skip;
    }

    @Override
    public void run() {
        int count = 0;
        double totalRead = 0;
        double totalWrite = 0;
        double totalLookup = 0;

        for (int i = 0; i < itemsCount; i++) {
            Transaction transaction = generator.generateTransaction();

            long writeStartTime = System.nanoTime();
            String key = keyProvider.getKey(transaction);
            storage.put(key, transaction);
            long writeTime = System.nanoTime() - writeStartTime;

            long loadStartTime = System.nanoTime();
            key = keyProvider.getKey(transaction);
            Transaction loaded = storage.get(key);
//            Transaction loaded = storage.get(String.valueOf(transaction.getId()));
            long loadTime = System.nanoTime() - loadStartTime;

            long lookupStartTime = System.nanoTime();
            key = keyProvider.getKey(transaction);
            storage.exists(key);
            long lookupTime = System.nanoTime() - lookupStartTime;

//            if (loaded != null) {
//                if (loaded.getTransactionID() != transaction.getTransactionID()) {
//                    throw new IllegalStateException("Transactions don't match!");
//                }
//            }

            count++;
            totalWrite += (double) writeTime / 1000000;
            totalRead += (double) loadTime / 1000000;
            totalLookup += (double) lookupTime / 1000000;

            if (String.valueOf(threadIndex + i * threadsCount + 1).endsWith("0000")) {
                System.out.println("Processed item" + (threadIndex + i * threadsCount + 1));
            }

            if (String.valueOf(threadIndex + i * threadsCount + 1).endsWith("00000")) {
                csvLogger.debug("{} | {} | {} | {} | {} | {} | {} | {} | {}",
                        threadIndex + 1,
                        threadsCount,
                        keyProvider.getClass().getSimpleName(),
                        storage.getClass().getSimpleName(),
                        threadIndex + i * threadsCount + 1,
                        memoryFormat.format(((double) storage.getUsedMemory()) / (1024 * 1024)),
                        decFormat.format(totalWrite / count),
                        decFormat.format(totalRead / count),
                        decFormat.format(totalLookup / count));
                count = 0;
                totalWrite = 0.0;
                totalRead = 0.0;
            }
        }
    }
}
