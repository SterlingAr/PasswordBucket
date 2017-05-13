package com.passwordbucket.model.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.passwordbucket.model.domain.repository.EntryRepository;
import com.passwordbucket.model.domain.repository.impl.EntriesInMemory;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class EntryList implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9091591604027561290L;

	private List<Entry> entryList;
	private StringProperty title;
	private SimpleObjectProperty<UUID> idList;

	// change later for services
	private EntryRepository entryRepository = new EntriesInMemory();
	
	public EntryList() {

	}

	public EntryList(String title) {
		super();

		this.title = new SimpleStringProperty(title);
		this.idList = new SimpleObjectProperty<UUID>(UUID.randomUUID());
		entryList = new ArrayList<>();
	}

	// constructor for existing list
	public EntryList(String title, UUID idList) {
		super();
		this.title = new SimpleStringProperty(title);;
		this.idList =  new SimpleObjectProperty<UUID>(idList);
		entryList = new ArrayList<>(entryRepository.getAllEntries(this));
		

	}

	

	public List<Entry> getEntryList() {
		return entryList;
	}

	public void setEntryList(List<Entry> entryList) {
		this.entryList = entryList;
	}

	public String getTitle() {
		return title.get();
	}

	public void setTitle(String title) {
		this.title.set(title);
	}

	public UUID getIdList() {
		return idList.get();
	}

	public void setIdList(UUID idList) {
		this.idList.set(idList);
	}
	
	
	//JAVAFX PROPERTIES
	

	public StringProperty titleProperty(){
		return title;
	}
	
	public SimpleObjectProperty<UUID> idListProperty(){
		return idList;
	}
	//////
	
	@Override
	public String toString() {
		return "EntryList [entryList=" + entryList + ", title=" + title + ", idList=" + idList + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((entryList == null) ? 0 : entryList.hashCode());
		result = prime * result + ((idList == null) ? 0 : idList.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EntryList other = (EntryList) obj;
		if (entryList == null) {
			if (other.entryList != null)
				return false;
		} else if (!entryList.equals(other.entryList))
			return false;
		if (idList == null) {
			if (other.idList != null)
				return false;
		} else if (!idList.equals(other.idList))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	
	
	//

	

}
