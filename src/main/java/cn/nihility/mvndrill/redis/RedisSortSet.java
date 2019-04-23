package cn.nihility.mvndrill.redis;

import java.util.Set;

import redis.clients.jedis.Jedis;

public class RedisSortSet {
	
	/**
     * �򼯺�������һ����¼,������ֵ�Ѵ��ڣ����ֵ��Ӧ��Ȩ�ؽ�����Ϊ�µ�Ȩ��
     * @param String  key
     * @param double score Ȩ��
     * @param String  member Ҫ�����ֵ��
     * @return ״̬�� 1�ɹ���0�Ѵ���member��ֵ
     * */
    public long zadd(String key, double score, String member) {
        Jedis jedis = getJedis();
        long s = jedis.zadd(key, score, member);
        releaseJedis(jedis);
        return s;
    }

    /*public long zadd(String key, Map<Double, String> scoreMembers) {
        Jedis jedis = getJedis();
        long s = jedis.zadd(key, scoreMembers);
        releaseJedis(jedis);
        return s;
    }*/

    /**
     * ��ȡ������Ԫ�ص�����
     * @param String  key
     * @return �������0�򼯺ϲ�����
     * */
    public long zcard(String key) {
        //ShardedJedis sjedis = getShardedJedis();
        Jedis sjedis = getJedis();
        long len = sjedis.zcard(key);
        releaseJedis(sjedis);
        return len;
    }

    /**
     * ��ȡָ��Ȩ�������ڼ��ϵ�����
     * @param String key
     * @param double min ��С����λ��
     * @param double max �������λ��
     * */
    public long zcount(String key, double min, double max) {
        //ShardedJedis sjedis = getShardedJedis();
        Jedis sjedis = getJedis();
        long len = sjedis.zcount(key, min, max);
        releaseJedis(sjedis);
        return len;
    }

    /**
     * ���set�ĳ���
     * 
     * @param key
     * @return
     */
    public long zlength(String key) {
        long len = 0;
        Set<String> set = zrange(key, 0, -1);
        len = set.size();
        return len;
    }

    /**
     * Ȩ�����Ӹ���ֵ�����������member�Ѵ���
     * @param String  key
     * @param double score Ҫ����Ȩ��
     * @param String  member Ҫ�����ֵ
     * @return �����Ȩ��
     * */
    public double zincrby(String key, double score, String member) {
        Jedis jedis = getJedis();
        double s = jedis.zincrby(key, score, member);
        releaseJedis(jedis);
        return s;
    }

    /**
     * ����ָ��λ�õļ���Ԫ��,0Ϊ��һ��Ԫ�أ�-1Ϊ���һ��Ԫ��
     * @param String key
     * @param int start ��ʼλ��(����)
     * @param int end ����λ��(����)
     * @return Set<String>
     * */
    public Set<String> zrange(String key, int start, int end) {
        //ShardedJedis sjedis = getShardedJedis();
        Jedis sjedis = getJedis(); 
        Set<String> set = sjedis.zrange(key, start, end);
        releaseJedis(sjedis);
        return set;
    }

    /**
     * ����ָ��Ȩ�������Ԫ�ؼ���
     * @param String key
     * @param double min ����Ȩ��
     * @param double max ����Ȩ��
     * @return Set<String>
     * */
    public Set<String> zrangeByScore(String key, double min, double max) {
        //ShardedJedis sjedis = getShardedJedis();
        Jedis sjedis = getJedis(); 
        Set<String> set = sjedis.zrangeByScore(key, min, max);
        releaseJedis(sjedis);
        return set;
    }

    /**
     * ��ȡָ��ֵ�ڼ����е�λ�ã���������ӵ͵���
     * @see zrevrank
     * @param String key
     * @param String member
     * @return long λ��
     * */
    public long zrank(String key, String member) {
        //ShardedJedis sjedis = getShardedJedis();
        Jedis sjedis = getJedis(); 
        long index = sjedis.zrank(key, member);
        releaseJedis(sjedis);
        return index;
    }

    /**
     * ��ȡָ��ֵ�ڼ����е�λ�ã���������Ӹߵ���
     * @see zrank
     * @param String key
     * @param String member
     * @return long λ��
     * */
    public long zrevrank(String key, String member) {
        //ShardedJedis sjedis = getShardedJedis();
        Jedis sjedis = getJedis(); 
        long index = sjedis.zrevrank(key, member);
        releaseJedis(sjedis);
        return index;
    }

    /**
     * �Ӽ�����ɾ����Ա
     * @param String key
     * @param String member 
     * @return ����1�ɹ�
     * */
    public long zrem(String key, String member) {
        Jedis jedis = getJedis();
        long s = jedis.zrem(key, member);
        releaseJedis(jedis);
        return s;
    }

    /**
     * ɾ��
     * @param key
     * @return
     */
    public long zrem(String key) {
        Jedis jedis = getJedis();
        long s = jedis.del(key);
        releaseJedis(jedis);
        return s;
    }

    /**
     * ɾ������λ�������Ԫ��
     * @param String  key
     * @param int start ��ʼ���䣬��0��ʼ(����)
     * @param int end ��������,-1Ϊ���һ��Ԫ��(����)
     * @return ɾ��������
     * */
    public long zremrangeByRank(String key, int start, int end) {
        Jedis jedis = getJedis();
        long s = jedis.zremrangeByRank(key, start, end);
        releaseJedis(jedis);
        return s;
    }

    /**
     * ɾ������Ȩ�������Ԫ��
     * @param String key
     * @param double min ����Ȩ��(����)
     * @param double max ����Ȩ��(����)
     * @return ɾ��������
     * */
    public long zremrangeByScore(String key, double min, double max) {
        Jedis jedis = getJedis();
        long s = jedis.zremrangeByScore(key, min, max);
        releaseJedis(jedis);
        return s;
    }

    /**
     * ��ȡ���������Ԫ�أ�ԭʼ����Ȩ���ɸߵ�������
     * @param String  key
     * @param int start
     * @param int end
     * @return Set<String>
     * */
    public Set<String> zrevrange(String key, int start, int end) {
        //ShardedJedis sjedis = getShardedJedis();
        Jedis sjedis = getJedis(); 
        Set<String> set = sjedis.zrevrange(key, start, end);
        releaseJedis(sjedis);
        return set;
    }

    /**
     * ��ȡ����ֵ�ڼ����е�Ȩ��
     * @param String  key
     * @param memeber
     * @return double Ȩ��
     * */
    public double zscore(String key, String memebr) {
        //ShardedJedis sjedis = getShardedJedis();
        Jedis sjedis = getJedis(); 
        Double score = sjedis.zscore(key, memebr);
        releaseJedis(sjedis);
        if (score != null) { return score; }
        return 0;
    }

	private void releaseJedis(Jedis sjedis) {
		JedisUtils.getInstance().releaseJedis(sjedis);
	}

	private Jedis getJedis() {
		return JedisUtils.getInstance().getJedis();
	}
	
}
