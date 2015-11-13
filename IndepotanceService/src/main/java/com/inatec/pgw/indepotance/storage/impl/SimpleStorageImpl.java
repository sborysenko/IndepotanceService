package com.inatec.pgw.indepotance.storage.impl;

import com.inatec.pgw.indepotance.domain.Transaction;
import com.inatec.pgw.indepotance.storage.Storage;

import java.util.concurrent.ConcurrentHashMap;


/**
 * Created by Sergey on 01.11.2015.
 */
public class SimpleStorageImpl extends AbstractInMemoryStorage {
    @Override
    public Transaction get(String transactionKey) {
        return (Transaction) getImpl(transactionKey);
    }

    @Override
    public void put(String transactionKey, Transaction transaction) {
        putImpl(transactionKey, transaction);
    }
}
