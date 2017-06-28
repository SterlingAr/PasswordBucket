package application.model.domain.repository.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import application.model.domain.Contact;
import application.model.domain.repository.ContactRepository;

public class InMemoryContactRepository implements ContactRepository {

	private static final String DB_URL = "jdbc:sqlite:contact.db";
	private static final String DB_USER = "root";
	private static final String DB_PASSWD = "root";

	@Override
	public List<Contact> getAllContacts() {

		try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWD);) {

			List<Contact> contactList = new ArrayList<>();

			String queryAllContacts = "SELECT * FROM CONTACTS";

			PreparedStatement preparedStatement = conn.prepareStatement(queryAllContacts);

			ResultSet rset = preparedStatement.executeQuery();

			while (rset.next()) {

				contactList.add(new Contact(UUID.fromString(rset.getString("id")), rset.getString("name"),
						rset.getString("phone_number"), rset.getInt("age")));
			}

			return contactList;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public List<Contact> getContact(String name) {

		try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWD);) {

			String queryByName = "SELECT * FROM CONTACTS WHERE name = ?";
			PreparedStatement preparedStatement = conn.prepareStatement(queryByName);
			preparedStatement.setString(1, name);

			// ResultSet rset =

		} catch (SQLException e) {

		}

		return null;
	}

	@Override
	public void deleteContact(Contact contact) {

		try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWD);) {

			String queryDeleteEntry = "DELETE FROM contacts WHERE id = ?";
			PreparedStatement preparedStatement = conn.prepareStatement(queryDeleteEntry);
			preparedStatement.setString(1, contact.getId().toString());
			preparedStatement.executeUpdate();

		} catch (SQLException ex) {

			ex.printStackTrace();

		}

	}

	@Override
	public void deleteAllContacts() {

		try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWD);) {

			String queryDeleteEntry = "DELETE FROM contacts";
			PreparedStatement preparedStatement = conn.prepareStatement(queryDeleteEntry);
			preparedStatement.executeUpdate();

		} catch (SQLException ex) {

			ex.printStackTrace();

		}

	}

	@Override
	public void addContact(Contact contact) {

		try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWD);) {

			String queryNewEntry = "INSERT INTO CONTACTS (id,name,phone_number,age) VALUES (?,?,?,?)";

			PreparedStatement preparedStatement = conn.prepareStatement(queryNewEntry);
			preparedStatement.setString(1, contact.getId().toString());
			preparedStatement.setString(2, contact.getName());
			preparedStatement.setString(3, contact.getPhoneNumber());
			preparedStatement.setInt(4, contact.getAge());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();

		}

	}

	@Override
	public void editContact(Contact contact) {

		try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWD);) {

			String queryEditContact = "UPDATE contact SET name = ? , phoneNumber = ? , age = ? WHERE id = ?";

			PreparedStatement preparedStatement = conn.prepareStatement(queryEditContact);
			preparedStatement.setString(2, contact.getName());
			preparedStatement.setString(3, contact.getPhoneNumber());
			preparedStatement.setInt(4, contact.getAge());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {

		}

	}

	@Override
	public Contact getContact(UUID id) {
		return null;
	}

}
