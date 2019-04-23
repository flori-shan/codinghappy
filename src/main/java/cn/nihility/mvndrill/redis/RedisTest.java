package cn.nihility.mvndrill.redis;

import java.util.Iterator;
import java.util.Set;

import redis.clients.jedis.Jedis;

public class RedisTest {

	public static void main(String[] args) {
		JedisUtils jedisUtils = JedisUtils.getInstance();

		Jedis jedis = JedisUtils.getInstance().getJedis();
		batchDeleteKeys(jedis, "redis*");
		
		System.out.println("success");

		/*jedis.set("delkey", "This key will be deleted");*/
		
		/*long del = jedis.del("delkey");
		System.out.println("del key count " + del);*/
		
		/*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
		sdf.format(new Date(System.currentTimeMillis()));
		String key = jedis.set("delkey", sdf.format(new Date(System.currentTimeMillis())));
		System.out.println("key : " + key);*/
		
		/*Random rand = new Random(System.currentTimeMillis());
		int randint = 0;
		for (int i = 0; i < 100; i++) {
			randint = rand.nextInt(100);
			System.out.println("redis: " + randint);
			jedis.set("redis:"+randint, "redis" + randint);
		}
		System.out.println("===========================");*/
		
		
		/*for (int i = 0; i < 10; i++) {
			strings.setEx(" : :testi"+i, 200, "test null");
			System.out.println(i + "==" + jedis.get(" : :testi"+i));
		}*/
		
		
		/*JSONArray ja = new JSONArray();
		for (int i = 0; i < 100; i++) {
			JSONObject jo = new JSONObject();
			jo.put("key", "key" + i);
			jo.put("value", "value" + i);
			ja.put(jo);
		}
		System.out.println(ja);

		strings.set("jsonarry", ja.toString());

		System.out.println(SerializeUtils.setJsonObject(strings.get("jsonarry")));*/

		jedisUtils.releaseJedis(jedis);
	}
	
	public static void batchDeleteKeys(Jedis jedis, String pattern) {
		Set<String> keys = jedis.keys(pattern);
		Iterator<String> sit = keys.iterator();
		while (sit.hasNext()) {
			jedis.del(sit.next());
		}
	}

}
