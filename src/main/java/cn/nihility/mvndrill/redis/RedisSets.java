package cn.nihility.mvndrill.redis;

import java.util.Set;

import redis.clients.jedis.Jedis;

public class RedisSets {

	 /**
     * ��Set���һ����¼�����member�Ѵ��ڷ���0,���򷵻�1
     * @param String  key
     * @param String member
     * @return ������,0��1
     * */
    public long sadd(String key, String member) {
        Jedis jedis = getJedis();
        long s = jedis.sadd(key, member);
        releaseJedis(jedis);
        return s;
    }

    public long sadd(byte[] key, byte[] member) {
        Jedis jedis = getJedis();
        long s = jedis.sadd(key, member);
        releaseJedis(jedis);
        return s;
    }

    /**
     * ��ȡ����key��Ԫ�ظ���
     * @param String key
     * @return Ԫ�ظ���
     * */
    public long scard(String key) {
        //ShardedJedis sjedis = getShardedJedis();
        Jedis sjedis = getJedis(); 
        long len = sjedis.scard(key);
        releaseJedis(sjedis);
        return len;
    }

    /**
     * ���شӵ�һ������еĸ�������֮��Ĳ���ĳ�Ա
     * @param String ... keys
     * @return ����ĳ�Ա����
     * */
    public Set<String> sdiff(String... keys) {
        Jedis jedis = getJedis();
        Set<String> set = jedis.sdiff(keys);
        releaseJedis(jedis);
        return set;
    }

    /**
     * ����������sdiff,�����صĲ��ǽ����,���ǽ�������洢���µļ����У����Ŀ���Ѵ��ڣ��򸲸ǡ�
     * @param String newkey �½������key
     * @param String ... keys �Ƚϵļ���
     * @return �¼����еļ�¼��
     * **/
    public long sdiffstore(String newkey, String... keys) {
        Jedis jedis = getJedis();
        long s = jedis.sdiffstore(newkey, keys);
        releaseJedis(jedis);
        return s;
    }

    /**
     * ���ظ������Ͻ����ĳ�Ա,�������һ������Ϊ�����ڻ�Ϊ�գ��򷵻ؿ�Set
     * @param String ... keys
     * @return ������Ա�ļ���
     * **/
    public Set<String> sinter(String... keys) {
        Jedis jedis = getJedis();
        Set<String> set = jedis.sinter(keys);
        releaseJedis(jedis);
        return set;
    }

    /**
     * ����������sinter,�����صĲ��ǽ����,���ǽ�������洢���µļ����У����Ŀ���Ѵ��ڣ��򸲸ǡ�
     * @param String  newkey �½������key
     * @param String ... keys �Ƚϵļ���
     * @return �¼����еļ�¼��
     * **/
    public long sinterstore(String newkey, String... keys) {
        Jedis jedis = getJedis();
        long s = jedis.sinterstore(newkey, keys);
        releaseJedis(jedis);
        return s;
    }

    /**
     * ȷ��һ��������ֵ�Ƿ����
     * @param String  key
     * @param String member Ҫ�жϵ�ֵ
     * @return ���ڷ���1�������ڷ���0
     * **/
    public boolean sismember(String key, String member) {
        //ShardedJedis sjedis = getShardedJedis();
        Jedis sjedis = getJedis(); 
        boolean s = sjedis.sismember(key, member);
        releaseJedis(sjedis);
        return s;
    }

    /**
     * ���ؼ����е����г�Ա
     * @param String  key
     * @return ��Ա����
     * */
    public Set<String> smembers(String key) {
        //ShardedJedis sjedis = getShardedJedis();
        Jedis sjedis = getJedis(); 
        Set<String> set = sjedis.smembers(key);
        releaseJedis(sjedis);
        return set;
    }

    public Set<byte[]> smembers(byte[] key) {
        //ShardedJedis sjedis = getShardedJedis();
        Jedis sjedis = getJedis();  
        Set<byte[]> set = sjedis.smembers(key);
        releaseJedis(sjedis);
        return set;
    }

    /**
     * ����Ա��Դ�����Ƴ�����Ŀ�꼯�� <br/>
     * ���Դ���ϲ����ڻ򲻰���ָ����Ա���������κβ���������0<br/>
     * ����ó�Ա��Դ������ɾ��������ӵ�Ŀ�꼯�ϣ����Ŀ�꼯���г�Ա�Ѵ��ڣ���ֻ��Դ���Ͻ���ɾ��
     * @param String  srckey Դ����
     * @param String dstkey Ŀ�꼯��
     * @param String member Դ�����еĳ�Ա
     * @return ״̬�룬1�ɹ���0ʧ��
     * */
    public long smove(String srckey, String dstkey, String member) {
        Jedis jedis = getJedis();
        long s = jedis.smove(srckey, dstkey, member);
        releaseJedis(jedis);
        return s;
    }

    /**
     * �Ӽ�����ɾ����Ա
     * @param String  key
     * @return ��ɾ���ĳ�Ա
     * */
    public String spop(String key) {
        Jedis jedis = getJedis();
        String s = jedis.spop(key);
        releaseJedis(jedis);
        return s;
    }

    /**
     * �Ӽ�����ɾ��ָ����Ա
     * @param String key
     * @param String  member Ҫɾ���ĳ�Ա
     * @return ״̬�룬�ɹ�����1����Ա�����ڷ���0
     * */
    public long srem(String key, String member) {
        Jedis jedis = getJedis();
        long s = jedis.srem(key, member);
        releaseJedis(jedis);
        return s;
    }

    /**
     * �ϲ�������ϲ����غϲ���Ľ�����ϲ���Ľ�����ϲ�������<br/>
     * @param String  ... keys
     * @return �ϲ���Ľ������
     * @see sunionstore
     * */
    public Set<String> sunion(String... keys) {
        Jedis jedis = getJedis();
        Set<String> set = jedis.sunion(keys);
        releaseJedis(jedis);
        return set;
    }

    /**
     * �ϲ�������ϲ����ϲ���Ľ����������ָ�����¼����У�����¼����Ѿ������򸲸�
     * @param String  newkey �¼��ϵ�key
     * @param String ... keys Ҫ�ϲ��ļ���
     * **/
    public long sunionstore(String newkey, String... keys) {
        Jedis jedis = getJedis();
        long s = jedis.sunionstore(newkey, keys);
        releaseJedis(jedis);
        return s;
    }

	private void releaseJedis(Jedis jedis) {
		JedisUtils.getInstance().releaseJedis(jedis);
	}

	private Jedis getJedis() {
		return JedisUtils.getInstance().getJedis();
	}
	
}
