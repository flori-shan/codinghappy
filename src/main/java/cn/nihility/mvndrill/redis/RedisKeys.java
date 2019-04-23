package cn.nihility.mvndrill.redis;

import java.util.List;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.SortingParams;
import redis.clients.util.SafeEncoder;

public class RedisKeys {
	
	 /**
     * �������key
     */
    public String flushAll() {
        Jedis jedis = getJedis();
        String stata = jedis.flushAll();
        releaseJedis(jedis);
        return stata;
    }

    private void releaseJedis(Jedis jedis) {
		JedisUtils.getInstance().releaseJedis(jedis);
	}

	/**
     * ����key
     * @param String oldkey
     * @param String  newkey
     * @return ״̬��
     * */
    public String rename(String oldkey, String newkey) { 
        return rename(SafeEncoder.encode(oldkey), SafeEncoder.encode(newkey));
    }

    /**
     * ����key,������key������ʱ��ִ��
     * @param String oldkey
     * @param String newkey 
     * @return ״̬��
     * */
    public long renamenx(String oldkey, String newkey) {
        Jedis jedis = getJedis();
        long status = jedis.renamenx(oldkey, newkey);
        releaseJedis(jedis);
        return status;
    }

    private Jedis getJedis() {
		return JedisUtils.getInstance().getJedis();
	}

	/**
     * ����key
     * @param String oldkey
     * @param String newkey
     * @return ״̬��
     * */
    public String rename(byte[] oldkey, byte[] newkey) {
        Jedis jedis = getJedis();
        String status = jedis.rename(oldkey, newkey);
        releaseJedis(jedis);
        return status;
    }

    /**
     * ����key�Ĺ���ʱ�䣬����Ϊ��λ
     * @param String key
     * @param ʱ��,����Ϊ��λ
     * @return Ӱ��ļ�¼��
     * */
    public long expired(String key, int seconds) {
        Jedis jedis = getJedis();
        long count = jedis.expire(key, seconds);
        releaseJedis(jedis);
        return count;
    }

    /**
     * ����key�Ĺ���ʱ��,���Ǿ���Ԫ�����������α�׼ʱ�� 1970 �� 1 �� 1 �յ� 00:00:00���������������ƫ������
     * @param String key
     * @param ʱ��,����Ϊ��λ
     * @return Ӱ��ļ�¼��
     * */
    public long expireAt(String key, long timestamp) {
        Jedis jedis = getJedis();
        long count = jedis.expireAt(key, timestamp);
        releaseJedis(jedis);
        return count;
    }

    /**
     * ��ѯkey�Ĺ���ʱ��
     * @param String key
     * @return ����Ϊ��λ��ʱ���ʾ
     * */
    public long ttl(String key) {
        //ShardedJedis sjedis = getShardedJedis();
        Jedis sjedis = getJedis(); 
        long len = sjedis.ttl(key);
        releaseJedis(sjedis);
        return len;
    }

    /**
     * ȡ����key����ʱ�������
     * @param key
     * @return Ӱ��ļ�¼��
     * */
    public long persist(String key) {
        Jedis jedis = getJedis();
        long count = jedis.persist(key);
        releaseJedis(jedis);
        return count;
    }

    /**
     * ɾ��keys��Ӧ�ļ�¼,�����Ƕ��key
     * @param String  ... keys
     * @return ɾ���ļ�¼��
     * */
    public long del(String... keys) {
        Jedis jedis = getJedis();
        long count = jedis.del(keys);
        releaseJedis(jedis);
        return count;
    }

    /**
     * ɾ��keys��Ӧ�ļ�¼,�����Ƕ��key
     * @param String .. keys
     * @return ɾ���ļ�¼��
     * */
    public long del(byte[]... keys) {
        Jedis jedis = getJedis();
        long count = jedis.del(keys);
        releaseJedis(jedis);
        return count;
    }

    /**
     * �ж�key�Ƿ����
     * @param String key
     * @return boolean
     * */
    public boolean exists(String key) {
        //ShardedJedis sjedis = getShardedJedis();
        Jedis sjedis = getJedis();  
        boolean exis = sjedis.exists(key);
        releaseJedis(sjedis);
        return exis;
    }

    /**
     * ��List,Set,SortSet��������,����������ݽϴ�Ӧ����ʹ���������
     * @param String key
     * @return List<String> ���ϵ�ȫ����¼
     * **/
    public List<String> sort(String key) {
        //ShardedJedis sjedis = getShardedJedis();
        Jedis sjedis = getJedis();  
        List<String> list = sjedis.sort(key);
        releaseJedis(sjedis);
        return list;
    }

    /**
     * ��List,Set,SortSet���������limit
     * @param String key
     * @param SortingParams parame �����������ͻ�limit����ֹλ��.
     * @return List<String> ȫ���򲿷ּ�¼
     * **/
    public List<String> sort(String key, SortingParams parame) {
        Jedis sjedis = getJedis(); 
        List<String> list = sjedis.sort(key, parame);
        releaseJedis(sjedis);
        return list;
    }

    /**
     * ����ָ��key�洢������
     * @param String key
     * @return String string|list|set|zset|hash
     * **/
    public String type(String key) {
        //ShardedJedis sjedis = getShardedJedis(); 
        Jedis sjedis = getJedis();  
        String type = sjedis.type(key); 
        releaseJedis(sjedis);
        return type;
    }

    /**
     * ��������ƥ�������ģʽ�ļ�
     * @param String  key�ı��ʽ,*��ʾ���������ʾһ��
     * */
    public Set<String> keys(String pattern) {
        Jedis jedis = getJedis();
        Set<String> set = jedis.keys(pattern);
        releaseJedis(jedis);
        return set;
    }
	
}
