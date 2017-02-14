package com.xiaolan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.Set;

/**
 * Author: fallen
 * Date: 17-2-13
 * Time: 下午9:52
 * Usage:
 */
@Controller
public class test {

    @Resource
    private JedisPool jedisPool;

    @RequestMapping("/test")
    public void test() throws Exception {
        Jedis jedis = jedisPool.getResource();
        Set keys = jedis.keys("OA_*");
        Iterator<String> it = keys.iterator();
        while (it.hasNext()) {
            String s = it.next();
            System.out.println(s);
        }
    }
}