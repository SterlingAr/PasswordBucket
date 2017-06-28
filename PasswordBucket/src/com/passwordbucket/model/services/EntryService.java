package com.passwordbucket.model.services;

import java.util.List;

import com.passwordbucket.model.domain.Entry;
import com.passwordbucket.model.domain.EntryList;

public interface EntryService {
	
	
	void addEntry(Entry entry, EntryList entryList);

	void deleteEntry(Entry entry);
	
	List<Entry> getAllEntries(EntryList list);

	Entry getEntryByLink(EntryList list, String link);

	Entry getEntryByUser(EntryList list, String userName);
	
	void deleteAllEntries(EntryList list);
	
	void modifyEntry(Entry entry);
	
	
	

}
