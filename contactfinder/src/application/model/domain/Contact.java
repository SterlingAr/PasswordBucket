package application.model.domain;

import java.util.UUID;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Contact {

	private SimpleObjectProperty<UUID> id;

	private StringProperty name;

	private StringProperty phoneNumber;

	private IntegerProperty age;
	
	public Contact(){
		
	}
	

	public Contact(String name, String phoneNumber, int age) {
		this.name = new SimpleStringProperty(name);
		this.phoneNumber = new SimpleStringProperty(phoneNumber);
		this.age = new SimpleIntegerProperty(age);
		this.id = new SimpleObjectProperty<UUID>(UUID.randomUUID());

	}

	public Contact(UUID id, String name, String phoneNumber, int age) {
		this.name = new SimpleStringProperty(name);
		this.phoneNumber = new SimpleStringProperty(phoneNumber);
		this.age = new SimpleIntegerProperty(age);
		this.id = new SimpleObjectProperty<UUID>(id);

	}

	// POJO Getters & setters
	public String getName() {
		return name.get();
	}

	public void setName(String name) {
		this.name.set(name);
		;
	}

	public String getPhoneNumber() {
		return phoneNumber.get();
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber.set(phoneNumber);
		;
	}

	public int getAge() {
		return age.get();
	}

	public void setAge(int age) {
		this.age.set(age);
		;
	}

	public UUID getId() {
		return id.get();
	}

	public void setId(UUID id) {
		this.id.set(id);
	}

	// JAVAFX return property methods

	public SimpleObjectProperty<UUID> idProperty() {
		return id;
	}

	public StringProperty nameProperty() {
		return name;
	}

	public StringProperty phoneNumberProperty() {
		return phoneNumber;
	}

	public IntegerProperty ageProperty() {
		return age;
	}

	@Override
	public String toString() {
		return "Contact [id=" + id + ", name=" + name + ", phoneNumber=" + phoneNumber + ", age=" + age + "]";
	}

}
