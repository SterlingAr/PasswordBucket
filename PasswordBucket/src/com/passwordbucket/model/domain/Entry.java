package com.passwordbucket.model.domain;

import java.io.Serializable;
import java.util.UUID;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Entry implements Serializable {
	/**
	 * a
	 */
	private static final long serialVersionUID = 7232104882194680770L;
	private StringProperty site;
	private StringProperty user;
	private StringProperty password;
	private SimpleObjectProperty<UUID> id;
	private SimpleObjectProperty<UUID> id_list;
	

	
	public Entry(String site, String user, String password) {
		super();
		
		this.site = new SimpleStringProperty(site);
		this.user = new SimpleStringProperty(user);
		this.password = new SimpleStringProperty(password);
		this.id = new SimpleObjectProperty<UUID>(UUID.randomUUID());

	}


	//existing entry
	public Entry(String site, String user, String password, UUID id, UUID id_list) {
		super();
		this.site = new SimpleStringProperty(site);
		this.user = new SimpleStringProperty(user);
		this.password = new SimpleStringProperty(password);
		this.id = new SimpleObjectProperty<UUID>(id);
		this.id_list = new SimpleObjectProperty<UUID>(id_list);
	}

	// POJO GETTERS && SETTERS
	public String getSite() {
		return site.get();
	}


	public void setSite(String site) {
		this.site.set(site);;
	}


	public String getUser() {
		return user.get();
	}


	public void setUser(String user) {
		this.user.set(user);
	}


	public String getPassword() {
		return password.get();
	}


	public void setPassword(String password) {
		this.password.set(password);
	}


	public UUID getId() {
		return id.get();
	}


	public void setId(UUID id) {
		this.id.set(id);;
	}


	public UUID getId_list() {
		return id_list.get();
	}


	public void setId_list(UUID id_list) {
		this.id_list.set(id_list);
	}
	
	//JAVAFX PROPERTIES
	
	public StringProperty siteProperty(){
		return site;
	}
	
	public StringProperty userProperty(){
		return user;
	}
	
	public StringProperty passwordProperty(){
		return password;
	}
	
	public SimpleObjectProperty<UUID> idProperty(){
		return id;
	}
	
	public SimpleObjectProperty<UUID> idListProperty(){
		return id_list;
	}
	
	//misc
	@Override
	public String toString() {
		return "Entry [site=" + site + ", user=" + user + ", password=" + password + ", id=" + id + ", id_list="
				+ id_list + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((id_list == null) ? 0 : id_list.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((site == null) ? 0 : site.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		Entry other = (Entry) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (id_list == null) {
			if (other.id_list != null)
				return false;
		} else if (!id_list.equals(other.id_list))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (site == null) {
			if (other.site != null)
				return false;
		} else if (!site.equals(other.site))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	
	

	
	
}
