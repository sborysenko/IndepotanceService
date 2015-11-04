package com.inatec.pgw.indepotance.storage;

import com.inatec.pgw.indepotance.domain.Transaction;

/**
 * Created by Sergey on 01.11.2015.
 */
public interface KeyProvider {
    String getKey(Transaction transaction);
}
