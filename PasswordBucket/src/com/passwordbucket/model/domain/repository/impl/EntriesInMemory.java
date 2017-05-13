
package com.passwordbucket.model.domain.repository.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.passwordbucket.model.domain.Entry;
import com.passwordbucket.model.domain.EntryList;
import com.passwordbucket.model.domain.repository.EntryRepository;

public class EntriesInMemory implements EntryRepository {

	private static final String DB_URL = "jdbc:sqlite:PasswordBucket.db";
	private static final String DB_USER = "root";
	private static final String DB_PASSWD = "root";

	@Override
	public List<Entry> getAllEntries(EntryList list) {

		List<Entry> entryList = new ArrayList<>();
		// look here
		try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWD);) {

			String allEntriesQuery = "SELECT * FROM entry WHERE id_list = ?";
			PreparedStatement preparedStatement = conn.prepareStatement(allEntriesQuery);
			preparedStatement.setString(1, list.getIdList().toString());

			ResultSet rset = preparedStatement.executeQuery();

			while (rset.next()) {
				
			
				
				Entry entry = new Entry(rset.getString("site"), rset.getString("username"), rset.getString("password"),
						UUID.fromString(rset.getString("id_entry")),//convert to UUID object
						UUID.fromString(rset.getString("id_list")));

				entryList.add(entry);

			}

		} catch (SQLException ex) {

			ex.printStackTrace();
		}

		return entryList;
	}

	@Override
	public Entry getEntryByLink(EntryList list, String link) {

		Entry entry = null;

		try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWD);) {

			String queryByLink = "SELECT * FROM entry WHERE id_list = ? AND site = ?";
			PreparedStatement preparedStatement = conn.prepareStatement(queryByLink);
			preparedStatement.setString(1, list.getIdList().toString());
			preparedStatement.setString(2, link);
			ResultSet rset = preparedStatement.executeQuery();

			while (rset.next()) {

				UUID idEntry = UUID.fromString(rset.getString("id_entry"));
				String site = rset.getString("site");
				String user = rset.getString("username");
				String passwd = rset.getString("password");
				UUID id_list = UUID.fromString(rset.getString("id_list"));

				entry = new Entry(site,user,passwd, idEntry, id_list);

			}

		} catch (SQLException ex) {

			ex.printStackTrace();
		}

		return entry;

	}

	@Override
	public Entry getEntryByUser(EntryList list, String userName) {

		Entry entry = null;

		try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWD);) {

			String queryByUser = "SELECT * FROM entry WHERE id_list = ? AND username = ?";
			PreparedStatement preparedStatement = conn.prepareStatement(queryByUser);
			preparedStatement.setString(1, list.getIdList().toString());
			preparedStatement.setString(2, userName);
			ResultSet rset = preparedStatement.executeQuery();

			while (rset.next()) {

				UUID idEntry = UUID.fromString(rset.getString("id_entry"));
				String site = rset.getString("site");
				String user = rset.getString("username");
				String passwd = rset.getString("password");
				UUID id_list = UUID.fromString(rset.getString("id_list"));

				entry = new Entry(site,user,passwd, idEntry, id_list);

			}

		} catch (SQLException ex) {

			ex.printStackTrace();
		}

		return entry;
	}

	@Override
	public void addEntry(Entry entry, EntryList entryList) {

		try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWD);) {

			String queryAddEntry = "INSERT INTO entry (id_entry,site,username,password,id_list) VALUES (?,?,?,?,?)";
			PreparedStatement preparedStatement = conn.prepareStatement(queryAddEntry);
			preparedStatement.setString(1, entry.getId().toString());
			preparedStatement.setString(2, entry.getSite());
			preparedStatement.setString(3, entry.getUser());
			preparedStatement.setString(4, entry.getPassword());
			preparedStatement.setString(5, entryList.getIdList().toString());

			preparedStatement.executeUpdate();

		} catch (SQLException ex) {

			ex.printStackTrace();

		}

	}

	@Override
	public void deleteEntry(Entry entry) {

		try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWD);) {

			String queryDeleteEntry = "DELETE FROM entry WHERE id_entry = ?";
			PreparedStatement preparedStatement = conn.prepareStatement(queryDeleteEntry);
			preparedStatement.setString(1, entry.getId().toString());
			preparedStatement.executeUpdate();
			
		} catch (SQLException ex) {

			ex.printStackTrace();

		}

	}

	@Override
	public boolean doesEntryExist(String entryName) {
		try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWD);) {

			String doesExistQuery = "SELECT * FROM list WHERE title = ?";
			PreparedStatement preparedStatement = conn.prepareStatement(doesExistQuery);
			preparedStatement.setString(1, entryName);

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
	public void deleteMultipleEntries(List<Entry> entries, EntryList entryList) {
		try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWD);) {
			
			

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

	}

	@Override
	public void deleteAllEntries(EntryList entryList) {

		try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWD);) {

			String queryDelAllEntries = "DELETE FROM entry WHERE id_list = ?";
			PreparedStatement preparedStatement = conn.prepareStatement(queryDelAllEntries);
			preparedStatement.setString(1, entryList.getIdList().toString());
			preparedStatement.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

	}

	@Override
	public void modifyEntry(Entry entry, EntryList entryList) {
		// TODO Auto-generated method stub

	}

}
