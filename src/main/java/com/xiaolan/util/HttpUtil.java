package com.xiaolan.util;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Author: fallen
 * Date: 17-2-10
 * Time: 下午5:04
 * Usage:
 */
public class HttpUtil {
    public static String get(String url) throws Exception {
        String res = null;
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpGet httpGet = new HttpGet("http://httpbin.org/get");
            CloseableHttpResponse response = httpclient.execute(httpGet);
            try {
                if (response.getStatusLine().getStatusCode() == 200) {
                    HttpEntity entity = response.getEntity();
                    res = EntityUtils.toString(entity);
                    EntityUtils.consume(entity);
                }
            } finally {
                response.close();
            }
        } finally {
            httpclient.close();
        }
        return res;
    }

    public static String post(String url, Map<String, String> formFileds) throws Exception {
        String res = null;
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>(formFileds.size());
        for (Map.Entry<String, String> entry : formFileds.entrySet()) {
            nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        httpPost.setEntity(new UrlEncodedFormEntity(nvps));
        CloseableHttpResponse response = httpclient.execute(httpPost);
        try {
            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();
                res = EntityUtils.toString(entity);
                EntityUtils.consume(entity);
            }
        } finally {
            response.close();
            httpclient.close();
        }
        return res;
    }
}
