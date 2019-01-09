package com.lakala.sign;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

import com.lakala.config.Config;

public class RSA {

	public static String ALGORITHM = "RSA";

	public static String SIGN_ALGORITHMS = "SHA1WithRSA";// 摘要加密算饭

	/**
	 * 数据签名
	 * 
	 * @param content
	 *            签名内容
	 * @param privateKey
	 *            私钥
	 * @return 返回签名数据
	 */
	public static String sign(String content, String privateKey) {
		try {
			PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(
					Base64.decode(privateKey));
			KeyFactory keyf = KeyFactory.getInstance("RSA");
			PrivateKey priKey = keyf.generatePrivate(priPKCS8);

			java.security.Signature signature = java.security.Signature
					.getInstance(SIGN_ALGORITHMS);

			signature.initSign(priKey);
			signature.update(content.getBytes(Config.CHARSET));

			byte[] signed = signature.sign();

			return Base64.encode(signed);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 签名验证
	 * 
	 * @param content
	 * @param sign
	 * @param lakala_public_key
	 * @return
	 */
	public static boolean verify(String content, String sign,
			String lakala_public_key) {
		try {
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			byte[] encodedKey = Base64.decode(lakala_public_key);
			PublicKey pubKey = keyFactory
					.generatePublic(new X509EncodedKeySpec(encodedKey));

			java.security.Signature signature = java.security.Signature
					.getInstance(SIGN_ALGORITHMS);

			signature.initVerify(pubKey);
			signature.update(content.getBytes(Config.CHARSET));

			boolean bverify = signature.verify(Base64.decode(sign));
			return bverify;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	/**
	 * 通过公钥解密
	 * 
	 * @param content待解密数据
	 * @param pk公钥
	 * @return 返回 解密后的数据
	 */
	protected static byte[] decryptByPublicKey(String content, PublicKey pk) {

		try {
			Cipher ch = Cipher.getInstance(ALGORITHM);
			ch.init(Cipher.DECRYPT_MODE, pk);
			InputStream ins = new ByteArrayInputStream(Base64.decode(content));
			ByteArrayOutputStream writer = new ByteArrayOutputStream();
			// rsa解密的字节大小最多是128，将需要解密的内容，按128位拆开解密
			byte[] buf = new byte[128];
			int bufl;
			while ((bufl = ins.read(buf)) != -1) {
				byte[] block = null;

				if (buf.length == bufl) {
					block = buf;
				} else {
					block = new byte[bufl];
					for (int i = 0; i < bufl; i++) {
						block[i] = buf[i];
					}
				}

				writer.write(ch.doFinal(block));
			}

			return writer.toByteArray();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * 通过私钥加密
	 * 
	 * @param content
	 * @param pk
	 * @return,加密数据，未进行base64进行加密
	 */
	protected static byte[] encryptByPrivateKey(String content, PrivateKey pk) {

		try {
			Cipher ch = Cipher.getInstance(ALGORITHM);
			ch.init(Cipher.ENCRYPT_MODE, pk);
			return ch.doFinal(content.getBytes(Config.CHARSET));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("通过私钥加密出错");
		}
		return null;

	}

	/**
	 * 解密数据，接收端接收到数据直接解密
	 * 
	 * @param content
	 * @param key
	 * @return
	 */
	public static String decrypt(String content, String key) {
		if (null == key || "".equals(key)) {
			return null;
		}
		PublicKey pk = getPublicKey(key);
		byte[] data = decryptByPublicKey(content, pk);
		String res = null;
		try {
			res = new String(data, Config.CHARSET);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	/**
	 * 对内容进行加密
	 * 
	 * @param content
	 * @param key私钥
	 * @return
	 */
	public static String encrypt(String content, String key) {
		PrivateKey pk = getPrivateKey(key);
		byte[] data = encryptByPrivateKey(content, pk);
		String res = null;
		try {
			res = Base64.encode(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;

	}
	
	/* 
	 * 得到私钥对象
	* @param key 密钥字符串（经过base64编码的秘钥字节）
	* @throws Exception
	*/
	public static PrivateKey getPrivateKey(String privateKey)  {
		try {
		byte[] keyBytes;
		
		keyBytes = Base64.decode(privateKey);
		
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
		
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		
		PrivateKey privatekey = keyFactory.generatePrivate(keySpec);
		
		return privatekey;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	 
    /**
	* 获取公钥对象
	* @param key 密钥字符串（经过base64编码秘钥字节）
	* @throws Exception
	*/
	public static PublicKey getPublicKey(String publicKey) {

		try {
			
			byte[] keyBytes;
			
			keyBytes = Base64.decode(publicKey);
			
			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
			
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			
			PublicKey publickey = keyFactory.generatePublic(keySpec);
			
			return publickey;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
		
	}

	public static void main(String[] args) {
		String str = Base64.encode("test".getBytes());
		System.out.println("Base64加密-->" + str);
		String aaa = sign(
				str,
				
	"MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDtWEo3u9qKiBoJukkDvhViLlLTJPyNVhCOGqMrNIQtD8tr087v94rmi1wV4hKmK14yNUmOFDrRJ3HKEOExzciY0TWI2Gu8eHK+UZRlqV7Toml7s2K+MSLJ8pLejL2UJZD1hQou4LNFRNe2eNMjCyfWvkc/ekmppaYUonjTxXkWgW1LIY+KpNrYDbyo89sHHPMWT0KAZkgoz9ycV0ivFOFTyvbGY64mEnYbl65tAFSMVRNBQo/rNw+WOSv66mqiRz757WkmeHvVBc8msDyUjsm7KPeSEXK/qm0ZnaitkHjOgNr9OzkOtrRaROytMB6LTU++ig912QXd0inw7fqt7zBFAgMBAAECggEACjLA/aQUv98ZP1HB0WdZg955sJjmQSxxiambWT/obgc/tmJjgewhC8bxLf+IzZsPcRjZEEHORS5stUIEax9sfyR+KGMkPfTu+T6IIdYlPPK9lZMfqwxxcjv6E5X4j2UN1X7h8SG2kkNjAXQvnlHcZF3IsfGPOLrlZ/2TM4ZIra7M00AAKHtNjnlts9Qmm5wJhutkMbPIm4sXvazvih8mZG+b4EKbc5dfQ8OaKEri0voXiIeuG23pt9AGUSQByzD7zXupYbTuCW/L4fRCplpHP3ZI3xRMGNOf5+9Sma/LM6asGb8xeAJkPwcalmMKnE+8/9aliKWsZ3eXTNsUtubAAQKBgQD74SMkH+xusBSifV6BFPxI7eJyKfrl3DAc72MeihhwuwDK3pYAZ5mgH6t4ZVsvzv4jOB5O6XFkRU6yf3PuuPs5VGNtqd2+yKjJZFb4JBQz04gbW3tA5vsxpYF7YqdluoqlwTWqC6jADNu2rhimD1ISO4ZOaGQ8crrNyniHQEmw9QKBgQDxOkhKpVCZ0BC49w0hPYYEEijk9yYVRQPQVUGCOuxkom0NXzbLudoH4XryChh/HZ61nZOmitx5V02NvW7xzWh6JlChi02rvgPPyCdk7dlZYgjUc+9cSioTIUVmp8ihT22A1S1ELOKoiXhWOX0yuZYHu2kVevuZraMbvvg3nJqwEQKBgQC0DEAxb3xztvxZFqL6RIT+oMVQ9D88FkRyOpCw4OgNlS9A1RGGoofHVTweKN2g9CboCBJqOCbtFTnnEIJPr/0tAuy8FEPFP+yfYVFluWyTi27sl10ojLQczgEbsO6WIl0/lTgGIKV0+vwHvrUx5xu0msYrwCV+rEhqGIRFsie7DQKBgEbic3/L2EV6iI9xuV/9LjZgB7hQj9ZMM3SyuM9F9VUVDaqSQg3XjSQns/MP2GbyE0MXDN4xi9v0NYvjEIjpbTwHqkXuI2LGMWSLUlgsNGQ5lJKphrq96GM6saQaxa/xISJsgiK4ddpEmyHrPsj7FAUtoxF21Bwyk+2BTns4/5OhAoGAakknhNfEbq3Hh8JWI0dDVpPRB/D/DTSYsIWQG4ZZy+1ociLrDN+grJg2o+nW6NQblRvdJdJFFY+7Mms4lUz2A/+EYBhjeYwDKxYfoSjudR/uPivtkeY/ArK3T3YDYSXbFvyXRjcboj0QO9fipsvFhhxe79SO/TLQOKWCJtzGgOs=");
		System.out.println("加签-->" + aaa);
		System.out
				.println("验签-->"
						+ verify(
								str,
								aaa,
								"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA7VhKN7vaiogaCbpJA74VYi5S0yT8jVYQjhqjKzSELQ/La9PO7/eK5otcFeISpiteMjVJjhQ60SdxyhDhMc3ImNE1iNhrvHhyvlGUZale06Jpe7NivjEiyfKS3oy9lCWQ9YUKLuCzRUTXtnjTIwsn1r5HP3pJqaWmFKJ408V5FoFtSyGPiqTa2A28qPPbBxzzFk9CgGZIKM/cnFdIrxThU8r2xmOuJhJ2G5eubQBUjFUTQUKP6zcPljkr+upqokc++e1pJnh71QXPJrA8lI7Juyj3khFyv6ptGZ2orZB4zoDa/Ts5Dra0WkTsrTAei01PvooPddkF3dIp8O36re8wRQIDAQAB"));
		System.out.println("原文-->" + new String(Base64.decode(str)));
	}

}
