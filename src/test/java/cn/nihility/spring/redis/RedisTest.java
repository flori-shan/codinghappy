package cn.nihility.spring.redis;

import org.junit.After;
import org.junit.Assert;
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
 * Spring Redis 集成， 测试连接和使用
 * @author muscari
 * @date 2019-06-10 15:05
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-jedis.xml"})
public class RedisTest {

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

    @Test
    public void testConnection() {
        System.out.println(jedisPoolJedis);
        System.out.println(shardedJedis);
        Assert.assertNotNull(jedisPoolJedis);
        Assert.assertNotNull(shardedJedis);

        Assert.assertEquals("redis01:11 redis key value", jedisPoolJedis.get("redis01:11"));
        Assert.assertEquals("redis01:11 redis key value", shardedJedis.get("redis01:11"));
    }

}
