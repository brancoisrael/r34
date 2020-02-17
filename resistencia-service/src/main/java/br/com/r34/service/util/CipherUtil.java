package br.com.r34.service.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

public class CipherUtil {

	private CipherUtil() {}
	
	public static String cipher(String value) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-1");
        BASE64Encoder encoder = new BASE64Encoder();
        md.update(value.getBytes());
        return encoder.encode(md.digest());        
	}
	
}
