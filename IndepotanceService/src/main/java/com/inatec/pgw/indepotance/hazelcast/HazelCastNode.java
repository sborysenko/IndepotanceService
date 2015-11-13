package com.inatec.pgw.indepotance.hazelcast;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Map;

/**
 * Created by Sergey on 06.11.2015.
 */
public class HazelCastNode {

    protected HazelCastNode() throws RemoteException {
        super();

    }

    public static void main(String [] args) throws Exception {
        Config cfg = new Config();
        HazelcastInstance instance = Hazelcast.newHazelcastInstance(cfg);

/*
        // Assign a security manager, in the event that dynamic
        // classes are loaded
        if (System.getSecurityManager() == null)
            System.setSecurityManager ( new SecurityManager() );

        // Create an instance of our power service server ...
        MemoryService svr = new MemoryServiceImpl();

        // ... and bind it with the RMI Registry
        Naming.bind ("MemoryService", svr);

        System.out.println ("Service bound....");
*/
    }

}
