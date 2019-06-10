package cn.nihility.spring.redis;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/**
 * @author muscari
 * @date 2019-06-10 15:56
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-jedis.xml"})
public class RedisKeysUtilsTest {

    @Autowired
    private JedisPool jedisPool;
    @Autowired
    private ShardedJedisPool shardedJedisPool;

    private Jedis jedisPoolJedis;
    private ShardedJedis shardedJedis;

    @Before
    public void getJedisConnection() {
        jedisPoolJedis = jedisPool.getResource();
        shardedJedis = shardedJedisPool.getResource();
    }

    @After
    public void releaseResources() {
        if (jedisPoolJedis != null) { jedisPoolJedis.close(); }
        if (shardedJedis != null) { shardedJedis.close(); }
        if (jedisPool != null) { jedisPool.close(); jedisPool = null; }
        if (shardedJedisPool != null) { shardedJedisPool.close(); shardedJedisPool = null; }
    }

    public Jedis getJedis() { return jedisPool.getResource(); }
    public ShardedJedis getShardedJedis() { return shardedJedisPool.getResource(); }

    @Test
    public void delete() {
        RedisKeysUtils.delete(getJedis(), "redis01:11");
    }

}