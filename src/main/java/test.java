import com.xiaolan.util.QiniuUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.File;
import java.util.Iterator;
import java.util.Set;

/**
 * Author: fallen
 * Date: 17-2-13
 * Time: 下午9:43
 * Usage:
 */
public class test {
    public static void main(String[] args) throws Exception {
        ApplicationContext ac = new ClassPathXmlApplicationContext(new String[]{"classpath:spring/applicationContext-dao.xml",
                "classpath:spring/applicationContext-service.xml",
                "classpath:spring/applicationContext-transaction.xml",
                "classpath:spring/springMVC.xml",
                "classpath:mybatis/SqlMapperConfig.xml"});
        JedisPool pool = (JedisPool) ac.getBean("jedisPool");
//        jedis.set("OA_APPID", "wx6211d324a34ac687");
//        jedis.set("OA_SECRET", "0757876d0170ece43e0723ef97b28e3d");
//        jedis.set("MP_APPID", "wx183a64dcc6df7b8c");
//        jedis.set("MP_SECRET", "4c710b7c39ac163ba7a7b4efd98e5029");
//        jedis.setex("name", 1000, "fallen");
//        jedis.expire("name", 1000);

        Jedis jedis = pool.getResource();
        Set keys = jedis.keys("OA_*");

        Iterator<String> it = keys.iterator();

        while (it.hasNext()) {
            String s = it.next();
            System.out.println(s);
        }


        jedis.close();
    }
}
