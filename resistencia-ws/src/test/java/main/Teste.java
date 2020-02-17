package main;

import static org.junit.Assert.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.junit.Test;

import sun.misc.BASE64Encoder;

public class Teste {

	@Test
	public void test() throws NoSuchAlgorithmException{
		
		MessageDigest md = MessageDigest.getInstance("SHA-1");
        BASE64Encoder encoder = new BASE64Encoder();
        md.update("asdf".getBytes());
        System.out.println(encoder.encode(md.digest()));
        
        assertTrue(true);
	}

}
