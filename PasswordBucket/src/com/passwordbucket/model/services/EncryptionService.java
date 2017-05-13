package com.passwordbucket.model.services;


/**
 * 
 * Encrypts and decrypts password, so as to not store it in text form
 * @author ik012982i9
 * @category encryption
 * @see EncryptionServiceImpl 
 *  
 *  
 *  */
public interface EncryptionService {
	/**
	 * 
	 *  Encrypts a password using an AES secret key stored in a safe location.
	 *  
	 *  @param password  A variable of type String
	 *  @return AES encrypted password as String
	 *  */
	String encryptPassword(String password);
	
	/**
	 * 
	 *  Decrypts a password
	 *  
	 *  */
	String decryptPassword(String password);
	
}
