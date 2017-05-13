package com.passwordbucket.model.domain.repository;

import java.util.List;

import com.passwordbucket.model.domain.EntryList;

public interface ListRepository {
	//display lists and search methods
	List<EntryList> getAllLists();
	
	
	EntryList getListByName(String name);
	
	boolean doesTheListExist(String listName);
	
	
	void newEmptyList(EntryList el);
	
	void deleteList(EntryList el);
	
	void deleteMultipleLists(List<EntryList> lel);
	
	
	
}
