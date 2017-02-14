package com.xiaolan.util;

import java.io.File;

import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import com.xiaolan.staticconfig.StaticConfig;

public class QiniuUtil {
    private static String QN_ACCESS_KEY;
    private static String QN_SECRET_KEY;
    private static UploadManager uploadManager;

    public static void init() {
        QN_ACCESS_KEY = JedisUtil.getValue("QN_ACCESS_KEY");
        QN_SECRET_KEY = JedisUtil.getValue("QN_SECRET_KEY");
        Zone zone = Zone.zone0();
        Configuration config = new Configuration(zone);
        uploadManager = new UploadManager(config);
    }

    /**
     * 覆盖上传
     * 注意：由于CDN缓存问题，生效时间为至少10分钟，是至少，如果实时性要求较高，可以在URL中带上参数?v=随机数
     * 详细见：https://support.qiniu.com/hc/kb/article/134426
     */
    public static String upload(File file) throws Exception {
        String token = getUpToken(file.getName());
        //调用put方法上传
        Response resp = uploadManager.put(file, file.getName(), token);
        //打印返回的信息
        return resp.bodyString();
    }

    public static void delete(String fileName) throws Exception {
        Auth auth = Auth.create(QN_ACCESS_KEY, QN_SECRET_KEY);
        Configuration config = new Configuration(Zone.zone0());
        BucketManager bucketMgr = new BucketManager(auth, config);
        bucketMgr.delete(StaticConfig.bucket_name, fileName);
    }

    public static String getUpToken(String key) {
        Auth auth = Auth.create(QN_ACCESS_KEY, QN_SECRET_KEY);
        //创建上传对象
        return auth.uploadToken(StaticConfig.bucket_name, key, 3600, new StringMap().put("insertOnly", 0));
    }


}