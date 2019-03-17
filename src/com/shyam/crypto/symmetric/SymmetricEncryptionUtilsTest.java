package com.shyam.crypto.symmetric;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.crypto.SecretKey;
import javax.xml.bind.DatatypeConverter;

import org.junit.Test;

public class SymmetricEncryptionUtilsTest {

	/*@Test
	public void createAESKey() throws Exception{
		SecretKey key = SymmetricEncryptionUtils.createAESKey();
		assertNotNull(key);
		System.out.println(DatatypeConverter.printHexBinary(key.getEncoded()));
	}*/
	
	@Test
	public void testAESCryptoRoutine() {
		try {
			SecretKey key = SymmetricEncryptionUtils.createAESKey();
			byte[] initializationVector = SymmetricEncryptionUtils.createInitializationVector();
			String plainText = "My name is Shyam Prasad Gupta.";
			byte[] cipherText = SymmetricEncryptionUtils.performAESEncryption(plainText, key, initializationVector);
			assertNotNull(cipherText);
			System.out.println(DatatypeConverter.printHexBinary(cipherText));
			String decryptedText = SymmetricEncryptionUtils.performAESDecryption(cipherText, key, initializationVector);
			
			assertEquals(plainText, decryptedText);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
