package com.wefi.crypt;

import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.Cipher;

public class RSAMethods {
	
	public static void createBase64Keys(int bits) {
		try {
			KeyPairGenerator kGen = KeyPairGenerator.getInstance("RSA");
			kGen.initialize(bits);
			KeyPair kp = kGen.generateKeyPair();
			String priv = Base64.getEncoder().encodeToString(kp.getPrivate().getEncoded());
			String pub = Base64.getEncoder().encodeToString(kp.getPublic().getEncoded());
			
			System.out.println();
			System.out.println("Private");
			System.out.println(priv);
			System.out.println();
			System.out.println("Public");
			System.out.println(pub);
			System.out.println();
			
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	public static String decrypt(String encryptedBase64, String base64Private) {
		String decryptedString = "";
	    try {
	        KeyFactory keyFac = KeyFactory.getInstance("RSA");
	        KeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(base64Private.getBytes()));
	        Key key = keyFac.generatePrivate(keySpec);

	        // get an RSA cipher object and print the provider
	        final Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWITHSHA-256ANDMGF1PADDING");
	        cipher.init(Cipher.DECRYPT_MODE, key);

	        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedBase64);
	        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
	        decryptedString = new String(decryptedBytes);
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	    return decryptedString;
	}
	
	public static String encrypt(String clearText, String publicKey) {
	    String encryptedBase64 = "";
	    try {
	        KeyFactory keyFac = KeyFactory.getInstance("RSA");
	        KeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(publicKey.getBytes()));
	        Key key = keyFac.generatePublic(keySpec);

	        // get an RSA cipher object and print the provider
	        final Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWITHSHA-256ANDMGF1PADDING");
	        // encrypt the plain text using the public key
	        cipher.init(Cipher.ENCRYPT_MODE, key);

	        byte[] encryptedBytes = cipher.doFinal(clearText.getBytes("UTF-8"));
	        encryptedBase64 = new String(Base64.getEncoder().encode(encryptedBytes));
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	    return encryptedBase64.replaceAll("(\\r\\n|\\r|\\n)", "");
	}

}
