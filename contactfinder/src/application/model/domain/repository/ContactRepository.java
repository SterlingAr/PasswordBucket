package application.model.domain.repository;

import java.util.List;
import java.util.UUID;

import application.model.domain.Contact;

public interface ContactRepository {
	
	List<Contact> getAllContacts();
	
	List<Contact> getContact(String name);
	
	Contact getContact(UUID id);
	
	void deleteContact(Contact contact);
	
	void deleteAllContacts();
	
	void addContact(Contact contact);
	
	void editContact(Contact contact);
	

}
