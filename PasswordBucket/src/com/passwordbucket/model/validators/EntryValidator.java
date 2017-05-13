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
			errorMessage += ("El campo 'URL' no puede estár vacío. \n");
		}
		
		if(entry.getSite().length() > 150){
			
			errorMessage += ("El campo 'URL' no peude contener más de 150 caracteres\n");
		}
		//validate user field
		if(entry.getUser() == null || entry.getUser().length() == 0) {
		
			errorMessage += ("El campo 'Usuario' no puede estár vacío. \n");
		}
		
		if(entry.getUser().length() > 150){
	
			errorMessage +=("El campo 'Usuario' no peude contener más de 150 caracteres \n");
			
		}
		//validate password field
		if(entry.getPassword() == null || entry.getPassword().length() == 0) {
		
			errorMessage +=("El campo 'Contraseña' no puede estár vacío. \n");
		}
		
		if(entry.getPassword().length() > 150){
			errorMessage +=("El campo 'Contraseña' no puede contener más de 150 caracteres\n");
		}
		
		
		if(errorMessage.length() == 0) {
			return true;
		} else {
			
			
			return false;
		}
		
	
		
	}

}
