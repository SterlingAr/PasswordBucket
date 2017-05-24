package application.model.services.impl;

import java.util.List;
import java.util.UUID;

import application.model.domain.Contact;
import application.model.domain.repository.ContactRepository;
import application.model.domain.repository.impl.InMemoryContactRepository;
import application.model.services.ContactService;

public class ContactServiceImpl implements ContactService{
	
	private ContactRepository contactRepository = new InMemoryContactRepository();

	@Override
	public List<Contact> getAllContacts() {
		return contactRepository.getAllContacts();
	}

	@Override
	public List<Contact> getContact(String name) {
		return contactRepository.getContact(name);
	}

	@Override
	public Contact getContact(UUID id) {
		return contactRepository.getContact(id);
	}

	@Override
	public void deleteContact(Contact contact) {
		
		contactRepository.deleteContact(contact);
		
	}

	@Override
	public void deleteAllContacts() {
		contactRepository.deleteAllContacts();
	}

	@Override
	public void addContact(Contact contact) {
		
		contactRepository.addContact(contact);
		
	}

	@Override
	public void editContact(Contact contact) {
		// TODO Auto-generated method stub
		
	}

}
