package com.johnny.dao.cache;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import com.johnny.entity.SecKill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisDao {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final JedisPool jedisPool;

    private RuntimeSchema<SecKill> schema = RuntimeSchema.createFrom(SecKill.class);

    public RedisDao(String ip, int port) {
        jedisPool = new JedisPool(ip, port);
    }

    public SecKill getSecKill(long secKillId) {

        try {
            Jedis jedis = jedisPool.getResource();

            try {
                String key = "secKill:" + secKillId;
                byte[] bytes = jedis.get(key.getBytes());
                if (bytes != null) {
                    SecKill secKill = schema.newMessage();
                    ProtostuffIOUtil.mergeFrom(bytes, secKill, schema);
                    logger.info("从redis获取到的seckill: "+secKill);
                    return secKill;
                }
            } finally {
                jedis.close();
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    public String putSecKill(SecKill secKill) {
        try {
            Jedis jedis = jedisPool.getResource();

            try {
                String key = "secKill:" + secKill.getSecKillId();
                byte[] bytes = ProtostuffIOUtil.toByteArray(secKill, schema, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
                //超时缓存,保证数据一致性
                int timeOut = 60 * 60;//1h
                String result = jedis.setex(key.getBytes(), timeOut, bytes);
                logger.info("放入redis中的secKill: "+secKill);
                return result;
            } finally {
                jedis.close();
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }
}
