package cn.nihility.jedis;

import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * @author muscari
 * @date 2019-05-10 06:21
 */
public class JedisUtilTest {

    @Test
    public void testJedisPool() {
            Jedis jedis = JedisUtil.getResources();
            System.out.println("jedis con " + jedis);
            String key;
            for (int i = 0; i < 100; i++) {
                key = "redis02:" + i;
                jedis.set(key, "redis key value " + i);
                System.out.println("set key " + key);
            }
            System.out.println("set value ok.");
            JedisUtil.releaseJedis(jedis);
            JedisUtil.releasePool();
            System.out.println("exit.");
    }

}
