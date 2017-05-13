package com.passwordbucket.model.domain.repository.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import com.passwordbucket.model.domain.repository.RetrieveSecretKey;

public class RetrievedSecretKeyInMemory implements RetrieveSecretKey{
	
	
	private static final String DB_URL = "jdbc:sqlite:PasswordBucket.db";
	private static final String DB_USER = "root";
	private static final String DB_PASSWD = "root";
	

	@Override
	public SecretKey getSecretEncryptionKey() {
		
		SecretKey secretKey = null;
	
		try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWD);) {
			
			String querySecretKey = "SELECT * FROM aes";
			PreparedStatement preparedStatement = conn.prepareStatement(querySecretKey);
			
			ResultSet rset = preparedStatement.executeQuery();
			
			while (rset.next()) {
				String encodedKey = rset.getString("secretkey");
				byte[] decodedKey = Base64.getDecoder().decode(encodedKey);
				secretKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
			}
			
			rset.close();
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		return secretKey;
		
	}

}
