package com.lakala.sign;


import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;


public class TrippleDes {

	public static final String DESEDE_ENCRYPTION_SCHEME = "DESede";

	private final String mode;
	private final String padding;
	private byte[] salt_IV;

	SecretKey key;

	public TrippleDes(String padding, byte[] salt) throws Exception {
		if(salt == null || salt.length != 8)
			throw new IllegalArgumentException(
					"A TripleDES IV should be 8 bytes long");
		this.padding = padding;
		this.mode = "CBC";
		this.salt_IV = salt;
	}

	public TrippleDes(String padding) throws Exception {
		this.padding = padding;
		this.mode = "ECB";
	}

	public void initKey(byte[] key) {
		byte[] keyValue = new byte[24]; // final 3DES key

		if (key.length == 16) {
			// Create the third key from the first 8 bytes
			System.arraycopy(key, 0, keyValue, 0, 16);
			System.arraycopy(key, 0, keyValue, 16, 8);

		} else if (key.length != 24) {
			throw new IllegalArgumentException(
					"A TripleDES key should be 24 bytes long");
		} else {
			keyValue = key;
		}
		try {
			this.key = makeSecretKey(keyValue);
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

	private SecretKey makeSecretKey(byte[] key) throws InvalidKeySpecException,
			InvalidKeyException, NoSuchAlgorithmException {
		KeySpec ks = new DESedeKeySpec(key);
		SecretKeyFactory skf = SecretKeyFactory
				.getInstance(DESEDE_ENCRYPTION_SCHEME);
		return skf.generateSecret(ks);
	}

	private String getEncryptionSchema() {
		return DESEDE_ENCRYPTION_SCHEME + "/" + mode + "/"
				+ (padding == null ? "NoPadding" : padding);
	}

	private Cipher makeCipher(int enc) {
		try {
			Cipher cipher = Cipher.getInstance(getEncryptionSchema());
			if (salt_IV != null) {
				IvParameterSpec iv = new IvParameterSpec(salt_IV);
				cipher.init(enc, key, iv);
			} else {
				cipher.init(enc, key);
			}
			return cipher;
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

	public byte[] encrypt(byte[] unencrypted) {
		byte[] encrypted = null;
		try {
			Cipher cipher = makeCipher(Cipher.ENCRYPT_MODE);
			encrypted = cipher.doFinal(unencrypted);
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
		return encrypted;
	}

	public byte[] decrypt(byte[] encrypted) {
		byte[] decrypted = null;
		try {
			Cipher cipher = makeCipher(Cipher.DECRYPT_MODE);
			decrypted = cipher.doFinal(encrypted);
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
		return decrypted;
	}

	public static void main(String args[]) throws Exception {
		TrippleDes td = new TrippleDes("PKCS5Padding", "test1234".getBytes());
		td.initKey("ThisIsSpartaThisIsSparta".getBytes()); 
		// key must be 24 or 16
		
		TrippleDes td2= new TrippleDes("PKCS5Padding", "test1234".getBytes());
		td2.initKey("ThisIsSpartaThisIsSparta".getBytes()); 
		// key must be 24 or 16

		String target = "imparator";
		byte[] encrypted = td.encrypt(target.getBytes());
		byte[] decrypted = td2.decrypt(encrypted);

		System.out.println("String To Encrypt: " + target);
		System.out.println("Encrypted String:"
				+ new String(encrypted));
		System.out.println("Encrypted String:"
				+ new String(Base64.encode(encrypted)));
		System.out.println("Decrypted String:" + new String(decrypted));

	}

}