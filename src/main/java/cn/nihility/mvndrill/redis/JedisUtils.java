package cn.nihility.mvndrill.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisUtils {

	/** ��������ʱ�� */
	private final int expire = 60000;
	private static JedisPool jedisPool = null;
	private static final JedisUtils JEDIS_UTILS = new JedisUtils();

	public JedisUtils() {
	}

	static {
		JedisPoolConfig config = new JedisPoolConfig();
		// ����һ��pool�ɷ�����ٸ�jedisʵ����ͨ��pool.getResource()����ȡ��
		// �����ֵΪ-1�����ʾ�����ƣ����pool�Ѿ�������maxActive��jedisʵ�������ʱpool��״̬Ϊexhausted(�ľ�)��
		config.setMaxTotal(500);
		// ����һ��pool����ж��ٸ�״̬Ϊidle(���е�)��jedisʵ����
		config.setMaxIdle(5);
		// ��ʾ��borrow(����)һ��jedisʵ��ʱ�����ĵȴ�ʱ�䣬��������ȴ�ʱ�䣬��ֱ���׳�JedisConnectionException��
		config.setMaxWaitMillis(1000 * 100);
		// ��borrowһ��jedisʵ��ʱ���Ƿ���ǰ����validate���������Ϊtrue����õ���jedisʵ�����ǿ��õģ�
		config.setTestOnBorrow(true);
		// redis������������룺
		/*
		 * jedisPool = new JedisPool(config, JRedisPoolConfig.REDIS_IP,
		 * JRedisPoolConfig.REDIS_PORT, 10000,JRedisPoolConfig.REDIS_PASSWORD);
		 */
		// redisδ���������룺
		jedisPool = new JedisPool(config, "127.0.0.1", 6379);
	}

	public JedisPool getPool() {
		return jedisPool;
	}

	/**
	 * ��jedis���ӳ��л�ȡ��ȡjedis����
	 * 
	 * @return
	 */
	public Jedis getJedis() {
		return jedisPool.getResource();
	}

	/**
	 * ��ȡJedisUtilʵ��
	 * @return
	 */
	public static JedisUtils getInstance() {
		return JEDIS_UTILS;
	}

	/**
	 * �ͷ�jedis (�ŵ�finally��)
	 * @param jedis
	 */
	public void releaseJedis(Jedis jedis) {
		if (null != jedis && null != jedisPool) {
			/*jedis.quit();
			jedis.disconnect();*/
			jedis.close();
		}
	}

	/**
	 * ���ù���ʱ��
	 * @param key
	 * @param seconds
	 */
	public void expire(String key, int seconds) {
		if (seconds <= 0) { return; }
		Jedis jedis = getJedis();
		jedis.expire(key, seconds);
		releaseJedis(jedis);
	}

	/**
	 * ����Ĭ�Ϲ���ʱ��
	 * 
	 * @param key
	 */
	public void expire(String key) {
		expire(key, expire);
	}

}
