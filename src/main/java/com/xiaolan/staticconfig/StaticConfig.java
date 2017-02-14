package com.xiaolan.staticconfig;

import com.xiaolan.util.ConfigReader;

import javax.annotation.PostConstruct;

/**
 * Author: fallen
 * Date: 17-2-12
 * Time: 下午2:39
 * Usage:
 */
public class StaticConfig {
    public static int user_alive_time;

    public static String url4Openid;

    public static int admin_alive_time;

    public static int page_size;

    public static String upload_prefix;

    public static String bucket_name;

    public static String upload_dir;

    public static String comment_table;
}