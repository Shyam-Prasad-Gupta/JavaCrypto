package com.shyam.crypto.hash;

import java.io.ByteArrayOutputStream;
import java.security.MessageDigest;
import java.security.SecureRandom;

import org.mindrot.jbcrypt.BCrypt;

public class HashUtils {

	public static final String SHA2_ALGORITHM = "SHA-256";

	public static byte[] generateRandomSalt() {
		byte[] salt = new byte[16];
		SecureRandom secureRandom = new SecureRandom();
		secureRandom.nextBytes(salt);

		return salt;
	}

	public static byte[] createSHA2Hash(String input, byte[] salt) throws Exception {

		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		byteStream.write(salt);
		byteStream.write(input.getBytes());
		byte[] valueToHash = byteStream.toByteArray();
		MessageDigest messageDigest = MessageDigest.getInstance(SHA2_ALGORITHM);

		return messageDigest.digest(valueToHash);
	}
	
	public static String hashPasswordUsingBcrypt(String password) {
		return BCrypt.hashpw(password, BCrypt.gensalt());
	}
	
	public static boolean verifyPassword(String plainPassword, String bcryptedPassword) {
		return BCrypt.checkpw(plainPassword, bcryptedPassword);
	}

}
