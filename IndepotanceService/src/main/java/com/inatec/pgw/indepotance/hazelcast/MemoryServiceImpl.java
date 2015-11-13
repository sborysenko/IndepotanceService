package com.inatec.pgw.indepotance.hazelcast;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by Sergey on 10.11.2015.
 */
public class MemoryServiceImpl extends UnicastRemoteObject implements MemoryService {
    Runtime runtime = Runtime.getRuntime();

    protected MemoryServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public long getUsedMemory() throws RemoteException {
        return runtime.totalMemory() - runtime.freeMemory();
    }
}
