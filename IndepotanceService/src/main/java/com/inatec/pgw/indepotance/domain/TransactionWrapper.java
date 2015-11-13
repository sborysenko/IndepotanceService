package com.inatec.pgw.indepotance.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Sergey on 13.11.2015.
 */
public class TransactionWrapper implements Serializable {
    private Transaction transaction;
    private Long transactionID;
    private Date addedTime;

    public TransactionWrapper(Transaction transaction) {
        this.transaction = transaction;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public Long getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(Long transactionID) {
        this.transactionID = transactionID;
    }

    public Date getAddedTime() {
        return addedTime;
    }

    public void setAddedTime(Date addedTime) {
        this.addedTime = addedTime;
    }
}
