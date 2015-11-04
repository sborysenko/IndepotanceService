package com.inatec.pgw.indepotance.storage.impl;

import com.inatec.pgw.indepotance.domain.Transaction;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * Created by Sergey on 03.11.2015.
 */
public class HashKeyProvider extends SimpleKeyProvider {
    @Override
    public String getKey(Transaction transaction) {
        String keyString = super.getKey(transaction);

        String digestString =  DigestUtils.sha256Hex(keyString);

        return digestString;

    }
}
