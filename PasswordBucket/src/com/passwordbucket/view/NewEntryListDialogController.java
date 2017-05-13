package com.passwordbucket.view;

import com.passwordbucket.MainApp;
import com.passwordbucket.model.domain.EntryList;
import com.passwordbucket.model.services.EntryListService;
import com.passwordbucket.model.services.impl.EntryListServiceImpl;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class NewEntryListDialogController {

	@FXML
	private TextField newListName;

	private Stage dialogStage;

	private MainApp mainApp;

	private EntryListService entryListService;

	/** Init the controller class */

	@FXML
	private void initialize() {

	}

	/** Sets the stage of this dialog */
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	/** References the main app */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	/** when the user clicks ok */
	@FXML
	private void handleOk() {
		entryListService = new EntryListServiceImpl();
		// use service to add to sql, mainApp to add the list
		EntryList el = new EntryList(newListName.getText());
		try {
			if (!entryListService.doesTheListExist(el.getTitle())) {
				entryListService.newEmptyList(el);
				mainApp.getListData().add(el);
				dialogStage.close();
			} else {

				// show the error message
				Alert alert = new Alert(AlertType.ERROR);
				alert.initOwner(dialogStage);
				alert.setTitle("Error de creación");
				alert.setHeaderText("La lista ya existe!");
				alert.setContentText("Por favor introduce otro nombre!");

				alert.showAndWait();

			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}

	}

	@FXML
	private void handleCancel() {
		dialogStage.close();
	}

}
