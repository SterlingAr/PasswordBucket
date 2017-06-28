package application;

import java.io.IOException;

import com.aquafx_project.AquaFx;


import application.model.domain.Contact;
import application.model.services.ContactService;
import application.model.services.impl.ContactServiceImpl;
import application.views.ContactController;
import application.views.NewContactController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Main extends Application {

	private ContactService contactService = new ContactServiceImpl();
	private ObservableList<Contact> contactData = FXCollections.observableArrayList(contactService.getAllContacts());

	private Stage primaryStage;
	private BorderPane rootLayout;

	@Override
	public void start(Stage primaryStage) {

		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Password Bucket");
		initRootLayout();
		initContactView();
	}

	public void initRootLayout() {
		try {
			// Load root layout from file
			AquaFx.style();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("views/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();

			// Show te scene containing the root layout

			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void initContactView() {

		try {
			// Load home view
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("views/ContactView.fxml"));
			AnchorPane contactView = (AnchorPane) loader.load();

			// set the home view into the center of the root layout
			rootLayout.setCenter(contactView);

			// give the controller access to the main app
			ContactController controller = loader.getController();
			controller.setMain(this);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	
	public void showNewContactDialog() {

		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("views/NewContactDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// Create the dialog stage
			Stage dialogStage = new Stage();
			dialogStage.setTitle("New Contact");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// set the controller
			NewContactController controller = loader.getController();
			controller.setDialogStage(dialogStage);

			controller.setMain(this);// so I can later refresh the list

			dialogStage.showAndWait();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	

	public static void main(String[] args) {
		launch(args);
	}

	public ObservableList<Contact> getContactData() {
		return contactData;
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

}
