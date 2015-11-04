package com.inatec.pgw.indepotance.storage.impl;


import com.inatec.pgw.indepotance.domain.Transaction;
import com.inatec.pgw.indepotance.storage.KeyProvider;
import org.apache.commons.codec.digest.DigestUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;

/**
 * Created by Sergey on 01.11.2015.
 */
public class SimpleKeyProvider implements KeyProvider {
    DecimalFormat decFormat = new DecimalFormat("#.##");

    public String getKey(Transaction transaction) {
        StringBuilder key = new StringBuilder();
        key.append(transaction.getMerchantID())
                .append("#").append(transaction.getCardNumber())
                .append("#").append(transaction.getTransactionCurrency())
                .append("#").append(decFormat.format(transaction.getAmount()));

        String keyString = key.toString();
        return keyString;
    }
}
