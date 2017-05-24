package com.passwordbucket.view;

import com.passwordbucket.MainApp;
import com.passwordbucket.model.domain.Entry;
import com.passwordbucket.model.services.EncryptionService;
import com.passwordbucket.model.services.EntryService;
import com.passwordbucket.model.services.PasswordServices;
import com.passwordbucket.model.services.impl.EncryptionServiceImpl;
import com.passwordbucket.model.services.impl.EntryServiceImpl;
import com.passwordbucket.model.services.impl.PasswordServicesImpl;
import com.passwordbucket.model.validators.EntryValidator;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditEntryDialogController {

	@FXML
	private TextField entryURL;
	@FXML
	private TextField entryUsername;
	@FXML
	private TextField entryPassword;
	@FXML
	private Label passwordStrengthMeter;

	private Entry entry;
	private Stage dialogStage;
	private MainApp mainApp;
	private PasswordServices passwordServices;
	private EntryValidator entryValidator;
	private EntryService entryService;
	private EncryptionService encrypt;
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

	public void setEntry(Entry entry) {// initialize the fields with the current
										// entry data
		this.entry = entry;
		System.out.println(entry.toString());
		entryURL.setText(entry.getSite());
		entryUsername.setText(entry.getUser());
		entryPassword.setText(entry.getPassword());

	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	
	public void setMainApp(MainApp mainApp){
		this.mainApp = mainApp;
	}

	@FXML
	private void handleGeneratePassword() {
		passwordServices = new PasswordServicesImpl();
		entryPassword.setText(passwordServices.generatePassword());
	}

	@FXML
	private void handleOk() {
		entryValidator = new EntryValidator();
		entryService = new EntryServiceImpl();
		encrypt = new EncryptionServiceImpl();
		
		Entry e = new Entry(entryURL.getText(), entryUsername.getText(), entryPassword.getText());

		if (entryValidator.validateEntryObject(e)) {
			
			
			entry.setSite(entryURL.getText());
			entry.setUser(entryUsername.getText());
			entry.setPassword(entryPassword.getText());
			
			e.setId(entry.getId());
			e.setPassword(encrypt.encryptPassword(e.getPassword()));
			entryService.modifyEntry(e);
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

}
