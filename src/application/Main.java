package application;

	
import java.io.IOException;

import model.List;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	
	private Stage 	   primaryStage;   // Stage of GUI
	private BorderPane rootLayout;     // UI Root
	
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Chat View");
		
		// Load FXML files and display in GUI
		initRootLayout();
		showMainWindow();
	}

	public void initRootLayout(){
		try{
			// Load RootLayout.fxml from 'view' package
			FXMLLoader loader = new FXMLLoader(); 
			loader.setLocation(Main.class.getResource("../view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();
			// Set the scene
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		}catch (IOException e){
			e.printStackTrace();
		}
	}
	
	public void showMainWindow(){
		try{
			// Load PersonView.fxml from 'view' package
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("../view/mainView.fxml"));
			AnchorPane personView = (AnchorPane) loader.load();
			// Set PersonView in centre of RootLayout
			rootLayout.setCenter(personView);
			System.out.println(loader.getController().toString());
			ListViewController controller = loader.getController();
			controller.setMainApp(this);
		} catch (IOException e){
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
