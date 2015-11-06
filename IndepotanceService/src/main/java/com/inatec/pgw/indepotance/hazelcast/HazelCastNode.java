package com.inatec.pgw.indepotance.hazelcast;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

import java.util.Map;

/**
 * Created by Sergey on 06.11.2015.
 */
public class HazelCastNode {
    public static void main(String [] args) {
        Config cfg = new Config();
        HazelcastInstance instance = Hazelcast.newHazelcastInstance(cfg);

    }
}
