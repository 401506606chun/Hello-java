package com.lakala.api;

import java.math.BigDecimal;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.crypto.SecretKey;

import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;

import com.lakala.api.ApiTask_queryIDCard.TradeTask;
import com.lakala.config.Config;
import com.lakala.dict.Dict;
import com.lakala.util.HttpUtil;
import com.lakala.util.SignUtil;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Test {
	
//	private static final BASE64Encoder encoder = new BASE64Encoder();
//	private static final BASE64Decoder decoder = new BASE64Decoder();

	public static void main(String[] args) throws InterruptedException {
//		Set<Map<String, Object>> list = new HashSet<Map<String, Object>>();
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("a", "1");
//		list.add(map);
//		Map<String, Object> map1 = new HashMap<String, Object>();
//		map1.put("a", "2");
//		map1.put("b", "2");
//		list.add(map1);
//		List<String> list = new ArrayList<String>();
//		list.add("a");
//		list.add("b");
//		list.add("c");
//		list.add("d");
//		list.add("e");
//		System.out.println(list.toString());
//		for(Iterator<String> ite=list.iterator(); ite.hasNext();){
//			String m = ite.next();
//			if(m.equals("b")){
//				ite.remove();
//			}
//			
//		}
//		for(String ite: list){
//			if(ite.equals("b")){
//				list.remove(ite);
//			}
//			
//		}
//		System.out.println(list.toString());
//		List<Map<String, Object>> errorList = new ArrayList<Map<String,Object>>();
//		List<Map<String, Object>> otherServiceList = new ArrayList<Map<String,Object>>();
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("a", "1");
//		Map<String, Object> map3 = new HashMap<String, Object>();
//		map3.put("d", "2");
//		errorList.add(map);
//		errorList.add(map3);
//		Map<String, Object> map1 = new HashMap<String, Object>();
//		map1.put("b", "2");
//		Map<String, Object> map2 = new HashMap<String, Object>();
//		map2.put("c", "3");
//		otherServiceList.add(map1);
//		otherServiceList.add(map2);
//		 Random random1 = new Random(100);
//		List<Map<String, Object>> secondList = new ArrayList<Map<String,Object>>();
////		for(Map<String, Object> errorData : errorList){
//			List<String> jnlNoList = new ArrayList<String>();
//			for(Map<String, Object> service : otherServiceList){
//				Map<String, Object> dataMap = new HashMap<String, Object>();
//				dataMap.putAll(service);
//				String orderJnlNo =  System.currentTimeMillis()+random1.nextInt()+"";
//				dataMap.put("_jnlNo", orderJnlNo);
//				secondList.add(dataMap);//发送的数据
//				jnlNoList.add(orderJnlNo);//错误数据流水号集合
//			}
////			errorData.put("jnlNoList", jnlNoList);
////		}
//		
//		System.out.println(secondList);
//		System.out.println(errorList);
//		String aa = "0W～0.2W";
//		System.out.println(aa.substring(0,aa.indexOf("W")));
//		BigDecimal rightBig = new BigDecimal(10); 
//		if(rightBig.compareTo(BigDecimal.valueOf(10))>=0){
//			System.out.println("1");
//		}
//		int statusCode = 445;
////		List<Integer> successCode = null;
//		List<Integer> successCode = new ArrayList<Integer>();
//		successCode.add(445);
//		if (statusCode != 200) {
//			if(!CollectionUtils.isEmpty(successCode) && successCode.contains(statusCode)){
//				System.out.println("success");
//			}else{
//				System.out.println("error");
//			}
//		}else{
//			System.out.println("success");
//		}
//		for(int i=0;i<10;i++){
//			startRequest();
//			Thread.sleep(1000);
//		}
//	}
//	
//	static void startRequest(){
//		long startTime = System.currentTimeMillis();
//		ExecutorService exec = Executors.newFixedThreadPool(300); 
//		TradeTask task;
//		for (int i = 0; i < 300; i++) {
//			task = new TradeTask("request" + i);
//			exec.execute(task);
//		}
//		exec.shutdown();
//		long endTime = System.currentTimeMillis();
//		System.out.println("总耗时:" + (endTime - startTime));
//	}
//	
//	static class TradeTask implements Runnable {
//
//
//		private String name;
//
//		public TradeTask(String name) {
//			this.name = name;
//		}
//
//		public void run() {
//			long startTime = System.currentTimeMillis();
//			String res = null;
//			String msg = null;
//			SecretKey sk = null;
//			String encryptedKey = null;
//			String reqData= null;
//			String encryptedMessage = null;
//			String publicKey="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtJOUirVQOzhQfYREZ2S/uKn6DtkN+QSGhUbgAuk5OwSNk7UETwtRq1+ZMmxjFDjdq445AN23JHddyt0WD5orhP1+QQ6zmFNwVaiuQwFR2CNgnVTRYbJo9Ls3nX5vo0dDV3mW4N5Z/zXN+q+ZWo3IC8JOS8B04NT7qDyy2zbCdvFMV3kPAsMLMJPf8Ch0Mckye6Ww+UU/s6zuGpGwjIb8uP1L8tEnHKADLIPdcP4NEZKjqSWhRTfzzf3BQHhbpxAD4sAZ8P4XQApa7izFBfrU23qd6Kn/bOZ3RbfwAeKO0dCHVox3pxmAzGAvhcZws8iW4FkMRBmxlBueJ/QPx1augQIDAQAB";
//			try {
//				PublicKey pk = CryptoUtils.getRSAPublicKey(Base64.decode(publicKey));
//				String key = getKey();
//				sk = CryptoUtils.createDESKey(key.getBytes("UTF-8"));
//				encryptedKey = new String(Base64.encode(CryptoUtils.rsaEncrypt(pk, key.getBytes("UTF-8"))));
////				reqData = reqData();
////				encryptedMessage = encoder.encode(CryptoUtils.desEncrypt(sk, reqData.getBytes("UTF-8")));
//			} catch (Exception e1) {
//				System.out.println("e1:"+e1);
//			}
//			long endTime = System.currentTimeMillis();
//			System.out.print(name + ",请求耗时(ms):" + (endTime - startTime) + ",");
//			System.out.println(msg);
//
//		}
		Map<String, Object> params =new HashMap<String, Object>();
		params.put("bankName","1");//发卡行简称
		System.out.println(params.toString());
		ss(params);
		System.out.println(params.toString());
	}
	private static void ss(Map<String, Object> params){
		params.put("bankName1","2");//发卡行简称
	}
	
	private static String getKey() {
		Random random = new Random();
		int length = 32 + random.nextInt(33);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; ++i) {
			sb.append((char) randomCharValue(random));
		}
		return sb.toString();
	}
	
	private static int randomCharValue(Random random) {
		switch (random.nextInt(3)) {
		case 0:
			return random.nextInt(10) + '0';
		case 1:
			return random.nextInt(26) + 'A';
		default:
			return random.nextInt(26) + 'a';
		}
	}

}
