package application;
	
import java.io.File;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class SidebarController extends Application {
	
	@FXML BorderPane borderpane;
	@FXML Pane centerPane;
	
    private final AtomicBoolean running = new AtomicBoolean(false);	
    private final AtomicLong timeCounter = new AtomicLong(0);
	private Thread thread;

	@FXML Label timer;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getClassLoader().getResource("Sidebar.fxml"));
			Scene scene = new Scene(root,1000,400);	
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
		this.running.set(true);
		
		thread = new Thread(){
			@Override
			public void run(){
				while (running.get()) {
					updateUILabelAsynchronous(timeCounter.toString());
					try {
						playSounds();
					} catch (LineUnavailableException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (UnsupportedAudioFileException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					timeCounter.addAndGet(1);

					try {
						sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};
		
		thread.start();

    }
		
	private void playSounds() throws LineUnavailableException, IOException, UnsupportedAudioFileException {
	    AudioInputStream audioInputStream =  AudioSystem.getAudioInputStream(new File("audio_files\\inject.aiff").getAbsoluteFile()); 
          
        // create clip reference 
        Clip clip = AudioSystem.getClip(); 
          
        // open audioInputStream to the clip 
        clip.open(audioInputStream); 
        clip.start();
          
	}

	private void updateUILabelAsynchronous(String newValue) {
		Platform.runLater( () -> { timer.setText(newValue); } );
	}
		
    @FXML void stop(MouseEvent event) {
    	this.running.set(false);
    	updateUILabelAsynchronous("0");	
    }
	
    @FXML void openPreferences(MouseEvent event) {
    	loadCenterPane("Preferences.fxml");
    }

    @FXML void openProtoss(MouseEvent event) {
    	loadCenterPane("Protoss.fxml");
    }

    @FXML void openTerran(MouseEvent event) {
    	loadCenterPane("Terran.fxml");

    }

    @FXML void openZerg(MouseEvent event) {
    	loadCenterPane("Zerg.fxml");
    }    
    
    private void loadCenterPane(String name) {
       	try {
            //FXMLLoader loader = new FXMLLoader(getClass().getResource(name));    		
            FXMLLoader loader = FXMLLoader.load(getClass().getClassLoader().getResource(name));	
            Parent root = loader.load();            
            
            centerPane = (Pane)loader.load();
            //centerPane = (Pane)FXMLLoader.load(getClass().getClassLoader().getResource(name));
    		borderpane.setRight(centerPane);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
}
