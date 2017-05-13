package com.passwordbucket.model.services.impl;

import java.util.List;

import com.passwordbucket.model.domain.EntryList;
import com.passwordbucket.model.domain.repository.ListRepository;
import com.passwordbucket.model.domain.repository.impl.ListsInMemory;
import com.passwordbucket.model.services.EntryListService;

public class EntryListServiceImpl implements EntryListService {

	private ListRepository listRepository = new ListsInMemory();

	@Override
	public void newEmptyList(EntryList el) {

		if (listRepository.doesTheListExist(el.getTitle())) {

			throw new IllegalArgumentException("A list with the same name already exists.");

		} else {
			
			listRepository.newEmptyList(el);

		}

	}

	@Override
	public EntryList getListByName(String name) {
		// TODO Auto-generated method stub
		return listRepository.getListByName(name);
	}


	@Override
	public void deleteList(EntryList el) {

		listRepository.deleteList(el);

	}

	@Override
	public void deleteMultipleLists(List<EntryList> lel) {
		listRepository.deleteMultipleLists(lel);
	}

	@Override
	public List<EntryList> getAllLists() {

		return listRepository.getAllLists();
	}

	@Override
	public boolean doesTheListExist(String listName) {
		// TODO Auto-generated method stub
		return listRepository.doesTheListExist(listName);
	}

}
