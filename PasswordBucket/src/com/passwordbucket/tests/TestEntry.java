package com.passwordbucket.tests;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.passwordbucket.model.domain.EntryList;
import com.passwordbucket.model.services.EntryListService;
import com.passwordbucket.model.services.EntryService;
import com.passwordbucket.model.services.impl.EntryListServiceImpl;
import com.passwordbucket.model.services.impl.EntryServiceImpl;

public class TestEntry {

	public static void main(String[] args) {

		EntryListService entryListService = new EntryListServiceImpl();
		EntryService entryService = new EntryServiceImpl();

		/**
		 * CREATE LIST TEST EntryList el = new EntryList("list1");
		 * entryListService.newEmptyList(el);
		 * 
		 * 
		 * for(){}
		 * 
		 * for (int i = 0; i <= 100 ; i++) { EntryList el = new
		 * EntryList("List"+i); entryListService.newEmptyList(el); }
		 * 
		 * for(int i = 0; i<=100; i++){ Entry e = new Entry("www.sample.com",
		 * "userName", "password"); Entry entry = new Entry("Example",
		 * "example", "e43214213"); Entry entry2 = new Entry("Example",
		 * "example", "5145234324"); Entry entry3 = new Entry("Example",
		 * "example", "13432423423"); Entry entry4 = new Entry("Example",
		 * "example", "3432f2134f213d32");
		 * entryService.addEntry(e,entryListService.getListByName("List"+i));
		 * entryService.addEntry(entry,entryListService.getListByName("List"+i));
		 * entryService.addEntry(entry2,entryListService.getListByName("List"+i));
		 * entryService.addEntry(entry3,entryListService.getListByName("List"+i));
		 * entryService.addEntry(entry4,entryListService.getListByName("List"+i));
		 * 
		 * 
		 * }
		 * 
		 * 
		 */

		/**
		 * TEST CREATE ENTRY
		 * 
		 * Entry entry = new Entry("Example", "example", "example"); Entry
		 * entry2 = new Entry("Example", "example", "example"); Entry entry3 =
		 * new Entry("Example", "example", "example"); Entry entry4 = new
		 * Entry("Example", "example", "example");
		 * 
		 * entryService.addEntry(entry,
		 * entryListService.getListByName("list1"));
		 * entryService.addEntry(entry2,
		 * entryListService.getListByName("list1"));
		 * entryService.addEntry(entry3,
		 * entryListService.getListByName("list1"));
		 * 
		 * 
		 * Entry entry = new Entry("Example", "example", "e43214213"); Entry
		 * entry2 = new Entry("Example", "example", "5145234324"); Entry entry3
		 * = new Entry("Example", "example", "13432423423"); Entry entry4 = new
		 * Entry("Example", "example", "3432f2134f213d32");
		 * 
		 * entryService.addEntry(entry, entryListService.getListByName("Lista
		 * Banco")); entryService.addEntry(entry2,
		 * entryListService.getListByName("Mercadona"));
		 * entryService.addEntry(entry3,
		 * entryListService.getListByName("Casa"));
		 * entryService.addEntry(entry4, entryListService.getListByName("Rincon
		 * de las matematicas")); entryService.addEntry(entry,
		 * entryListService.getListByName("Rincon de las matematicas2"));
		 * entryService.addEntry(entry2,
		 * entryListService.getListByName("HDDs"));
		 * entryService.addEntry(entry3,
		 * entryListService.getListByName("list1"));
		 * entryService.addEntry(entry4,
		 * entryListService.getListByName("list1"));
		 * 
		 */

		/**
		 * DELETE QUERIES
		 * 
		 * // delete by link EntryList entryList =
		 * entryListService.getListByName("list3"); Entry entry =
		 * entryService.getEntryByLink(entryList, "aa1a");
		 * entryService.deleteEntry(entry, entryList);
		 * 
		 * // delete by username
		 * 
		 * EntryList entryList = entryListService.getListByName("list3"); Entry
		 * entry = entryService.getEntryByUser(entryList, "bbssb");
		 * entryService.deleteEntry(entry, entryList);
		 * 
		 * 
		 */

		/**
		 * LOAD SELECTED LIST
		 * 
		 * EntryList selectedList = entryListService.getListByName("list2");
		 * 
		 * EntryList loadedList =
		 * entryListService.loadSelectedList(selectedList);
		 * 
		 * System.out.println(loadedList.toString());
		 * 
		 * 
		 */

		/**
		 * DELETE ALL ENTRIES
		 * 
		 * entryService.deleteAllEntries(entryListService.getListByName("list3"));
		 */

		/**
		 * DELETE LIST
		 * entryListService.deleteList(entryListService.getListByName("list3"));
		 * 
		 * 
		 */

		/**
		 * DELETE MULTIPLE LISTS
		 * 
		 * List<EntryList> listofLists = new ArrayList<>();
		 * listofLists.add(entryListService.getListByName("list2"));
		 * listofLists.add(entryListService.getListByName("list5"));
		 * listofLists.add(entryListService.getListByName("list6"));
		 * 
		 * entryListService.deleteMultipleLists(listofLists); * /
		 */
		// entryService.deleteAllEntries(entryListService.getListByName("list1"));

		// Entry entry4 = new Entry("Exampaslee", "example",
		// "exampladasddasfasadae");

		// entryService.addEntry(entry4,
		// entryListService.getListByName("list1"));
		EntryList el = entryListService.getListByName("List3");
		List<EntryList> lel = entryListService.getAllLists();
		
		/**
		 * 
		CSVWriter writer = null;
		try {
			writer = new CSVWriter(new FileWriter("yourfile2.csv"));
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}
		// feed in your array (or convert your data to an array)
		 
		String[] entries = "title,entries,stuff,morestuff,example".split(",");
		
		
		writer.writeNext(entries);
		try {
			writer.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		 *  
		 *  */
		
	
		
	

	}

	public static String randomString() {
		int length = 10;
		Random r = new Random(); // perhaps make it a class variable so you
									// don't make a new one every time
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {
			char c = (char) (r.nextInt((int) (Character.MAX_VALUE)));
			sb.append(c);
		}
		return sb.toString();
	}

}
