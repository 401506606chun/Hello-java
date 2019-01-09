package com.kaola.util;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Base64;

/**
 * Rsa算法
 * @author chengxuehong
 *
 */
public class RsaUtil {

	private static final int KEY_SIZE = 2048;

	//生成随机秘钥
	public static KeyPair generateKeyPair() {
		try {
			KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA",
					new BouncyCastleProvider());
			keyPairGen.initialize(KEY_SIZE, new SecureRandom());
			return keyPairGen.generateKeyPair();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	//得到私钥
	public static String getPrivateKey(PrivateKey key) throws Exception {
		return Base64Kit.encode(key.getEncoded());
	}

	//得到公钥
	public static String getPublicKey(PublicKey key) throws Exception {
		return Base64Kit.encode(key.getEncoded());
	}

	/**
	 * RSA私钥加密
	 * @param json
	 * @param privateKey
	 * @return
	 * @throws CoreException 
	 */
	public static String encryptByRSA(String json, String privateKey) {
		try {
			PKCS8EncodedKeySpec priv_spec = new PKCS8EncodedKeySpec(Base64.decode(privateKey));
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			PrivateKey privKey = keyFactory.generatePrivate(priv_spec);
			Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
			cipher.init(Cipher.ENCRYPT_MODE, privKey);
			byte[] data= cipher.doFinal(json.getBytes());
			return new String(Base64.encode(data));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	/**
	 * RSA公钥解密
	 * @param publicKey
	 * @param json
	 * @return
	 */
	public static String decryptByRSAPub(String json, String publicKey) {
		try {
			X509EncodedKeySpec pubSpec = new X509EncodedKeySpec(Base64.decode(publicKey));
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			PublicKey pubKey = keyFactory.generatePublic(pubSpec);
			Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
			cipher.init(Cipher.DECRYPT_MODE, pubKey);
			byte[] data= cipher.doFinal(Base64.decode(json));
			return new String(data);
		} catch (Exception e) {
			//throw new AmberRuntimeException(ERRORMSG.CT2011,"RSA公钥解密失败");
			e.printStackTrace();
		}
		return "";
	}
	
	/**
	 * 生成私钥、公钥
	 * 
	 * 注：实际应用中，公钥、私钥只用生成一次即可，http请求私钥为固定key值
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Object> getRsaKey() throws Exception{
		KeyPair keyPair = generateKeyPair();
		Map<String, Object> resultMap = new HashMap<String, Object>();
//		resultMap.put("privateKey", getPrivateKey(keyPair.getPrivate()));//生成私钥
		resultMap.put("privateKey", "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDJnJ812QnSXVkWUBaO5NXcEda2QfQJpZveiJ7dj98fA7iyiDO+k8xWlVk2iy/1qwLd6zYprpavmAnjwoYhdEqN98CRsWOCH+H961fDE+wN4EKnHy9hMjBhNtW7oHNa+QBsROVh6cYlQhyErgrkRugcVK/DX7yeCeVABSxzJdLz8jC83b4GRVRm9VFujyNxBJlOG7mQM2otPFRZfibE8xNI4bFlKyAeG0EcA2DD21Ye4d+jpaxg28kvqf46cLjL2UFb4+OmRNYfGWPryCZKDkDeI+EA+4P99GLYTCl7FY1gxnzSbT89kfnmdwyCzR2/BV69IaeFKvT72aS/GWaOkHLdAgMBAAECggEAb+EKOMeIO8Sxe0BWRFWYl9cOX3WaBwBvzr3h6vG7z2oxRGFw1YEPE70tFKeIpbuQi7XLCLZpyGeGlW2NIWN3O/EQW8kkf0FSF6ZRvV+AzvJ0Cmmz0SwYzNmxIb2YRhOHuI1C/WLNIj/ds4MhaCwxAge8wZ6O/ORg43X7hKqotdXjpcubtooKGTVkzLpsq5aNpozQn6dl/v6f4V9ijShaZy6YihWHhVZdfZGQWIl0IEE9op2z9iVrest55J8UnYag6XAyuQF9+INGl9A6Dw6+JVKZrdAv4aqjl6jko5J6YfrU0SxRbrI+Nulf/byAGXSTcc81jJ/cDYFcU4az2Q4kAQKBgQDynZjwnTRQUmRCzqTgLVbnhDAEKFlgxqBEVIm46/8WNknIYrd/N/HZlJfrzQTv3hwij7p5c2CV6kcjeFNhtj9mTVo8abksZFHf9BAjvAzxvxfEyaq8XOtXFVUz0V/KmqMCcWjv8l4uhjV4dtfBX7SAcVgKhV5+eDA2zzvGk3b+sQKBgQDUu+/76Pg7zr3lgEXDDZhX0hGc/ZaldMSdtBgapA3zulfwuD6R9LSXQUKWkYRuSzV6md1MsSj4tClhy/ZrRLZcdtJv30LbcKmQKncfuuGRbf5DoedD18908EPo2TAzQac0A/q32KKkAUI0mh4lmAv2dz4hQIVVpVr44jdy7pV57QKBgA2ygBP1jCKMxERRTlWpuaJQgDV74a/pBttUuoHKMRib8AZ0LE/uZKUYEoupg4HXVFdPLgP1U5qjI2kJPgviMioga9vFTEMJVuYB7XdXApQTadAeYplH1bGQ7sKCK5pQqKtWo8oIMPEHerVWYE/2i0ZFH+dYKEGTn4U4pfNMGAfBAoGBAIfjUQWiKEbT5htel7Fj0sduSY/1sLAZ6aovKyKBXuQsSRZcKqPDI0RnDGjvpR9q7UtWU7U0iU5sju6Igey9ZJulzwBlJejUi4uqhJOBJatnDn8XQYnlpgA1pbtsWMy1CGBClnmvaRzILQk61T0AZcvXGZnWBBXUXUsT/YVt/491AoGBAJEntC7Pp3Rce8qzAhcyL4Z5yb2fLzk94ciAJfBrtTALodLTIbn/0PUI64hIdDs+5bIO7Rk3cVMFCqReI6nF1bjHKCW3jjc1BbNDQ1zcr+o2q0bFR5jMma8G7oGe5VyLwp5xxOBDncFeZKPJFLxpwxhty2zPCOkC9POwsO8UW87R");
//		resultMap.put("publicKey", getPublicKey(keyPair.getPublic()));//生成公钥
		resultMap.put("publicKey", "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAyZyfNdkJ0l1ZFlAWjuTV3BHWtkH0CaWb3oie3Y/fHwO4sogzvpPMVpVZNosv9asC3es2Ka6Wr5gJ48KGIXRKjffAkbFjgh/h/etXwxPsDeBCpx8vYTIwYTbVu6BzWvkAbETlYenGJUIchK4K5EboHFSvw1+8ngnlQAUscyXS8/IwvN2+BkVUZvVRbo8jcQSZThu5kDNqLTxUWX4mxPMTSOGxZSsgHhtBHANgw9tWHuHfo6WsYNvJL6n+OnC4y9lBW+PjpkTWHxlj68gmSg5A3iPhAPuD/fRi2EwpexWNYMZ80m0/PZH55ncMgs0dvwVevSGnhSr0+9mkvxlmjpBy3QIDAQAB");
		return resultMap;
	}

}