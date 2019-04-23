package cn.nihility.mvndrill.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Jedis;

public class RedisHash {

	
	/**
     * ��hash��ɾ��ָ���Ĵ洢
     * @param String key
     * @param String  fieid �洢������
     * @return ״̬�룬1�ɹ���0ʧ��
     * */
    public long hdel(String key, String fieid) {
        Jedis jedis = getJedis();
        long s = jedis.hdel(key, fieid);
        releaseJedis(jedis);
        return s;
    }

    public long hdel(String key) {
        Jedis jedis = getJedis();
        long s = jedis.del(key);
        releaseJedis(jedis);
        return s;
    }

    /**
     * ����hash��ָ���Ĵ洢�Ƿ����
     * @param String key
     * @param String  fieid �洢������
     * @return 1���ڣ�0������
     * */
    public boolean hexists(String key, String fieid) {
        //ShardedJedis sjedis = getShardedJedis();
        Jedis sjedis = getJedis(); 
        boolean s = sjedis.hexists(key, fieid);
        releaseJedis(sjedis);
        return s;
    }

    /**
     * ����hash��ָ���洢λ�õ�ֵ
     * 
     * @param String key
     * @param String fieid �洢������
     * @return �洢��Ӧ��ֵ
     * */
    public String hget(String key, String fieid) {
        //ShardedJedis sjedis = getShardedJedis();
        Jedis sjedis = getJedis(); 
        String s = sjedis.hget(key, fieid);
        releaseJedis(sjedis);
        return s;
    }

    public byte[] hget(byte[] key, byte[] fieid) {
        //ShardedJedis sjedis = getShardedJedis();
        Jedis sjedis = getJedis(); 
        byte[] s = sjedis.hget(key, fieid);
        releaseJedis(sjedis);
        return s;
    }

    /**
     * ��Map����ʽ����hash�еĴ洢��ֵ
     * @param String    key
     * @return Map<Strinig,String>
     * */
    public Map<String, String> hgetAll(String key) {
        //ShardedJedis sjedis = getShardedJedis();
        Jedis sjedis = getJedis(); 
        Map<String, String> map = sjedis.hgetAll(key);
        releaseJedis(sjedis);
        return map;
    }

    /**
     * ���һ����Ӧ��ϵ
     * @param String  key
     * @param String fieid
     * @param String value
     * @return ״̬�� 1�ɹ���0ʧ�ܣ�fieid�Ѵ��ڽ����£�Ҳ����0
     * **/
    public long hset(String key, String fieid, String value) {
        Jedis jedis = getJedis();
        long s = jedis.hset(key, fieid, value);
        releaseJedis(jedis);
        return s;
    }

    public long hset(String key, String fieid, byte[] value) {
        Jedis jedis = getJedis();
        long s = jedis.hset(key.getBytes(), fieid.getBytes(), value);
        releaseJedis(jedis);
        return s;
    }

    /**
     * ��Ӷ�Ӧ��ϵ��ֻ����fieid������ʱ��ִ��
     * @param String key
     * @param String fieid
     * @param String value
     * @return ״̬�� 1�ɹ���0ʧ��fieid�Ѵ�
     * **/
    public long hsetnx(String key, String fieid, String value) {
        Jedis jedis = getJedis();
        long s = jedis.hsetnx(key, fieid, value);
        releaseJedis(jedis);
        return s;
    }

    /**
     * ��ȡhash��value�ļ���
     * 
     * @param String
     *            key
     * @return List<String>
     * */
    public List<String> hvals(String key) {
        //ShardedJedis sjedis = getShardedJedis();
        Jedis sjedis = getJedis(); 
        List<String> list = sjedis.hvals(key);
        releaseJedis(sjedis);
        return list;
    }

    /**
     * ��ָ���Ĵ洢λ�ü���ָ�������֣��洢λ�õ�ֵ�����תΪ��������
     * @param String  key
     * @param String  fieid �洢λ��
     * @param String long value Ҫ���ӵ�ֵ,�����Ǹ���
     * @return ����ָ�����ֺ󣬴洢λ�õ�ֵ
     * */
    public long hincrby(String key, String fieid, long value) {
        Jedis jedis = getJedis();
        long s = jedis.hincrBy(key, fieid, value);
        releaseJedis(jedis);
        return s;
    }

    /**
     * ����ָ��hash�е����д洢����,����Map�е�keySet����
     * @param String key
     * @return Set<String> �洢���Ƶļ���
     * */
    public Set<String> hkeys(String key) {
        //ShardedJedis sjedis = getShardedJedis();
        Jedis sjedis = getJedis(); 
        Set<String> set = sjedis.hkeys(key);
        releaseJedis(sjedis);
        return set;
    }

    /**
     * ��ȡhash�д洢�ĸ���������Map��size����
     * @param String  key
     * @return long �洢�ĸ���
     * */
    public long hlen(String key) {
        //ShardedJedis sjedis = getShardedJedis();
        Jedis sjedis = getJedis();  
        long len = sjedis.hlen(key);
        releaseJedis(sjedis);
        return len;
    }

    /**
     * ���ݶ��key����ȡ��Ӧ��value������List,���ָ����key������,List��Ӧλ��Ϊnull
     * @param String  key
     * @param String ... fieids �洢λ��
     * @return List<String>
     * */
    public List<String> hmget(String key, String... fieids) {
        //ShardedJedis sjedis = getShardedJedis();
        Jedis sjedis = getJedis(); 
        List<String> list = sjedis.hmget(key, fieids);
        releaseJedis(sjedis);
        return list;
    }

    public List<byte[]> hmget(byte[] key, byte[]... fieids) {
        //ShardedJedis sjedis = getShardedJedis();
        Jedis sjedis = getJedis();  
        List<byte[]> list = sjedis.hmget(key, fieids);
        releaseJedis(sjedis);
        return list;
    }

    /**
     * ��Ӷ�Ӧ��ϵ�������Ӧ��ϵ�Ѵ��ڣ��򸲸�
     * @param Strin   key
     * @param Map <String,String> ��Ӧ��ϵ
     * @return ״̬���ɹ�����OK
     * */
    public String hmset(String key, Map<String, String> map) {
        Jedis jedis = getJedis();
        String s = jedis.hmset(key, map);
        releaseJedis(jedis);
        return s;
    }

    /**
     * ��Ӷ�Ӧ��ϵ�������Ӧ��ϵ�Ѵ��ڣ��򸲸�
     * @param Strin key
     * @param Map <String,String> ��Ӧ��ϵ
     * @return ״̬���ɹ�����OK
     * */
    public String hmset(byte[] key, Map<byte[], byte[]> map) {
        Jedis jedis = getJedis();
        String s = jedis.hmset(key, map);
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
