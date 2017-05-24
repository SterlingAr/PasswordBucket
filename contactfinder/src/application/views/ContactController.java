package application.views;

import application.Main;
import application.model.domain.Contact;
import javafx.fxml.FXML;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ContactController {

	@FXML
	private TableView<Contact> contactTable;
	@FXML
	private TableColumn<Contact, String> nameColumn;
	@FXML
	private TableColumn<Contact, String> phoneColumn;
	@FXML
	private TableColumn<Contact, String> ageColumn;
	
	private Main main;


	public ContactController() {

	}

	@FXML
	private void initialize() {

		nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
		phoneColumn.setCellValueFactory(cellData -> cellData.getValue().phoneNumberProperty());
		ageColumn.setCellValueFactory(cellData -> cellData.getValue().ageProperty().asString());

		contactTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

	}


	public void setMain(Main main) {
		this.main = main;
		
		contactTable.setItems(main.getContactData());
	}
	
	@FXML
	private void addContact(){
		
		main.showNewContactDialog();
		
	}

}
