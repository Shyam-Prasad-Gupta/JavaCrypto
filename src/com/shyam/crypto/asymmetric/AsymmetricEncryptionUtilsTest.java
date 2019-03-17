package com.shyam.crypto.asymmetric;

import static org.junit.Assert.*;

import java.security.KeyPair;
import java.security.PrivateKey;

import javax.crypto.Cipher;
import javax.xml.bind.DatatypeConverter;

import org.junit.Test;

public class AsymmetricEncryptionUtilsTest {

	@Test
	public void testGenerateRSAKeyPair() throws Exception {
		// fail("Not yet implemented");
		KeyPair keyPair = AsymmetricEncryptionUtils.generateRSAKeyPair();
		assertNotNull(keyPair);
		System.out.println("Private key: " + DatatypeConverter.printHexBinary(keyPair.getPrivate().getEncoded()));
		System.out.println("Public Key:  " + DatatypeConverter.printHexBinary(keyPair.getPublic().getEncoded()));
	}
	
	@Test
	public void getRSACyptoRoutine() throws Exception{
		KeyPair keyPair = AsymmetricEncryptionUtils.generateRSAKeyPair();
		String plainText = "This is the text we are going to hide.";
		
		byte[] cipherText = AsymmetricEncryptionUtils.performRSAEncryption(plainText, keyPair.getPrivate());
		assertNotNull(cipherText);
		System.out.println(DatatypeConverter.printHexBinary(cipherText));
		
		String decryptedText = AsymmetricEncryptionUtils.performRSADecryption(cipherText, keyPair.getPublic());
		assertEquals(plainText, decryptedText);
	}
}
