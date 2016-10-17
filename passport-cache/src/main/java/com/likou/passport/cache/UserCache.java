package com.likou.passport.cache;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/**
 * Created by jiangli on 16/6/28.
 */
@Component
public class UserCache {

    private final String LOGIN = "login_";
    @Autowired
    ShardedJedisPool shardedJedisPool;

    public void login(String sessionID,String uuid){
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();

            shardedJedis.set(LOGIN+sessionID,uuid);
            shardedJedis.set(LOGIN+uuid,sessionID);
            shardedJedis.expire(sessionID,60*30);

        } catch (Exception ex) {
            shardedJedisPool.returnBrokenResource(shardedJedis);
        } finally {
            shardedJedisPool.returnResource(shardedJedis);
        }
    }
    public boolean isLogin(String sessionID , String uuid){
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();

            String redis_sessionID = shardedJedis.get(LOGIN+uuid);
            String redis_uuid = shardedJedis.get(LOGIN+sessionID);

            if(StringUtils.isNotBlank(redis_sessionID) && StringUtils.isNotBlank(redis_uuid)
                && redis_sessionID.equals(sessionID) && redis_uuid.equals(uuid)){

                return true;
            }else{
                return false;
            }

        } catch (Exception ex) {
            shardedJedisPool.returnBrokenResource(shardedJedis);
            return false;
        } finally {
            shardedJedisPool.returnResource(shardedJedis);
        }
    }
    public void logout(String sessionID,String uuid){
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();

            shardedJedis.del(LOGIN+uuid);
            shardedJedis.del(LOGIN+sessionID);

        } catch (Exception ex) {
            shardedJedisPool.returnBrokenResource(shardedJedis);
        } finally {
            shardedJedisPool.returnResource(shardedJedis);
        }
    }
}
