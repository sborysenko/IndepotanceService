package com.inatec.pgw.indepotance.storage.impl;

import com.inatec.pgw.indepotance.domain.Transaction;
import com.inatec.pgw.indepotance.domain.TransactionWrapper;

import java.util.Date;

/**
 * Created by Sergey on 13.11.2015.
 */
public class MixedInMemoryStorage extends AbstractInMemoryStorage {
    @Override
    public Transaction get(String transactionKey) {
        TransactionWrapper wrapper = (TransactionWrapper) getImpl(transactionKey);
        return wrapper.getTransaction();
    }

    @Override
    public void put(String transactionKey, Transaction transaction) {
        TransactionWrapper wrapper = new TransactionWrapper(null);
        wrapper.setTransactionID(transaction.getTransactionID());
        wrapper.setAddedTime(new Date());
        putImpl(transactionKey, wrapper);
    }

}
