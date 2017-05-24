package application.views;

import application.Main;
import application.model.domain.Contact;
import application.model.services.ContactService;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditContactController {
	
	@FXML
	private TextField nameField;
	@FXML
	private TextField phoneField;
	@FXML
	private TextField ageField;

	private Stage dialogStage;

	private Main main;
	
	private Contact contact;

	private ContactService contactService;

	public Stage getDialogStage() {
		return dialogStage;
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public Main getMain() {
		return main;
	}
	
	public void setContact(Contact contact){
		this.contact = contact;
	}

	public void setMain(Main main) {
		this.main = main;
	}

	@FXML
	private void initialize() {
		
	}
	

}
