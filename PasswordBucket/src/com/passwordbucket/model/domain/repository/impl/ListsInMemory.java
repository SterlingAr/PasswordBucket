package com.passwordbucket.model.domain.repository.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.passwordbucket.model.domain.EntryList;
import com.passwordbucket.model.domain.repository.EntryRepository;
import com.passwordbucket.model.domain.repository.ListRepository;

public class ListsInMemory implements ListRepository {

	private static final String DB_URL = "jdbc:sqlite:PasswordBucket.db";
	private static final String DB_USER = "root";
	private static final String DB_PASSWD = "root";
	private EntryRepository entriesInMemory = new EntriesInMemory();

	@Override
	public EntryList getListByName(String name) {

		EntryList entryList = null;

		try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWD);) {

			String byNameQuery = "SELECT * FROM list WHERE title = ?";
			PreparedStatement preparedStatement = conn.prepareStatement(byNameQuery);
			preparedStatement.setString(1, name);

			ResultSet rset = preparedStatement.executeQuery();

			while (rset.next()) {
				
				UUID id_list = UUID.fromString(rset.getString("id_list"))	;
				String title = rset.getString("title");

				entryList = new EntryList(title, id_list);

			}
			rset.close();
		} catch (SQLException ex) {
			ex.printStackTrace();

		}

		return entryList;
	}

	@Override
	public List<EntryList> getAllLists() {

		List<EntryList> lel = new ArrayList<>();

		try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWD);
				Statement stmt = conn.createStatement();) {

			String selectQuery = "SELECT * FROM list";

			ResultSet rset = stmt.executeQuery(selectQuery);

			while (rset.next()) {
				
				UUID id_list = UUID.fromString(rset.getString("id_list"));
				String title = rset.getString("title");

				EntryList el = new EntryList(title, id_list);

				lel.add(el);

			}
			rset.close();
		} catch (SQLException ex) {

			ex.printStackTrace();

		} finally {

		}

		return lel;
	}

	@Override
	public boolean doesTheListExist(String listName) {
		try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWD);) {

			String doesExistQuery = "SELECT * FROM list WHERE title = ?";
			PreparedStatement preparedStatement = conn.prepareStatement(doesExistQuery);
			preparedStatement.setString(1, listName);

			ResultSet rset = preparedStatement.executeQuery();

			if (rset.next() == false) {
				return false;
			} else {
				return true;
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return false;
	}

	
	@Override
	public void newEmptyList(EntryList el) {

		try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWD);) {

			String newListQuery = "INSERT INTO list (id_list,title) VALUES (?,?)";

			PreparedStatement preparedStatement = conn.prepareStatement(newListQuery);
			preparedStatement.setString(1, el.getIdList().toString());
			preparedStatement.setString(2, el.getTitle());
			preparedStatement.executeUpdate();

		} catch (SQLException ex) {

			ex.printStackTrace();

		}

	}

	@Override
	public void deleteList(EntryList el) {

		try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWD);) {
			entriesInMemory.deleteAllEntries(el);

			String queryDeleteList = "DELETE FROM list where id_list = ?";

			PreparedStatement preparedStatement = conn.prepareStatement(queryDeleteList);
			preparedStatement.setString(1, el.getIdList().toString());

			preparedStatement.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

	}

	@Override
	public void deleteMultipleLists(List<EntryList> lel) {
		try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWD);) {
			
			lel.forEach(currentList -> {
				entriesInMemory.deleteAllEntries(currentList);
				deleteList(currentList);
			});

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

	}

}
