package cn.nihility.mvndrill.redis;

import java.util.List;

import redis.clients.jedis.BinaryClient.LIST_POSITION;
import redis.clients.jedis.Jedis;
import redis.clients.util.SafeEncoder;

public class RedisLists {

	
	/**
     * List����
     * @param String key
     * @return ����
     * */
    public long llen(String key) {
        return llen(SafeEncoder.encode(key));
    }

    /**
     * List����
     * @param byte[] key
     * @return ����
     * */
    public long llen(byte[] key) {
        //ShardedJedis sjedis = getShardedJedis();
        Jedis sjedis = getJedis();  
        long count = sjedis.llen(key);
        releaseJedis(sjedis);
        return count;
    }

    /**
     * ���ǲ���,������List��ָ��λ�õ�ֵ
     * @param byte[] key
     * @param int index λ��
     * @param byte[] value ֵ
     * @return ״̬��
     * */
    public String lset(byte[] key, int index, byte[] value) {
        Jedis jedis = getJedis();
        String status = jedis.lset(key, index, value);
        releaseJedis(jedis);
        return status;
    }

    /**
     * ���ǲ���,������List��ָ��λ�õ�ֵ
     * @param key
     * @param int index λ��
     * @param String  value ֵ
     * @return ״̬��
     * */
    public String lset(String key, int index, String value) {
        return lset(SafeEncoder.encode(key), index,
                SafeEncoder.encode(value));
    }

    /**
     * ��value�����λ�ò����¼
     * @param key
     * @param LIST_POSITION   ǰ������������
     * @param String pivot ���λ�õ�����
     * @param String value ���������
     * @return ��¼����
     * */
    public long linsert(String key, LIST_POSITION where, String pivot,
            String value) {
        return linsert(SafeEncoder.encode(key), where,
                SafeEncoder.encode(pivot), SafeEncoder.encode(value));
    }

    /**
     * ��ָ��λ�ò����¼
     * @param String key
     * @param LIST_POSITION ǰ������������
     * @param byte[] pivot ���λ�õ�����
     * @param byte[] value ���������
     * @return ��¼����
     * */
    public long linsert(byte[] key, LIST_POSITION where, byte[] pivot,
            byte[] value) {
        Jedis jedis = getJedis();
        long count = jedis.linsert(key, where, pivot, value);
        releaseJedis(jedis);
        return count;
    }

    /**
     * ��ȡList��ָ��λ�õ�ֵ
     * @param String  key
     * @param int index λ�� 
     * @return ֵ
     * **/
    public String lindex(String key, int index) {
        return SafeEncoder.encode(lindex(SafeEncoder.encode(key), index));
    }

    /**
     * ��ȡList��ָ��λ�õ�ֵ 
     * @param byte[] key
     * @param int index λ��
     * @return ֵ
     * **/
    public byte[] lindex(byte[] key, int index) { 
        //ShardedJedis sjedis = getShardedJedis();
        Jedis sjedis = getJedis();  
        byte[] value = sjedis.lindex(key, index);
        releaseJedis(sjedis);
        return value;
    }

    /**
     * ��List�еĵ�һ����¼�Ƴ�List
     * @param String key
     * @return �Ƴ��ļ�¼ 
     * */
    public String lpop(String key) {
        return SafeEncoder.encode(lpop(SafeEncoder.encode(key)));
    }

    /**
     * ��List�еĵ�һ����¼�Ƴ�List
     * @param byte[] key
     * @return �Ƴ��ļ�¼
     * */
    public byte[] lpop(byte[] key) {
        Jedis jedis = getJedis();
        byte[] value = jedis.lpop(key);
        releaseJedis(jedis);
        return value;
    }

    /**
     * ��List������һ����¼�Ƴ�List
     * 
     * @param byte[] key
     * @return �Ƴ��ļ�¼
     * */
    public String rpop(String key) {
        Jedis jedis = getJedis();
        String value = jedis.rpop(key);
        releaseJedis(jedis);
        return value;
    }

    /**
     * ��Listβ��׷�Ӽ�¼
     * @param String key
     * @param String value
     * @return ��¼����
     * */
    public long lpush(String key, String value) {
        return lpush(SafeEncoder.encode(key), SafeEncoder.encode(value));
    }

    /**
     * ��Listͷ��׷�Ӽ�¼
     * @param String  key
     * @param String  value
     * @return ��¼����
     * */
    public long rpush(String key, String value) {
        Jedis jedis = getJedis();
        long count = jedis.rpush(key, value);
        releaseJedis(jedis);
        return count;
    }

    /**
     * ��Listͷ��׷�Ӽ�¼
     * @param String key
     * @param String value
     * @return ��¼����
     * */
    public long rpush(byte[] key, byte[] value) {
        Jedis jedis = getJedis();
        long count = jedis.rpush(key, value);
        releaseJedis(jedis);
        return count;
    }

    /**
     * ��List��׷�Ӽ�¼
     * @param byte[] key
     * @param byte[] value
     * @return ��¼����
     * */
    public long lpush(byte[] key, byte[] value) {
        Jedis jedis = getJedis();
        long count = jedis.lpush(key, value);
        releaseJedis(jedis);
        return count;
    }

    /**
     * ��ȡָ����Χ�ļ�¼��������Ϊ��ҳʹ��
     * @param String key
     * @param long start
     * @param long end
     * @return List
     * */
    public List<String> lrange(String key, long start, long end) {
        //ShardedJedis sjedis = getShardedJedis();
        Jedis sjedis = getJedis();   
        List<String> list = sjedis.lrange(key, start, end);
        releaseJedis(sjedis);
        return list;
    }

    /**
     * ��ȡָ����Χ�ļ�¼��������Ϊ��ҳʹ��
     * @param byte[] key
     * @param int start
     * @param int end ���Ϊ��������β����ʼ����
     * @return List
     * */
    public List<byte[]> lrange(byte[] key, int start, int end) {
        //ShardedJedis sjedis = getShardedJedis();
        Jedis sjedis = getJedis();   
        List<byte[]> list = sjedis.lrange(key, start, end);
        releaseJedis(sjedis);
        return list;
    }

    /**
     * ɾ��List��c����¼����ɾ���ļ�¼ֵΪvalue
     * @param byte[] key
     * @param int c Ҫɾ�������������Ϊ�������List��β����鲢ɾ�����ϵļ�¼
     * @param byte[] value Ҫƥ���ֵ
     * @return ɾ�����List�еļ�¼��
     * */
    public long lrem(byte[] key, int c, byte[] value) {
        Jedis jedis = getJedis();
        long count = jedis.lrem(key, c, value);
        releaseJedis(jedis);
        return count;
    }

    /**
     * ɾ��List��c����¼����ɾ���ļ�¼ֵΪvalue
     * @param String key
     * @param int c Ҫɾ�������������Ϊ�������List��β����鲢ɾ�����ϵļ�¼
     * @param String value Ҫƥ���ֵ
     * @return ɾ�����List�еļ�¼��
     * */
    public long lrem(String key, int c, String value) {
        return lrem(SafeEncoder.encode(key), c, SafeEncoder.encode(value));
    }

    /**
     * ����ɾ���ɣ�ֻ����start��end֮��ļ�¼
     * @param byte[] key
     * @param int start ��¼�Ŀ�ʼλ��(0��ʾ��һ����¼)
     * @param int end ��¼�Ľ���λ�ã����Ϊ-1���ʾ���һ����-2��-3�Դ����ƣ�
     * @return ִ��״̬��
     * */
    public String ltrim(byte[] key, int start, int end) {
        Jedis jedis = getJedis();
        String str = jedis.ltrim(key, start, end);
        releaseJedis(jedis);
        return str;
    }

    private void releaseJedis(Jedis jedis) {
		JedisUtils.getInstance().releaseJedis(jedis);
	}

	private Jedis getJedis() {
		return JedisUtils.getInstance().getJedis();
	}

	/** 
     * ����ɾ���ɣ�ֻ����start��end֮��ļ�¼
     * @param String key 
     * @param int start ��¼�Ŀ�ʼλ��(0��ʾ��һ����¼)
     * @param int end ��¼�Ľ���λ�ã����Ϊ-1���ʾ���һ����-2��-3�Դ����ƣ�
     * @return ִ��״̬��
     * */
    public String ltrim(String key, int start, int end) {
        return ltrim(SafeEncoder.encode(key), start, end);
    }
	
}
