package com.passwordbucket.view;

import com.passwordbucket.MainApp;
import com.passwordbucket.model.domain.Entry;
import com.passwordbucket.model.domain.EntryList;
import com.passwordbucket.model.services.EntryService;
import com.passwordbucket.model.services.PasswordServices;
import com.passwordbucket.model.services.impl.EntryServiceImpl;
import com.passwordbucket.model.services.impl.PasswordServicesImpl;
import com.passwordbucket.model.validators.EntryValidator;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class NewEntryDialogController {

	@FXML
	private TextField entryURL;
	@FXML
	private TextField entryUser;
	@FXML
	private TextField entryPassword;
	@FXML
	private Label passwordStrengthMeter;

	private Stage dialogStage;
	private MainApp mainApp;
	private EntryList entryList;// I set the current selected list so i can add
								// the entry to it.

	private EntryService entryService;
	private PasswordServices passwordServices;
	private EntryListController entryListController; // reference to update
														// selected list

	@FXML
	private void initialize() {
		
		handlePasswordStrength();

		entryPassword.textProperty().addListener(new ChangeListener<String>(){

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				
				handlePasswordStrength();
			}
			
		});
		

	}

	public void setEntryListController(EntryListController entryListController) {
		this.entryListController = entryListController;
	}

	public void setDialogStage(Stage stage) {
		this.dialogStage = stage;
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	public void setEntryList(EntryList el) {
		this.entryList = el;
	}

	
	private void handlePasswordStrength(){
		passwordServices = new PasswordServicesImpl();
		String givenResult = passwordServices.passwordStrengthMeteredResult(entryPassword.getText());
		
		if(givenResult.equals("Sin contraseña")){
			passwordStrengthMeter.setText(givenResult);
			passwordStrengthMeter.setTextFill(Color.web("#DC143C"));
		}
		
		if(givenResult.equals("Muy débil")){
			passwordStrengthMeter.setText(givenResult);
			passwordStrengthMeter.setTextFill(Color.web("#E9967A"));
		}
		
		if(givenResult.equals("Débil")){
			passwordStrengthMeter.setText(givenResult);
			passwordStrengthMeter.setTextFill(Color.web("#FF4500"));
		}
		
		if(givenResult.equals("Aceptable")){
			passwordStrengthMeter.setText(givenResult);
			passwordStrengthMeter.setTextFill(Color.web("#FFFF33"));
		}
		
		if(givenResult.equals("Excelente")){
			passwordStrengthMeter.setText(givenResult);
			passwordStrengthMeter.setTextFill(Color.web("#32CD32"));
		}
		
	}

	@FXML
	private void handleOk() {
		entryService = new EntryServiceImpl();
		EntryValidator entryValidator = new EntryValidator();
		Entry e = new Entry(entryURL.getText(), entryUser.getText(), entryPassword.getText());

		if (entryValidator.validateEntryObject(e) == true) {

			entryService.addEntry(e, entryList);
			entryList.getEntryList().add(e); // update selected object with new
												// entry
			entryListController.refreshEntries();// refresh entries
			dialogStage.close();

		} else {
			// show the error message
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(mainApp.getPrimaryStage());
			alert.setTitle("Error de creaciï¿½n");
			alert.setHeaderText(entryValidator.getErrorMessage());
			alert.setContentText("Por favor corrige los errores");

			alert.showAndWait();
		}

	}

	@FXML
	private void handleCancel() {
		dialogStage.close();
	}

	@FXML
	private void handlePasswordGenerator() {
		passwordServices = new PasswordServicesImpl();
		this.entryPassword.setText(passwordServices.generatePassword());

	}

}
