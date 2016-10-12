package com.likou.passport.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/**
 * Created by jiangli on 16/6/28.
 */
@Component
public class TestCache{

    @Autowired
    ShardedJedisPool shardedJedisPool;

    public void saveTestData(String key,String value){
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis.set(key,value);

        } catch (Exception ex) {
            shardedJedisPool.returnBrokenResource(shardedJedis);
        } finally {
            shardedJedisPool.returnResource(shardedJedis);
        }
        System.err.println("TestCache---saveTestData");
    }
}
