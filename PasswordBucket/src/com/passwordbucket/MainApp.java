package com.passwordbucket;

import java.io.IOException;

import com.passwordbucket.model.domain.Entry;
import com.passwordbucket.model.domain.EntryList;
import com.passwordbucket.model.services.EntryListService;
import com.passwordbucket.model.services.EntryService;
import com.passwordbucket.model.services.impl.EntryListServiceImpl;
import com.passwordbucket.model.services.impl.EntryServiceImpl;
import com.passwordbucket.view.EditEntryDialogController;
import com.passwordbucket.view.EntryListController;
import com.passwordbucket.view.NewEntryDialogController;
import com.passwordbucket.view.NewEntryListDialogController;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainApp extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;

	/** EntryList and Entry services for data retrieval */

	private EntryListService entryListService = new EntryListServiceImpl();

	/** The data as an observable list of EntryLists */
	private ObservableList<EntryList> listData = FXCollections.observableArrayList(entryListService.getAllLists());

	@Override
	public void start(Stage primaryStage) {

		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Password Bucket");
		this.primaryStage.getIcons().add(new Image("file:resources/images/icon.png"));
		initRootLayout();
		showHomeView();

	}

	/** Returns the data as an observable list oflists */
	public ObservableList<EntryList> getListData() {
		return listData;
	}

	/** Method to initialize the root layout */
	public void initRootLayout() {
		try {
			// Load root layout from file
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();

			// Show te scene containing the root layout

			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/** Returns the main stage */

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public static void main(String[] args) {
		launch(args);
	}

	/** CONTROLLER RELATED METHODS START --- */

	/** Method to initialize the home view */

	public void showHomeView() {

		try {
			// Load home view
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/HomeView.fxml"));
			AnchorPane homeView = (AnchorPane) loader.load();

			// set the home view into the center of the root layout
			rootLayout.setCenter(homeView);

			// give the controller access to the main app
			EntryListController controller = loader.getController();
			controller.setMainApp(this);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/** shows dialog when user clicks Add New Entry Button */

	public void showNewListDialog() {

		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/NewEntryListDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// Create the dialog stage
			Stage dialogStage = new Stage();
			dialogStage.setTitle("New List");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// set the controller
			NewEntryListDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);

			controller.setMainApp(this);// so I can later refresh the list

			dialogStage.showAndWait();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/** show the add new entry dialog */

	public void showNewEntryDialog(EntryList el, EntryListController entryListController) {

		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/NewEntryDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// Create the dialog stage
			Stage dialogStage = new Stage();
			dialogStage.setTitle("New Entry");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// set the controller
			NewEntryDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setEntryList(el); // I set the list where I insert the
											// entry
			controller.setMainApp(this);// so I can later refresh the list
			controller.setEntryListController(entryListController);
			dialogStage.showAndWait();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void showEditEntryDialog(Entry entry) {

		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/EditEntryDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			
			
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Edit Entry");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);
			
			//load controller
			EditEntryDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setEntry(entry);//set the entry to edit on stage
			controller.setMainApp(this);
			dialogStage.showAndWait();//always put this one after the controller!

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/** ----CONTROLLERS RELATED METHODS END */

}
