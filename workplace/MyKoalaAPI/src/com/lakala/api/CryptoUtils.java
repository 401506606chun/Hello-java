package com.lakala.api;

import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class CryptoUtils {
	
	
	public static PublicKey getRSAPublicKey(byte[] keyBytes){
	    try{
		      KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		      X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
		      return keyFactory.generatePublic(keySpec); 
	    } catch (GeneralSecurityException e) {
	    	  throw new RuntimeException(e);
	    }
	    
	}

	public static PrivateKey getRSAPrivateKey(byte[] keyBytes){
	    try {
		      KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		      PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
		      return keyFactory.generatePrivate(keySpec); 
        } catch (GeneralSecurityException e) {
	    	  throw new RuntimeException(e);
	    }
    
	}
	
	public static SecretKey createDESKey(byte[] keyBytes){
	    try {
		      SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		      DESKeySpec keySpec = new DESKeySpec(keyBytes);
		      return keyFactory.generateSecret(keySpec); 
	      } catch (GeneralSecurityException e) {
	    	  throw new RuntimeException(e);
	    }
	    
	}

	public static byte[] rsaEncrypt(Key key, byte[] dataBytes){
		return operate("RSA", 1, key, dataBytes);
	}

	public static byte[] rsaDecrypt(Key key, byte[] dataBytes) {
		return operate("RSA", 2, key, dataBytes);
	}

	public static byte[] desEncrypt(Key key, byte[] dataBytes) {
		return operate("DES", 1, key, dataBytes);
	}

	public static byte[] desDecrypt(Key key, byte[] dataBytes) {
		return operate("DES", 2, key, dataBytes);
	}

	private static byte[] operate(String transformation, int opmode, Key key, byte[] dataBytes) {
		try {
			Cipher cipher = Cipher.getInstance(transformation);
			cipher.init(opmode, key);
			return cipher.doFinal(dataBytes); 
		} catch (GeneralSecurityException e) {
    	  throw new RuntimeException(e);
		}
   
	}
}