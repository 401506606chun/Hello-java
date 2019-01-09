package com.lakala.util.httpClient;

import java.io.IOException;
import java.net.UnknownHostException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.util.IdleConnectionTimeoutThread;

public class HttpProtocolHandler {

	private static String DEFAULT_CHARSET = "UTF-8";

	/** 连接超时时间 */
	private int defaultConnectionTimeout = 5000;

	/** 回应超时时间 */
	private int defaultSoTimeout = 300000;

	/** 闲置连接超时时间 */
	private int defaultIdleConnTimeout = 5000;

	private int defaultMaxConnPerHost = 1000;

	private int defaultMaxTotalConn = 1000;

	/** 默认等待HttpConnectionManager返回连接超时（只有在达到最大连接数时起作用） */
	private static final long defaultHttpConnectionManagerTimeout = 3000;

	/**
	 * HTTP连接管理器，该连接管理器必须是线程安全的.
	 */
	private HttpConnectionManager connectionManager;

	private static HttpProtocolHandler httpProtocolHandler = new HttpProtocolHandler();

	/**
	 * 工厂方法
	 * 
	 * @return
	 */
	public static HttpProtocolHandler getInstance() {
		return httpProtocolHandler;
	}

	/**
	 * 私有的构造方法
	 */
	private HttpProtocolHandler() {
		// 创建一个线程安全的HTTP连接池
		connectionManager = new MultiThreadedHttpConnectionManager();
		connectionManager.getParams().setDefaultMaxConnectionsPerHost(defaultMaxConnPerHost);
		connectionManager.getParams().setMaxTotalConnections(defaultMaxTotalConn);

		IdleConnectionTimeoutThread ict = new IdleConnectionTimeoutThread();
		ict.addConnectionManager(connectionManager);
		ict.setConnectionTimeout(defaultIdleConnTimeout);

		ict.start();
	}

	/**
	 * 执行Http请求
	 * 
	 * @param request
	 *            请求数据
	 * @return
	 * @throws HttpException
	 *             , IOException
	 */
	public HttpResponse execute(HttpRequest request) throws HttpException, IOException {
		HttpClient httpclient = new HttpClient(connectionManager);

		// 设置连接超时
		int connectionTimeout = defaultConnectionTimeout;
		if (request.getConnectionTimeout() > 0) {
			connectionTimeout = request.getConnectionTimeout();
		}
		httpclient.getHttpConnectionManager().getParams().setConnectionTimeout(connectionTimeout);

		// 设置回应超时
		int soTimeout = defaultSoTimeout;
		if (request.getTimeout() > 0) {
			soTimeout = request.getTimeout();
		}
		httpclient.getHttpConnectionManager().getParams().setSoTimeout(soTimeout);

		// 设置等待ConnectionManager释放connection的时间
		httpclient.getParams().setConnectionManagerTimeout(defaultHttpConnectionManagerTimeout);

		String charset = request.getCharset();
		charset = charset == null ? DEFAULT_CHARSET : charset;
		HttpMethod method = null;
		if (request.getMethod().equals(HttpRequest.METHOD_GET)) {
			method = new GetMethod(request.getUrl());
			method.getParams().setCredentialCharset(charset);
			method.setQueryString(request.getQueryString());
		} else {
			method = new PostMethod(request.getUrl());
			((PostMethod) method).addParameters(request.getParameters());
		}
		HttpResponse response = new HttpResponse();
		try {
			httpclient.executeMethod(method);
			if (request.getResultType().equals(HttpResultType.STRING)) {
				response.setStringResult(method.getResponseBodyAsString());
			} else if (request.getResultType().equals(HttpResultType.BYTES)) {
				response.setByteResult(method.getResponseBody());
			}
			response.setResponseHeaders(method.getResponseHeaders());
		} catch (UnknownHostException ex) {
			ex.printStackTrace();
			return null;
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		} finally {
			method.releaseConnection();
		}
		return response;
	}

	/**
	 * 将NameValuePairs数组转变为字符串
	 * 
	 * @param nameValues
	 * @return
	 */
	protected String toString(NameValuePair[] nameValues) {
		if (nameValues == null || nameValues.length == 0) {
			return "";
		}

		StringBuffer buffer = new StringBuffer();

		for (int i = 0; i < nameValues.length; i++) {
			NameValuePair nameValue = nameValues[i];

			if (i == 0) {
				buffer.append(nameValue.getName() + "=" + nameValue.getValue());
			} else {
				buffer.append("&" + nameValue.getName() + "=" + nameValue.getValue());
			}
		}

		return buffer.toString();
	}
}
