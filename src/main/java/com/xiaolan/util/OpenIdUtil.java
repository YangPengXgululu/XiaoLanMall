package com.xiaolan.util;

import com.google.gson.Gson;
import com.xiaolan.po.Code2OpenId;
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

    private static final String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx6211d324a34ac687&secret=0757876d0170ece43e0723ef97b28e3d&code={CODE}&grant_type=authorization_code";

//    https://api.weixin.qq.com/sns/jscode2session?appid=wx6211d324a34ac687&secret=0757876d0170ece43e0723ef97b28e3d&js_code=&grant_type=authorization_code
    public static String getOpenIdByCode(String code) {
        String res = null;
        try {
            res = HttpUtil.get(url.replace("{CODE}",code));
        } catch (Exception e) {
            log.error("getOpenIdByCode()：Http请求发起失败，原因是{}", e.getMessage());
        }
        return new Gson().fromJson(res, Code2OpenId.class).getOpenid();
    }
}