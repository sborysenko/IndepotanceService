package com.inatec.pgw.indepotance.storage.impl;

import com.inatec.pgw.indepotance.domain.Transaction;
import com.inatec.pgw.indepotance.storage.Storage;

import java.util.concurrent.ConcurrentHashMap;


/**
 * Created by Sergey on 01.11.2015.
 */
public class SimpleStorageImpl implements Storage {
    ConcurrentHashMap<String, Transaction> map = new ConcurrentHashMap<String, Transaction>();

     public Transaction get(String transactionKey) {
        return map.get(transactionKey);
    }

    public void put(String transactionKey, Transaction transaction) {
        map.put(transactionKey, transaction);
    }
}
