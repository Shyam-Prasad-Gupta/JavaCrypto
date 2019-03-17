package com.shyam.crypto.symmetric;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

public class SymmetricEncryptionUtils {

	private static final String AES = "AES";
	private static final String AES_CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";

	public static SecretKey createAESKey() throws Exception {
		SecureRandom secureRandom = new SecureRandom();

		KeyGenerator keyGenerator = KeyGenerator.getInstance(AES);
		keyGenerator.init(256, secureRandom);
		return keyGenerator.generateKey();
	}

	public static byte[] createInitializationVector() {
		byte[] initializationVector = new byte[16];
		SecureRandom secureRandom = new SecureRandom();
		secureRandom.nextBytes(initializationVector);
		return initializationVector;
	}

	public static byte[] performAESEncryption(String plainText, SecretKey secretKey, byte[] initializationVector) {
		try {
			Cipher cipher = Cipher.getInstance(AES_CIPHER_ALGORITHM);
			IvParameterSpec ivParameterSpec = new IvParameterSpec(initializationVector);
			cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec);
			return cipher.doFinal(plainText.getBytes());
		} catch (/*NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException | InvalidKeyException
				| InvalidAlgorithmParameterException | NoSuchAlgorithmException*/Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public static String performAESDecryption(byte[] cipherText, SecretKey secretKey, byte[] initializationVector) {
		try {
			Cipher cipher = Cipher.getInstance(AES_CIPHER_ALGORITHM);
			IvParameterSpec ivParameterSpec = new IvParameterSpec(initializationVector);
			cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);
			byte[] result = cipher.doFinal(cipherText);
			return new String(result);
		} catch (/*IllegalBlockSizeException | BadPaddingException | InvalidAlgorithmParameterException
				| InvalidKeyException | NoSuchPaddingException | NoSuchAlgorithmException*/Exception ex) {

		}

		return null;
	}

}
