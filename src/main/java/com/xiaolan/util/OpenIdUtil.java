package com.xiaolan.util;

import com.google.gson.Gson;
import com.xiaolan.po.Code2OpenId;
import com.xiaolan.staticconfig.StaticConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Author: fallen
 * Date: 17-2-10
 * Time: 下午4:53
 * Usage:
 */
public class OpenIdUtil {
    private static Logger log = LoggerFactory.getLogger("logger");

    public static String getOpenIdByCode(String code) {
        String res = null;
        String mp_appId = JedisUtil.getValue("MP_APPID");
        String mp_secret = JedisUtil.getValue("MP_SECRET");
        try {
            res = HttpUtil.get(StaticConfig.url4Openid.replace("{MP_APPID}", mp_appId).replace("{MP_SECRET}", mp_secret).replace("{CODE}", code));
        } catch (Exception e) {
            log.error("getOpenIdByCode()：获取openId失败，原因是{}", e.getMessage());
        }
        return new Gson().fromJson(res, Code2OpenId.class).getOpenid();
    }
}