package cn.nihility.spring.redis;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static cn.nihility.spring.redis.RedisUtils.isNull;
import static cn.nihility.spring.redis.RedisUtils.releaseJedis;

/**
 * Redis Keys 操作类
 * @author muscari
 * @date 2019-06-10 15:34
 */
public class RedisKeysUtils {

    public static void delete(Jedis jedis, String key) {
        if (isNull(key)) { return; }
        jedis.del(key);
        releaseJedis(jedis);
    }

    public static void delete(Jedis jedis, Collection<String> keys) {
        if (null == keys) { return; }
        for (String key : keys) { jedis.del(key); }
        releaseJedis(jedis);
    }

    public static byte[] dump(String key) throws IOException {
        if (isNull(key)) { return null; }
        ByteOutputStream bos = new ByteOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(key);
        byte[] data = bos.getBytes();
        oos.close();
        bos.close();
        return data;
    }

    public static String deDump(byte[] key) throws IOException, ClassNotFoundException {
        if (null == key) { return null; }
        ByteInputStream bis = new ByteInputStream(key, key.length);
        ObjectInputStream ois = new ObjectInputStream(bis);
        String keyObj = (String)ois.readObject();
        ois.close();
        bis.close();
        return keyObj;
    }

    public static boolean hasKey(Jedis jedis, String key) {
        if (isNull(key)) { return false; }
        boolean exist = jedis.exists(key);
        releaseJedis(jedis);
        return exist;
    }

    public static boolean expire(Jedis jedis, String key, int time, TimeUnit timeUnit) {
        if (isNull(key)) { return false; }
        int expireTime;
        switch (timeUnit) {
            case MINUTES: expireTime = time * 60; break;
            case HOURS: expireTime = time * 60 * 60; break;
            case DAYS: expireTime = time * 60 * 60 * 24; break;
            default: expireTime = time;
        }
        Long expire = jedis.expire(key, expireTime);
        releaseJedis(jedis);
        return  expire == 1;
    }

    public static Set<String> keys(Jedis jedis, String pattern) {
        if (isNull(pattern)) { return null; }
        Set<String> keys = jedis.keys(pattern);
        releaseJedis(jedis);
        return keys;
    }

    public static boolean move(Jedis jedis, String key, int dbIndex) {
        if (isNull(key)) { return false; }
        Long move = jedis.move(key, dbIndex);
        releaseJedis(jedis);
        return 1 == move;
    }

    public static boolean persist(Jedis jedis, String key) {
        if (isNull(key)) { return false; }
        Long expire = jedis.persist(key);
        releaseJedis(jedis);
        return 1 == expire;
    }

    public static long getExpire(Jedis jedis, String key, TimeUnit timeUnit) {
        if (isNull(key)) { return -1; }
        long expire = jedis.ttl(key);
        releaseJedis(jedis);
        if (-1 == expire) { return expire; }
        switch (timeUnit) {
            case MINUTES: expire = expire / 60; break;
            case HOURS: expire = expire / 60 / 60; break;
            case DAYS: expire = expire / 60 / 60 / 24; break;
        }
        return expire;
    }

    public static String random(Jedis jedis) {
        String key = jedis.randomKey();
        releaseJedis(jedis);
        return key;
    }

    public static void rename(Jedis jedis, String oldKey, String newKey) {
        jedis.rename(oldKey, newKey);
        releaseJedis(jedis);
    }

    public static boolean renameIfAbsent(Jedis jedis, String oldKey, String newKey) {
        Long renamenx = jedis.renamenx(oldKey, newKey);
        releaseJedis(jedis);
        return 1 == renamenx;
    }

    public static String type(Jedis jedis, String key) {
        if (isNull(key)) { return null; }
        String type = jedis.type(key);
        releaseJedis(jedis);
        return type;
    }

}
