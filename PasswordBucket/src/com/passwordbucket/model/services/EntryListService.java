package com.passwordbucket.model.services;

import java.util.List;

import com.passwordbucket.model.domain.EntryList;

public interface EntryListService {
	
	List<EntryList> getAllLists();
	
	void newEmptyList(EntryList el);
	
	boolean doesTheListExist(String listName);

	EntryList getListByName(String name);
	
    void deleteList(EntryList el);
    
    void deleteMultipleLists(List<EntryList> lel);
    
    

}
