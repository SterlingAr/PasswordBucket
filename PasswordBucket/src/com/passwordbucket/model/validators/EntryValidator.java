package com.passwordbucket.model.validators;

import com.passwordbucket.model.domain.Entry;

public class EntryValidator {
	
	
	private String errorMessage="";
	
	public String getErrorMessage(){
		return errorMessage;
	}
	
	public boolean validateEntryObject(Entry entry) {
		
		
		//validate site field
		if(entry.getSite() == null || entry.getSite().length() == 0) {
			errorMessage += ("El campo 'URL' no puede est�r vac�o. \n");
		}
		
		if(entry.getSite().length() > 150){
			
			errorMessage += ("El campo 'URL' no peude contener m�s de 150 caracteres\n");
		}
		//validate user field
		if(entry.getUser() == null || entry.getUser().length() == 0) {
		
			errorMessage += ("El campo 'Usuario' no puede est�r vac�o. \n");
		}
		
		if(entry.getUser().length() > 150){
	
			errorMessage +=("El campo 'Usuario' no peude contener m�s de 150 caracteres \n");
			
		}
		//validate password field
		if(entry.getPassword() == null || entry.getPassword().length() == 0) {
		
			errorMessage +=("El campo 'Contrase�a' no puede est�r vac�o. \n");
		}
		
		if(entry.getPassword().length() > 150){
			errorMessage +=("El campo 'Contrase�a' no puede contener m�s de 150 caracteres\n");
		}
		
		
		if(errorMessage.length() == 0) {
			return true;
		} else {
			
			
			return false;
		}
		
	
		
	}

}
