package com.lakala.api;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import com.lakala.config.Config;
import com.lakala.dict.Dict;
import com.lakala.util.HttpUtil;
import com.lakala.util.SignUtil;

public class Demo_qryBankCardBy3Element {
	public static final SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");

	public static void main(String[] args) throws Exception {
		// 明文数据
		Map<String, String> reqMap = new HashMap<String, String>();
		reqMap.put("outOrderNo", "zx" + System.currentTimeMillis());// 上送流水号，保证唯一性
		// 调用考拉其他产品替换成相关的业务参数
		reqMap.put("name", "代明明");
		reqMap.put("accountNo", "6228480500861233451");
		reqMap.put("idCardCore", "220681198804010190");
		System.out.println("明文数据:" + reqMap);
		// 签名后数据
		Map<String, String> signMap = SignUtil.getSign(reqMap);
		signMap.put("customerId", Config.CUSTOMERID);
		// 调用考拉其他产品替换prdGrpId和prdId
		signMap.put("prdGrpId", "bankCardQuery");
		signMap.put("prdId", "qryBankCardBy3Element");
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
