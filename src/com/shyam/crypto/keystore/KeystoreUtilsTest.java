package com.shyam.crypto.keystore;

import static org.junit.Assert.*;

import java.security.KeyStore;

import javax.crypto.SecretKey;
import javax.xml.bind.DatatypeConverter;

import org.junit.Test;

import com.shyam.crypto.symmetric.SymmetricEncryptionUtils;

public class KeystoreUtilsTest {

	@Test
	public void createPrivateKeyJavaKeystore() throws Exception {
		SecretKey secretKey = SymmetricEncryptionUtils.createAESKey();
		String secretKeyHex = DatatypeConverter.printHexBinary(secretKey.getEncoded());
		KeyStore keyStore = KeystoreUtils.createPrivateKeyJavaKeyStore("password", "foo", secretKey, "keyPassword");
		assertNotNull(keyStore);
		
		keyStore.load(null, "password".toCharArray());
		KeyStore.ProtectionParameter entryPassword = new KeyStore.PasswordProtection("keyPassword".toCharArray());
		KeyStore.SecretKeyEntry resultEntry = (KeyStore.SecretKeyEntry)keyStore.getEntry("foo", entryPassword);
		SecretKey result = resultEntry.getSecretKey();
		String resultKeyHex = DatatypeConverter.printHexBinary(result.getEncoded());
		assertEquals(secretKeyHex, resultKeyHex);
	}

}
