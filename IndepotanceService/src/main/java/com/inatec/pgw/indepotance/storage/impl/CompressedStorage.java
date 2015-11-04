package com.inatec.pgw.indepotance.storage.impl;

import com.inatec.pgw.indepotance.domain.Transaction;
import com.inatec.pgw.indepotance.storage.Storage;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * Created by Sergey on 03.11.2015.
 */
public class CompressedStorage implements Storage {
    ConcurrentHashMap<String, Wrapper> map = new ConcurrentHashMap();

    public Transaction get(String transactionKey) {
        try {
            Wrapper wrapper = map.get(transactionKey);

            byte[] buffer = wrapper.getData();

            ByteArrayInputStream bain = new ByteArrayInputStream(buffer);
            GZIPInputStream gin = new GZIPInputStream(bain);
            ObjectInputStream in = new ObjectInputStream(gin);

            Transaction transaction = (Transaction) in.readObject();

            in.close();
            gin.close();
            bain.close();

            return transaction;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void put(String transactionKey, Transaction transaction) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            GZIPOutputStream gout = new GZIPOutputStream(baos);
            ObjectOutputStream out = new ObjectOutputStream(gout);

            out.writeObject(transaction);

            out.flush();
            out.close();
            gout.close();
            baos.close();

            map.put(transactionKey, new Wrapper(baos.toByteArray()));
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
