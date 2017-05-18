package com.passwordbucket.model.domain.repository;

import java.util.List;

import com.passwordbucket.model.domain.Entry;
import com.passwordbucket.model.domain.EntryList;

/**
 *
 * I can recover entries method to get a list of entries method to get entry
 * based on link (REGEX) method to get entries based on user method to add entry
 * method to delete entry method to multiple entries method to delete all
 * entries method to modify an update
 */

public interface EntryRepository {

	List<Entry> getAllEntries(EntryList list);

	Entry getEntryByLink(EntryList list, String link);

	Entry getEntryByUser(EntryList list, String userName);
	
	boolean doesEntryExist(String entryName);

	void addEntry(Entry entry, EntryList entryList);

	void deleteEntry(Entry entry);

	void deleteMultipleEntries(List<Entry> entries, EntryList entryList);

	void deleteAllEntries(EntryList entryList);

	void modifyEntry(Entry entry);

}
