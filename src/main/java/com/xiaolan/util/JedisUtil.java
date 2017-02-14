package com.xiaolan.util;


import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Set;

/**
 * Author: fallen
 * Date: 17-2-12
 * Time: 下午2:12
 * Usage:
 */
public class JedisUtil {

    public static JedisPool pool;

    @Resource
    private JedisPool jedisPool;

    public Jedis getJedis() {
        return pool.getResource();
    }

    public static void setValue(String key, String value) {
        Jedis jedis = pool.getResource();
        jedis.set(key, value);
        jedis.close();
    }

    public static void setValue(String key, String value, int len) {
        Jedis jedis = pool.getResource();
        jedis.setex(key, len, value);
        jedis.close();
    }

    public static String getValue(String key) {
        Jedis jedis = pool.getResource();
        String res = jedis.get(key);
        jedis.close();
        return res;
    }

    public static void reborn(String key, int len) {
        if (key != null) {
            Jedis jedis = pool.getResource();
            jedis.expire(key, len);
            jedis.close();
        }
    }

    public static Set<String> FuzzySeach(String pattern) {
        Set<String> keys = null;
        if (pattern != null) {
            Jedis jedis = pool.getResource();
            keys = jedis.keys(pattern);
            jedis.close();
        }
        return keys;
    }
}