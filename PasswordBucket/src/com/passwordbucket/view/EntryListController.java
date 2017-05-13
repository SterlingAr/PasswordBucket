package com.passwordbucket.view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.passwordbucket.MainApp;
import com.passwordbucket.model.domain.Entry;
import com.passwordbucket.model.domain.EntryList;
import com.passwordbucket.model.services.EntryListService;
import com.passwordbucket.model.services.EntryService;
import com.passwordbucket.model.services.impl.EntryListServiceImpl;
import com.passwordbucket.model.services.impl.EntryServiceImpl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;

public class EntryListController {

	private EntryService entryService;
	private EntryListService entryListService = new EntryListServiceImpl();

	
	/** List columns */
	@FXML
	private TableView<EntryList> entryListTable;
	@FXML
	private TableColumn<EntryList, String> listTitleColumn;

	/** Entry columns */
	@FXML
	private TableView<Entry> entryTable;
	@FXML
	private TableColumn<Entry, String> siteColumn;
	@FXML
	private TableColumn<Entry, String> userColumn;
	@FXML
	private TableColumn<Entry, String> passwordColumn;

	// Reference to the main application
	private MainApp mainApp;

	/** The constructor is called before the initialize method */
	public EntryListController() {

	}

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded
	 */
	@FXML
	private void initialize() {
		// Initialize the list table with its only column
		listTitleColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());

		// listen for selection changes
		
		
		entryTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
		entryListTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		

	}

	/** Is called by the main application to give a reference back to itself */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;

		// add observable list data to the table
		entryListTable.setItems(mainApp.getListData());
	}

	/** Called when the user clicks on the delete button */
	@FXML
	private void handleDeleteList() {
		entryListService = new EntryListServiceImpl();
		
		ObservableList<EntryList> selectedEntries = entryListTable.getSelectionModel().getSelectedItems();
		
		entryListService.deleteMultipleLists(selectedEntries);
		
		entryListTable.getItems().removeAll(selectedEntries);
		
	
	}

	@FXML
	private void handleClickOnList() {
		entryService = new EntryServiceImpl();
		
		int selectedIndex = entryListTable.getSelectionModel().getSelectedIndex();
		EntryList el = entryListTable.getItems().get(selectedIndex);

		List<Entry> listOfEntries = entryService.getAllEntries(el);

		this.entryTable.setItems(FXCollections.observableArrayList(listOfEntries));

		siteColumn.setCellValueFactory(cellData -> cellData.getValue().siteProperty());
		userColumn.setCellValueFactory(cellData -> cellData.getValue().userProperty());
		passwordColumn.setCellValueFactory(cellData -> cellData.getValue().passwordProperty());
		System.out.println("funca?");

	}
	
	/** dirty fix for refreshing entries */
	public void refreshEntries(){
		handleClickOnList();
	}

	@FXML
	private void handleDeleteEntry() {
		entryService = new EntryServiceImpl();

		List<Entry> listofEntries = entryTable.getSelectionModel().getSelectedItems();
		
		listofEntries.forEach(entry -> {
			entryService.deleteEntry(entry);
		});
		
		entryTable.getItems().removeAll(listofEntries);
		initialize();
	}
	
	@FXML
	private void handleNewEntryList(){
		mainApp.showNewListDialog();
	}
	
	@FXML
	private void handleNewEntry(){
		
		int selectedIndex = entryListTable.getSelectionModel().getSelectedIndex();
		
		
		if(selectedIndex >= 0){
		EntryList el = entryListTable.getItems().get(selectedIndex);
		mainApp.showNewEntryDialog(el,this);
		
		} else {
			// show the error message
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(mainApp.getPrimaryStage());
			alert.setTitle("Error de creaci�n");
			alert.setHeaderText("Ninguna lista seleccionada.");
			alert.setContentText("Por favor selecciona una lista");

			alert.showAndWait();
		}
	}
	
	@FXML
	private void handleModEntry(){
		Entry e = entryTable.getSelectionModel().getSelectedItem();
		if (e != null){
		mainApp.showEditEntryDialog(e);
		} else {
			
			// show the error message
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(mainApp.getPrimaryStage());
			alert.setTitle("Error de creaci�n");
			alert.setHeaderText("Ninguna entrada seleccionada.");
			alert.setContentText("Por favor selecciona una entrada");

			alert.showAndWait();
			
		}
	}

}
