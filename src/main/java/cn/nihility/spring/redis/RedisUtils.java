package cn.nihility.spring.redis;

import redis.clients.jedis.Jedis;

/**
 * Redis 操作工具类
 * @author muscari
 * @date 2019-06-10 15:29
 */
public class RedisUtils {

    public static void releaseJedis(Jedis jedis) { if (isNotNull(jedis)) { jedis.close(); } }
    public static boolean isNull(String key) { return null == key || "".equals(key.trim()); }
    public static boolean isNotNull(String key) { return !isNull(key); }
    public static boolean isNull(Object object) { return null == object; }
    public static boolean isNotNull(Object object) { return !isNull(object); }

}
