package cn.nihility.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Redis 连接的工具类，主要是获取连接池和释放连接等操作
 * @author muscari
 * @date 2019-05-09 23:23
 */
public class JedisUtil {

    private static JedisUtil jedisUtil;
    private static JedisPool jedisPool;

    static {
        JedisPoolConfig config = new JedisPoolConfig();
        String host = "10.10.37.130";
        int port = 6379;
        String password = "redis";

        config.setMaxIdle(10);
        config.setMaxTotal(500);
        config.setMaxWaitMillis(1000);
        config.setTestOnBorrow(true);

        jedisPool = new JedisPool(config, host, port, 60, password);
    }

    public static void releaseJedis(Jedis jedis) {
        if (null != jedis && null != jedisPool) {
            jedis.close();
        }
    }

    public static Jedis getResources() {
        return null == jedisPool ? null : jedisPool.getResource();
    }

    public static JedisUtil getInstance() {
        if (null == jedisUtil) {
            synchronized (JedisUtil.class) {
                if (null == jedisUtil) {
                    jedisUtil = new JedisUtil();
                }
            }
        }
        return jedisUtil;
    }

    public static void releasePool() {
        if (null != jedisPool) {
            jedisPool.close();
        }
    }

}
