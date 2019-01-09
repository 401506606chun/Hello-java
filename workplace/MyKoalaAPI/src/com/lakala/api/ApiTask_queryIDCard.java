package com.lakala.api;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import net.sf.json.JSONObject;

import com.lakala.config.Config;
import com.lakala.dict.Dict;
import com.lakala.util.HttpUtil;
import com.lakala.util.SignUtil;

public class ApiTask_queryIDCard {
	public static final SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");

	public static void main(String[] args) throws Exception {
		// 明文数据
		Map<String, String> reqMap = new HashMap<String, String>();
		reqMap.put("outOrderNo", "zx" + System.currentTimeMillis());// 上送流水号，保证唯一性
		// 调用考拉其他产品替换成相关的业务参数
		reqMap.put("idCardName", "代明明");
		reqMap.put("idCardCode", "220681198804010190");

		System.out.println("明文数据:" + reqMap);
		// 签名后数据
		Map<String, String> signMap = SignUtil.getSign(reqMap);
		signMap.put("customerId", Config.CUSTOMERID);
		// 调用考拉其他产品替换prdGrpId和prdId
		signMap.put("prdGrpId", "identityQuery");
		signMap.put("prdId", "queryIDCard");
		System.out.println("签名后数据:" + signMap);
		
		for(int i=0;i<10;i++){
			startRequest(signMap);
			Thread.sleep(1000);
		}
	}
	
	static void startRequest(Map<String, String> signMap){
		long startTime = System.currentTimeMillis();
		ExecutorService exec = Executors.newFixedThreadPool(300); 
		TradeTask task;
		for (int i = 0; i < 300; i++) {
			task = new TradeTask("request" + i, signMap);
			exec.execute(task);
		}
		exec.shutdown();
		long endTime = System.currentTimeMillis();
		System.out.println("总耗时:" + (endTime - startTime));
	}
	
	
	

	static class TradeTask implements Runnable {

		private Map<String, String> sendMap;

		private String name;

		public TradeTask(String name, Map<String, String> sendMap) {
			this.name = name;
			this.sendMap = sendMap;
		}

		public void run() {
			long startTime = System.currentTimeMillis();
			String res = null;
			String msg = null;
			try {
				res = HttpUtil.buildRequest(Config.KOALA_GATEWAY_NEW, sendMap);
			} catch (Exception e) {
				msg = "请求错误----------";
			}
			if (res != null) {
				@SuppressWarnings("unchecked")
				Map<String, Object> resMap = JSONObject.fromObject(res);
				String retCode = (String) resMap.get(Dict.RETCODE);
				String retMsg = (String) resMap.get(Dict.RETMSG);
				msg = "考拉响应码:" + retCode + ",考拉响应信息:" + retMsg;
				if (Config.SUCC_RETCODE.equals(retCode)) {
					Map<String, Object> retData = SignUtil.getSignVeryfy(resMap);
					msg = "考拉返回明文数据:" + retData;
				} else {
					msg = "考拉响应失败";
				}
			} else {
				msg = "调用考拉接口异常";
			}
			long endTime = System.currentTimeMillis();
			System.out.print(name + ",请求耗时(ms):" + (endTime - startTime) + ",");
			System.out.println(msg);

		}

	}
}
