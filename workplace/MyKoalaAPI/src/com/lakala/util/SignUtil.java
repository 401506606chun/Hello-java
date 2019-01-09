package com.lakala.util;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import net.sf.json.JSONObject;
import com.lakala.config.Config;
import com.lakala.dict.Dict;
import com.lakala.sign.DES;
import com.lakala.sign.RSA;

public class SignUtil {
	
	/**
	 * 加密并签名
	 * @param reqMap
	 * @return 加密和签名后的结果Map
	 */
    public static Map<String,String> getSign(Map<String ,String> reqMap){
    	Map<String, String> data = new HashMap<String, String>();
    	JSONObject reqJson = JSONObject.fromObject(reqMap);
		String reqData = reqJson.toString();
		System.out.println("转json后数据:"+reqData);
		try {
			 DES des=new DES(Config.KOALA_DES_KEY);
			 reqData=des.encrypt(reqData);
			data.put(Dict.REQDATA, reqData);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		data.put(Dict.SIGN, RSA.sign(reqData, Config.RSA_PRIVATE_KEY));
 		return data;	
    }
    
    /**
     * 根据反馈回来的信息，生成签名结果
     * @param Params 通知返回来的参数数组
     * @param sign 比对的签名结果
     * @return 生成的签名结果
     */
	@SuppressWarnings("unchecked")
	public static Map<String,Object> getSignVeryfy(Map<String, Object> resMap) {
		String retData=String.valueOf(resMap.get(Dict.RETDATA));
        //获得签名验证结果
        boolean isSign = RSA.verify(retData,String.valueOf(resMap.get(Dict.SIGN)), Config.KOALA_RSA_PUBLIC_KEY);
        System.out.println("验证考拉签名结果:"+isSign);
        if(isSign){
        	DES des = new DES(Config.KOALA_DES_KEY);
    		try {
    			retData=des.decrypt(retData);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    		Map<String, Object> data = JSONObject.fromObject(retData);
    		return data;
        }else{
        	System.out.println("验证考拉签名失败");
        	return null;
        }
    }
}
