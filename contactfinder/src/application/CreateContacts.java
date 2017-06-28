package application;

import application.model.domain.Contact;
import application.model.services.ContactService;
import application.model.services.impl.ContactServiceImpl;

public class CreateContacts {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		 ContactService contactService = new ContactServiceImpl();
		
		for (int i = 0; i < 50 ; i++){
			
			Contact contact = new Contact("Name"+i,"ImaginaryNumber"+i,22+i);
			contactService.addContact(contact);
			
		}
		
	}

}
