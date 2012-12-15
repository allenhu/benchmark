package com.alibaba.study.benchmark.xcase;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;

public class RSA_Encrypt extends BenchmarkCaseAdapter {
	private Cipher cipher;
	private byte[] plainBytes;

	public RSA_Encrypt() {
		super("RSA-Encrypt");
	}

	public void init() throws Exception {
		plainBytes = new byte[32]; // 4k

		SecureRandom random = new SecureRandom();
		random.nextBytes(plainBytes);

		KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
		keyPairGen.initialize(2048);
		KeyPair keyPair = keyPairGen.generateKeyPair();
		PublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
//		PrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

		cipher = Cipher.getInstance(publicKey.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
	}

	public void execute() throws Exception {
		cipher.doFinal(plainBytes);
	}
}
