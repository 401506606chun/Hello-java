package com.lakala.util;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

import com.lakala.config.Config;
import com.lakala.sign.Base64;

public class KeyUtil {

	public  static PrivateKey pkey ;
	
	public	static PublicKey pubkey ;
	 
	public static Map<String,Object> genKey() throws Exception{
		
		KeyPairGenerator kpg = KeyPairGenerator.getInstance(Config.RSA_ALGORITHM);
		kpg.initialize(1024);
		KeyPair kep = kpg.generateKeyPair();
		Provider p  = kpg.getProvider();
		System.out.println(p.getName());
		 pkey = kep.getPrivate();
		 pubkey = kep.getPublic();
		System.out.println("生成的公钥"+new String(Base64.encode(pubkey.getEncoded())));
		System.out.println("生成的私钥"+new String(Base64.encode(pkey.getEncoded())));

		Map<String,Object> param=new HashMap<String,Object>();
		param.put("PublicKey", new String(Base64.encode(pubkey.getEncoded())));
		param.put("PrivateKey", new String(Base64.encode(pkey.getEncoded())));
		
		return param;
	}

	
	public static void main(String[] args) throws Exception{
		Map<String,Object> param=genKey();
		String pb=(String) param.get("PublicKey");
		String pr=(String) param.get("PrivateKey");
		System.out.println(pb);
		System.out.println(pr);	
	}
	
}

