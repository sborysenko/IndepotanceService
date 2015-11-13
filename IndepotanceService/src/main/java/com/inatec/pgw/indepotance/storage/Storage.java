package com.inatec.pgw.indepotance.storage;

import com.inatec.pgw.indepotance.domain.Transaction;

/**
 * Created by Sergey on 01.11.2015.
 */
public interface Storage {
    Transaction get(String transactionKey);
    void put (String transactionKey, Transaction transaction);
    boolean exists(String transactionKey);
    long getUsedMemory();
}
