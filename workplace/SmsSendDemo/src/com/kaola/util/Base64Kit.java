package com.kaola.util;

import java.io.UnsupportedEncodingException;

import org.bouncycastle.util.encoders.Base64;

public class Base64Kit {
	private static String charset = "UTF-8";

	@SuppressWarnings("static-access")
	public static String encode(byte[] b) {
		String s = null;
		if (b != null) {
			byte[] tmp = new Base64().encode(b);
			s = new String(tmp);
		}
		return s;
	}

	@SuppressWarnings("static-access")
	public static String encode(String str, String charset) {
		byte[] b = null;
		String s = null;
		try {
			b = str.getBytes(charset);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
		if (b != null) {
			byte[] tmp = new Base64().encode(b);
			try {
				s = new String(tmp, charset);
			} catch (UnsupportedEncodingException e) {
				s = null;
			}
		}
		return s;
	}

	public static String encode(String str) {
		return encode(str, charset);
	}

	@SuppressWarnings("static-access")
	public static String decoder(String str, String charset) {
		byte[] b = null;
		String result = null;
		if (str != null) {
			try {
				b = new Base64().decode(str);
				result = new String(b, charset);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return result;
	}

	public static String decoder(String str) {
		return decoder(str, charset);
	}

	@SuppressWarnings("static-access")
	public static byte[] decode(String str) {
		byte[] b = null;
		if (str != null) {
			try {
				b = new Base64().decode(str);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return b;
	}
}
