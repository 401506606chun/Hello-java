package com.kaola.util;

import java.util.Map;

import org.apache.commons.httpclient.ConnectTimeoutException;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.NoHttpResponseException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.params.HttpMethodParams;

/**
 * http post请求
 * @author chengxuehong
 *
 */
public class BaseHttpClient {
	
	private static String url = "http://10.5.19.26:9080/dv/smsBatchApply.do?_t=json";//短信通道请求地址 --测试环境测试地址
//	private static String url = "http://123.124.162.36:9080/dv/smsBatchApply.do?_t=json";//外网地址
	
	private static HttpClient httpClient;

	/**
	 * 连接超时时间
	 */
	private Integer connTimeout = 3000;
	/**
	 * 读取内容超时时间
	 */
	private Integer soTimeout = 50000;
	
	private HttpConnectionManagerParams managerParams = null;//设置请求参数

	public static String getUrl() {
		return url;
	}

	public static void setUrl(String url) {
		BaseHttpClient.url = url;
	}

	public static HttpClient getHttpClient() {
		return httpClient;
	}

	public static void setHttpClient(HttpClient httpClient) {
		BaseHttpClient.httpClient = httpClient;
	}

	public Integer getConnTimeout() {
		return connTimeout;
	}

	public void setConnTimeout(Integer connTimeout) {
		this.connTimeout = connTimeout;
	}

	public Integer getSoTimeout() {
		return soTimeout;
	}

	public void setSoTimeout(Integer soTimeout) {
		this.soTimeout = soTimeout;
	}

	public BaseHttpClient() {
		httpClient = new HttpClient(new MultiThreadedHttpConnectionManager());
		managerParams = httpClient.getHttpConnectionManager().getParams();
		managerParams.setConnectionTimeout(connTimeout);
		managerParams.setSoTimeout(soTimeout);
		HttpClientParams clientParams =  new HttpClientParams();
        clientParams.setConnectionManagerTimeout(500);
        httpClient.setParams(clientParams);
		httpClient.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
	}
	
	/**
	 * 用于https拼装请求 post
	 * 
	 * @param para
	 * @return
	 * @throws Exception 
	 * @throws CoreException
	 */
	public static String postService(Map<String, Object> para) throws Exception {
		PostMethod postMethod = null;
		System.out.println("远程请求的url:" + url);
		postMethod = new PostMethod(url);
		String param = new String(JsonUtil.jsonFromObject(para, "UTF-8"));
		System.out.println("远程请求的json:" + param);
		for (String key : para.keySet()) {
			postMethod.addParameter(key, para.get(key).toString());
		}
		postMethod.getParams().setParameter(
				HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
		return excute(postMethod);
	}

	/**
	 * 用于模拟http请求
	 * 
	 * @param method
	 * @return
	 * @throws Exception 
	 * @throws CoreException
	 */
	protected static String excute(HttpMethod method) throws Exception {
		method.addRequestHeader("Connection", "close");
		try {
			// UsernamePasswordCredentials creds = new
			// UsernamePasswordCredentials(userName, password);
			// httpClient.getState().setCredentials(AuthScope.ANY, creds);
			int statusCode = httpClient.executeMethod(method);
			if (statusCode != HttpStatus.SC_OK) {
				System.out.println("返回数据:" + method.getResponseBodyAsString());
				throw new Exception("连接服务器其他错误, 状态码："
						+ statusCode);
			} else {
				System.out.println("返回数据:" + method.getResponseBodyAsString());
				return method.getResponseBodyAsString();
			}
		} catch (Exception e) {
			System.out.println("{}" + e.getMessage());
			if (e instanceof NoHttpResponseException) {
				throw new Exception("数据服务器忙", e);
			} else if (e instanceof ConnectTimeoutException) {
				throw new Exception("连接数据服务器超时", e);
			} else {
				throw new Exception("连接数据服务器其他错误", e);
			}
		} finally {
			method.releaseConnection();//释放、断开连接
		}
	}

}