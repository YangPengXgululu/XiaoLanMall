package com.xiaolan.listerner;

import com.xiaolan.staticconfig.StaticConfig;
import com.xiaolan.util.ConfigReader;
import com.xiaolan.util.JedisUtil;
import com.xiaolan.util.QiniuUtil;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import redis.clients.jedis.JedisPool;

/**
 * Author: fallen
 * Date: 17-2-14
 * Time: 上午9:45
 * Usage:
 */
public class SpringReadyLis implements ApplicationListener<ContextRefreshedEvent> {

    public void onApplicationEvent(ContextRefreshedEvent e) {
        ConfigReader.configInit();
        StaticConfig.user_alive_time = Integer.parseInt(ConfigReader.getValue("user_alive_time"));
        StaticConfig.admin_alive_time = Integer.parseInt(ConfigReader.getValue("admin_alive_time"));
        StaticConfig.url4Openid = ConfigReader.getValue("url4Openid");
        StaticConfig.upload_prefix = ConfigReader.getValue("upload_prefix");
        StaticConfig.page_size = Integer.parseInt(ConfigReader.getValue("page_size"));
        StaticConfig.bucket_name = ConfigReader.getValue("bucket_name");
        StaticConfig.upload_dir = ConfigReader.getValue("upload_dir");
        StaticConfig.comment_table = ConfigReader.getValue("comment_table");
        JedisUtil.pool = (JedisPool) e.getApplicationContext().getBean("jedisPool");
        QiniuUtil.init();
        System.out.println("配置文件加载完成");
    }
}
