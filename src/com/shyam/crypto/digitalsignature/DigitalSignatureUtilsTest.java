package com.shyam.crypto.digitalsignature;

import static org.junit.Assert.assertTrue;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyPair;

import javax.xml.bind.DatatypeConverter;

import org.junit.Test;

import com.shyam.crypto.asymmetric.AsymmetricEncryptionUtils;

public class DigitalSignatureUtilsTest {

	@Test
	public void digitalSignatureUtilTest() throws Exception {
		
		URL url = this.getClass().getClassLoader().getResource("test.txt");
		Path path = Paths.get(url.toURI());
		byte[] input = Files.readAllBytes(path);
		
		KeyPair keyPair = AsymmetricEncryptionUtils.generateRSAKeyPair();
		byte[] signature = DigitalSignatureUtils.createDigitalSignature(input, keyPair.getPrivate());
		System.out.println(DatatypeConverter.printHexBinary(signature));
		
		assertTrue(DigitalSignatureUtils.verifyDigitalSignature(input, signature, keyPair.getPublic()));
		
	}

}
