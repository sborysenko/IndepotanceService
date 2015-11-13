package com.inatec.pgw.indepotance.storage.impl;

import com.inatec.pgw.indepotance.storage.Storage;

/**
 * Created by Sergey on 10.11.2015.
 */
public abstract class AbstractStorage implements Storage{
    Runtime runtime = Runtime.getRuntime();

    @Override
    public long getUsedMemory() {
        return (runtime.totalMemory() - runtime.freeMemory());
    }
}
