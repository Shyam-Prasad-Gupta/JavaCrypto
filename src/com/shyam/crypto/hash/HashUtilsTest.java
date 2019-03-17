package com.shyam.crypto.hash;

import static org.junit.Assert.*;

import java.util.UUID;

import javax.xml.bind.DatatypeConverter;

import org.junit.Test;

public class HashUtilsTest {

	@Test
	public void testCreateSHA2Hash() throws Exception {
		byte[] salt = HashUtils.generateRandomSalt();
		
		//String inputText = "My name is Anthony.";
		String inputText = UUID.randomUUID().toString();
		byte[] hashCode1 = HashUtils.createSHA2Hash(inputText, salt);
		assertNotNull(hashCode1);
		System.out.println(hashCode1.toString());
		byte[] hashCode2 = HashUtils.createSHA2Hash(inputText, salt);
		assertNotNull(hashCode2);
		assertEquals(DatatypeConverter.printHexBinary(hashCode1), DatatypeConverter.printHexBinary(hashCode2));
	}
	
	@Test
	public void testBcryptHashing() {
		String plainPassword = "abra ka dabra";
		String bcryptedPassword = HashUtils.hashPasswordUsingBcrypt(plainPassword);
		System.out.println("Bcrypted Password is: " + bcryptedPassword);
		
		assertTrue(HashUtils.verifyPassword(plainPassword, bcryptedPassword));
	}

}
