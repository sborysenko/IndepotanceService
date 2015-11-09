package com.inatec.pgw.indepotance.storage.impl;

import com.inatec.pgw.indepotance.domain.Transaction;
import com.inatec.pgw.indepotance.storage.Storage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author Anatoly Chernysh
 */
public class RedisStorage implements Storage {
    private Logger logger = LogManager.getLogger(RedisStorage.class);
//    public static final String REDIS_HOST = "192.168.20.187";
    public static final String REDIS_HOST = "127.0.0.1";
    public static final int REDIS_PORT = 6379;


    private RedisTemplate redisTemplate;

    public RedisStorage() {
        JedisConnectionFactory jedisConnectionFactory=new JedisConnectionFactory();
        jedisConnectionFactory.setHostName(REDIS_HOST);
        jedisConnectionFactory.setPort(REDIS_PORT);
        jedisConnectionFactory.setUsePool(true);
        jedisConnectionFactory.setTimeout(0);
        jedisConnectionFactory.afterPropertiesSet();

        this.redisTemplate = new RedisTemplate();
        this.redisTemplate.setConnectionFactory(jedisConnectionFactory);
        this.redisTemplate.afterPropertiesSet();
    }

    public Transaction get(String transactionKey) {
        try {
            return (Transaction) redisTemplate.opsForValue().get(transactionKey);
        } catch (Throwable e) {
            logger.error(e);
            return null;
        }
    }

    public void put(String transactionKey, Transaction transaction) {
        try {
            redisTemplate.opsForValue().set(transactionKey, transaction);
        } catch (Throwable e) {
            logger.error(e);
        }
    }
}