package com.passwordbucket.model.domain.repository;

import javax.crypto.SecretKey;

public interface RetrieveSecretKey {
	
	 SecretKey getSecretEncryptionKey();

}
