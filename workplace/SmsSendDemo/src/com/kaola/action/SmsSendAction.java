package com.kaola.action;

import java.util.HashMap;
import java.util.Map;

import com.kaola.util.BaseHttpClient;
import com.kaola.util.JsonUtil;
import com.kaola.util.RsaUtil;

/**
 * 短信模拟发送类
 * 
 * 发送流程：
 * 1、客户根据我方提供的接口文档拼装参数，http请求接口(http://123.124.162.36:9080/dv/smsBatchApply.do?_t=json)地址，获取我方响应状态值(result：00 成功；01：失败)
 * 2、我方根据客户的customerId 将客户提供的公钥配置到cisdb.EENTERPRISEKEY表中(公钥由客户私下提供，手动配置到数据表)
 * 3、根据customerId查询cisdb.EENTERPRISEKEY表，获取MERPUBKEY字段，判断公钥是否为空，为空，程序返回提示语，并结束当前请求；反之，调用Rsa解密算法，解密业务数据(业务数据由客户方通过私钥加密生成)，
 * 判断业务数据是否为空，为空，程序返回提示语，并结束当前请求；反之，生成batchId批次号，保存客户提供的数据，并返回。
 * @author chengxuehong
 *
 */
public class SmsSendAction {
	
	private static String charset = "UTF-8";
	
	/**
	 * result 00：成功；01：失败
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		send();
	}
	
	//模拟发送
	public static String send() throws Exception {
		Map<String, Object> parMap = new HashMap<String, Object>();
		parMap.put("mobile", "13771767173");//手机号；多个号码用半角英文逗号隔开,一次最多不能超过 2000 个
		parMap.put("userJnlno", "1533353323429");//用户上送流水号；用户自己定义，不能重复，不能超过 64 位
		parMap.put("smsSign", "【考拉征信】");//短信签名；短信签名长度不超过 20 个汉字（包括 20）, 每个英文或阿拉伯字符或标点符号算1个汉字注：短信签名必须带【】格式，如：【考拉征信】
		parMap.put("smsContent", "考拉征信祝您阖家欢乐，幸福常伴");//短信内容；短信内容长度不超过1000个汉字（包括1000）, 每个英文或阿拉伯字符或标点符号算1个汉字
		parMap.put("customerId", "201504290000000001");//客户id；由考拉进行分配，客户接入时的的唯一标识
		
		Map<String, Object> rsaMap = RsaUtil.getRsaKey();
		System.out.println("rsa算法生成私钥：" + rsaMap.get("privateKey"));
		System.out.println("rsa算法生成公钥：" + rsaMap.get("publicKey"));
		String data = privateEncryData(String.valueOf(rsaMap.get("privateKey")), parMap);
		System.out.println("rsa算法私钥加密业务数据：" + data);
		
		parMap.put("data", data);//业务数据；接口业务数据，由客户私钥加密得到(业务数据 data 参数：customerId)
		String strJieMi = RsaUtil.decryptByRSAPub(data, String.valueOf(rsaMap.get("publicKey")));
		System.out.println("rsa算法公钥解密业务数据：" + strJieMi);
		BaseHttpClient httpClient = new BaseHttpClient();
		return httpClient.postService(parMap);
	}
	
	/**
	 * rsa私钥加密获取业务数据
	 * 
	 * 将代码中生成的公钥配置到cisdb.EENTERPRISEKEY表中，卡state状态为00；替换MERPUBKEY、 LAKALAPUBKEY两个字段
	 * SELECT CUSTOMERID, DESKEY, MERPUBKEY, LAKALAPRIKEY, STATE, LAKALAPUBKEY FROM EENTERPRISEKEY WHERE CUSTOMERID='201504290000000001' AND STATE='00';
	 * @param privateKey
	 * @param parMap
	 * @return
	 * @throws Exception
	 */
	public static String privateEncryData(String privateKey, Map<String, Object> parMap) throws Exception{
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("customerId", parMap.get("customerId"));
		
		byte params[] = JsonUtil.jsonFromObject(param, charset);
		String strJiaMi = RsaUtil.encryptByRSA(new String(params), privateKey);
		return strJiaMi;
	}
	
}