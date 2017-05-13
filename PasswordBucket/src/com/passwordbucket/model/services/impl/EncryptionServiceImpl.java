package com.passwordbucket.model.services.impl;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

import com.passwordbucket.model.domain.repository.RetrieveSecretKey;
import com.passwordbucket.model.domain.repository.impl.RetrievedSecretKeyInMemory;
import com.passwordbucket.model.services.EncryptionService;

public class EncryptionServiceImpl implements EncryptionService {

	private static RetrieveSecretKey getSecretKey = new RetrievedSecretKeyInMemory();
	private static final SecretKey SECRET_KEY = getSecretKey.getSecretEncryptionKey();
	private static final String INSTANCE = "AES";
	private static Cipher cipher;

	private static String encrypt(String password) {
		String encryptedText = null;
		try {
			cipher = Cipher.getInstance(INSTANCE);
			byte[] plainTextByte = password.getBytes();
			cipher.init(Cipher.ENCRYPT_MODE, SECRET_KEY);
			byte[] encryptedByte = cipher.doFinal(plainTextByte);
			Base64.Encoder encoder = Base64.getEncoder();
			encryptedText = encoder.encodeToString(encryptedByte);

		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException
				| BadPaddingException e) {
			e.printStackTrace();
		}

		return encryptedText;
	}

	private static String decrypt(String password) {
		String decryptedText = null;
		try {
			cipher = Cipher.getInstance(INSTANCE);
			Base64.Decoder decoder = Base64.getDecoder();
			byte[] encryptedTextByte = decoder.decode(password);
			cipher.init(Cipher.DECRYPT_MODE, SECRET_KEY);
			byte[] decryptedByte = cipher.doFinal(encryptedTextByte);
			decryptedText = new String(decryptedByte);
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException
				| BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return decryptedText;

	}

	@Override
	public String encryptPassword(String password) {

		return encrypt(password);
	}

	@Override
	public String decryptPassword(String password) {
		return decrypt(password);
	}

}
