package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.fxml.FXML;

public class SidebarController extends Application {
	
	@FXML BorderPane borderpane;
	
	public void start(Stage primaryStage) {
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getClassLoader().getResource("Sidebar.fxml"));
			Scene scene = new Scene(root,800,600);	
			scene.getStylesheets().add("application.css");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@FXML void start(MouseEvent event) {
    	System.out.println("Hello World");

    }

    @FXML void stop(MouseEvent event) {
    	System.out.println("Hello World");
    }
	
    @FXML void openPreferences(MouseEvent event) {
    	Pane root = null;
    	try {
			root = (Pane)FXMLLoader.load(getClass().getClassLoader().getResource("PreferencesControls.fxml"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	borderpane.setCenter(root);
    }

    @FXML void openProtoss(MouseEvent event) {
    	Pane root = null;
    	try {
			root = (Pane)FXMLLoader.load(getClass().getClassLoader().getResource("ProtossControls.fxml"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	borderpane.setCenter(root);
    }

    @FXML void openTerran(MouseEvent event) {
    	Pane root = null;
    	try {
			root = (Pane)FXMLLoader.load(getClass().getClassLoader().getResource("TerranControls.fxml"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	borderpane.setCenter(root);
    }

    @FXML void openZerg(MouseEvent event) {
   	
    	Pane root = null;
    	try {
			root = (Pane)FXMLLoader.load(getClass().getClassLoader().getResource("ZergControls.fxml"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	borderpane.setCenter(root);
    }    
    
}
