package com.lakala.util;

import java.util.Map;

import org.apache.commons.httpclient.NameValuePair;

import com.lakala.config.Config;
import com.lakala.util.httpClient.HttpProtocolHandler;
import com.lakala.util.httpClient.HttpRequest;
import com.lakala.util.httpClient.HttpResponse;
import com.lakala.util.httpClient.HttpResultType;

public class HttpUtil {
	
	public static String buildRequest(String url,Map<String, String> params) throws Exception {

        HttpProtocolHandler httpProtocolHandler = HttpProtocolHandler.getInstance();

        HttpRequest request = new HttpRequest(HttpResultType.BYTES);
        //设置编码集
        request.setCharset(Config.CHARSET);

        request.setParameters(generatNameValuePair(params));
        request.setUrl(url);

        String strResult = null;
        HttpResponse response = httpProtocolHandler.execute(request);
        if (response == null) {
        	strResult = null;
        }else{
            strResult = response.getStringResult();
        }
        System.out.println(strResult);
        return strResult;
    }
	
	  /**
     * MAP类型数组转换成NameValuePair类型
     * @param properties  MAP类型数组
     * @return NameValuePair类型数组
     */
    private static NameValuePair[] generatNameValuePair(Map<String, String> properties) {
        NameValuePair[] nameValuePair = new NameValuePair[properties.size()];
        int i = 0;
        for (Map.Entry<String, String> entry : properties.entrySet()) {
            nameValuePair[i++] = new NameValuePair(entry.getKey(), entry.getValue());
        }

        return nameValuePair;
    }

}
