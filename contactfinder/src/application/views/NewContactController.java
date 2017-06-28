package application.views;

import application.Main;
import application.model.domain.Contact;
import application.model.services.ContactService;
import application.model.services.impl.ContactServiceImpl;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewContactController {

	@FXML
	private TextField nameField;
	@FXML
	private TextField phoneField;
	@FXML
	private TextField ageField;

	private Stage dialogStage;

	private Main main;

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

	public void setMain(Main main) {
		this.main = main;
	}

	@FXML
	private void initialize() {
	}

	@FXML
	private void handleOk() {
		Contact contact = new Contact(nameField.getText(), phoneField.getText(), Integer.parseInt(ageField.getText()));
		contactService = new ContactServiceImpl();
		contactService.addContact(contact);

		main.getContactData().add(contact);
		dialogStage.close();

	}

	@FXML
	private void handleCancel() {

		dialogStage.close();

	}

}
