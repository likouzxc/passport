package com.likou.passport.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/**
 * Created by jiangli on 16/6/28.
 */
@Component
public class CaptchaCache {

    private final String CAPTCHA = "captcha_";

    @Autowired
    ShardedJedisPool shardedJedisPool;

    public void saveCaptcha(String sessionID,String code){
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();

            shardedJedis.set(CAPTCHA +sessionID,code);
            shardedJedis.expire(sessionID,60);

        } catch (Exception ex) {
            shardedJedisPool.returnBrokenResource(shardedJedis);
        } finally {
            shardedJedisPool.returnResource(shardedJedis);
        }
    }
    public String getCode(String sessionID){
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();

            String code = shardedJedis.get(CAPTCHA +sessionID);
//            shardedJedis.del(CAPTCHA +sessionID);

            return code;

        } catch (Exception ex) {
            shardedJedisPool.returnBrokenResource(shardedJedis);
            return null;
        } finally {
            shardedJedisPool.returnResource(shardedJedis);
        }
    }
}
