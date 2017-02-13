package com.xiaolan.util;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * Author: fallen
 * Date: 17-2-10
 * Time: 下午5:04
 * Usage:
 */
public class HttpUtil {
    public static String get(String url) throws Exception {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        String res = null;
        try {
            HttpGet httpGet = new HttpGet("http://httpbin.org/get");
            CloseableHttpResponse response = httpclient.execute(httpGet);
            try {
                System.out.println(response.getStatusLine());
                HttpEntity entity = response.getEntity();
                res = EntityUtils.toString(entity);
                EntityUtils.consume(entity);
            } finally {
                response.close();
            }
        }finally {
            httpclient.close();
        }
        return res;
    }
}
