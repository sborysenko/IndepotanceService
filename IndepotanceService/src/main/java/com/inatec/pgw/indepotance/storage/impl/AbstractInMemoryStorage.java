package com.inatec.pgw.indepotance.storage.impl;

import com.inatec.pgw.indepotance.domain.Transaction;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Sergey on 11.11.2015.
 */
public abstract class AbstractInMemoryStorage extends AbstractStorage {
    ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<String, Object>();

    public Object getImpl(String transactionKey) {
        return map.get(transactionKey);
    }

    public void putImpl(String transactionKey, Object value) {
        map.put(transactionKey, value);
    }

    @Override
    public boolean exists(String transactionKey) {
        return map.containsKey(transactionKey);
    }
}
