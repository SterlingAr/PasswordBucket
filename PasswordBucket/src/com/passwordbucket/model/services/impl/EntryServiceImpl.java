package com.passwordbucket.model.services.impl;

import java.util.List;

import com.passwordbucket.model.domain.Entry;
import com.passwordbucket.model.domain.EntryList;
import com.passwordbucket.model.domain.repository.EntryRepository;
import com.passwordbucket.model.domain.repository.impl.EntriesInMemory;
import com.passwordbucket.model.services.EncryptionService;
import com.passwordbucket.model.services.EntryService;

public class EntryServiceImpl implements EntryService {

	private EntryRepository entryRepository = new EntriesInMemory();
	private EncryptionService passwordEncryption = new EncryptionServiceImpl();

	@Override
	public void addEntry(Entry entry, EntryList entryList) {
		/** encrypt password before storing to database */
		entry.setPassword(passwordEncryption.encryptPassword(entry.getPassword()));
		entryRepository.addEntry(entry, entryList);

	}

	@Override
	public void deleteEntry(Entry entry) {

		entryRepository.deleteEntry(entry);

	}

	@Override
	public List<Entry> getAllEntries(EntryList list) {
		
		List<Entry> decryptedEntries =  entryRepository.getAllEntries(list);
		
		decryptedEntries.forEach(entry -> {
			entry.setPassword(passwordEncryption.decryptPassword(entry.getPassword()));
		});
		
		return decryptedEntries;
	}

	@Override
	public Entry getEntryByLink(EntryList list, String link) {
		
		
		
		Entry decodedPassword = entryRepository.getEntryByLink(list, link);
		decodedPassword.setPassword(passwordEncryption.decryptPassword(decodedPassword.getPassword()));
		
		return decodedPassword;
	}

	@Override
	public Entry getEntryByUser(EntryList list, String userName) {
		
		Entry decodedPassword = entryRepository.getEntryByLink(list, userName);
		decodedPassword.setPassword(passwordEncryption.decryptPassword(decodedPassword.getPassword()));
		
		return entryRepository.getEntryByUser(list, userName);
	}

	@Override
	public void deleteAllEntries(EntryList list) {
		entryRepository.deleteAllEntries(list);
	}
	
	

}
