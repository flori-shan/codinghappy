package cn.nihility.mvndrill.redis;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.json.JSONArray;

public class SerializeUtils {
	
	public static void main(String[] args) {
		
		String st = "Hello";
		byte[] serialize = serialize(st);
		System.out.println(serialize);
		Object obj = unserialize(serialize);
		System.out.println(obj);
		
		Inner in = new Inner("love", "adderss");
		byte[] serializes = serialize(in);
		System.out.println(serializes);
		Object obj1 = unserialize(serializes);
		System.out.println(obj1);
		
		
	}
	
	public static class Inner implements Serializable {
		private static final long serialVersionUID = -633377798279971516L;
		private String name;
		private String address;
		
		public Inner(String name, String address) {
			this.name = name;
			this.address = address;
		}
		
		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("SerializeUtils [name=");
			builder.append(name);
			builder.append(", address=");
			builder.append(address);
			builder.append("]");
			return builder.toString();
		}

	}
	
	/**
	 * ���л�
	 * @param object
	 * @return
	 */
	public static byte[] serialize(Object object) {
		ObjectOutputStream oos = null;
		ByteArrayOutputStream baos = null;
		try {
			// ���л�
			baos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(baos);
			oos.writeObject(object);
			byte[] bytes = baos.toByteArray();
			return bytes;
		} catch (Exception e) { }
		return null;
	}

	/**
	 * �����л�
	 * 
	 * @param bytes
	 * @return
	 */
	public static Object unserialize(byte[] bytes) {
		ByteArrayInputStream bais = null;
		try {
			// �����л�
			bais = new ByteArrayInputStream(bytes);
			ObjectInputStream ois = new ObjectInputStream(bais);
			return ois.readObject();
		} catch (Exception e) { }
		return null;
	}
	
	public static String getJsonString(JSONArray value){
       return value.toString();
    }
    public static JSONArray setJsonObject(String value){
        return new JSONArray(value);
    }
    
}
