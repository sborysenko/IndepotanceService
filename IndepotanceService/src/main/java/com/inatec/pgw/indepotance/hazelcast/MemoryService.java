package com.inatec.pgw.indepotance.hazelcast;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Sergey on 10.11.2015.
 */
public interface MemoryService extends Remote{
    long getUsedMemory() throws RemoteException;
}
