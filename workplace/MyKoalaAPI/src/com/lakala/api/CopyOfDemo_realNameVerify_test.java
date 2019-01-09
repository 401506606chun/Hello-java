package com.lakala.api;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import com.lakala.config.Config;
import com.lakala.dict.Dict;
import com.lakala.util.HttpUtil;
import com.lakala.util.SignUtil;

public class CopyOfDemo_realNameVerify_test {
	public static final SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");

	public static void main(String[] args) throws Exception {
		// 明文数据
		Map<String, String> reqMap = new HashMap<String, String>();
		reqMap.put("outOrderNo", "zx" + System.currentTimeMillis());// 上送流水号，保证唯一性
		// 调用考拉其他产品替换成相关的业务参数
		reqMap.put("mdn", "7bd4ab4d2e3dc42b75f68d7be7cbfeac");
		reqMap.put("realName", "吴昕");
		reqMap.put("idNo", "11010819970930542X");
		reqMap.put("version", "MD5");
		System.out.println("明文数据:" + reqMap);
		//		我加的
		System.out.println("签名后reqMap:" + reqMap);
		// 签名后数据
		Map<String, String> signMap = SignUtil.getSign(reqMap);
		//	我加的
		System.out.println("签名后signMap1:" + signMap);
		signMap.put("customerId", Config.CUSTOMERID);
		//		我加的
		System.out.println("签名后signMap2:" + signMap);
		// 调用考拉其他产品替换prdGrpId和prdId
		signMap.put("prdGrpId", "chinatelecom");
		signMap.put("prdId", "verifyIdNameByMdn");
		System.out.println("签名后数据:" + signMap);
		// 请求考拉api接口
		long startTime = System.currentTimeMillis();
		String res = null;
		try {
			res = HttpUtil.buildRequest(Config.KOALA_GATEWAY_NEW, signMap);
		} catch (Exception e) {
			System.out.println("请求错误----------");
		}
		if (res != null) {
			@SuppressWarnings("unchecked")
			Map<String, Object> resMap = JSONObject.fromObject(res);
			String retCode = (String) resMap.get(Dict.RETCODE);
			String retMsg = (String) resMap.get(Dict.RETMSG);
			System.out.println("考拉响应码:" + retCode + ",考拉响应信息:" + retMsg);
			if (Config.SUCC_RETCODE.equals(retCode)) {
				Map<String, Object> retData = SignUtil.getSignVeryfy(resMap);
				System.out.println("考拉返回明文数据:" + retData);
			} else {
				System.out.println("考拉响应失败");
			}
		} else {
			System.out.println("调用考拉接口异常");
		}
		long endTime = System.currentTimeMillis();

		System.out.println("总耗时:" + (endTime - startTime));

	}

}
