package com.inatec.pgw.indepotance.storage.impl;

import com.inatec.pgw.indepotance.domain.Transaction;
import com.inatec.pgw.indepotance.storage.Storage;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Sergey on 03.11.2015.
 */
public class SerializedStorage extends AbstractInMemoryStorage {
    ConcurrentHashMap<String, Wrapper> map = new ConcurrentHashMap();

    public Transaction get(String transactionKey) {
        try {
            Wrapper wrapper = (Wrapper) getImpl(transactionKey);

            byte[] buffer = wrapper.getData();

            ByteArrayInputStream bain = new ByteArrayInputStream(buffer);
            ObjectInputStream in = new ObjectInputStream(bain);

            return (Transaction) in.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void put(String transactionKey, Transaction transaction) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(baos);

            out.writeObject(transaction);

            Wrapper wrapper = new Wrapper(baos.toByteArray());

            putImpl(transactionKey, wrapper);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class Wrapper {
        byte[] data;

        public Wrapper(byte[] data) {
            this.data = data;
        }

        public byte[] getData() {
            return data;
        }

        public void setData(byte[] data) {
            this.data = data;
        }
    }
}
