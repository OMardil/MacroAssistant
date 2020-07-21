package application;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

public class ZergController {

	@FXML TextField txtInjects;
	@FXML TextField txtCreepTumors;
	@FXML TextField txtOverlords;
	@FXML Button btnInjects;
	@FXML Button btnCreepTumors;
	@FXML Button btnOverlords;
	
	public ZergController(){
		System.out.println("Hola");
	}

	@FXML public void popupFileDirectory(MouseEvent event) {
		
		Button btn = (Button)event.getSource();
		String id = btn.getId();
		
		JFileChooser fc = new JFileChooser();
		FileFilter mp3Filter = new FileNameExtensionFilter("MP3 File","mp3");
		FileFilter m4aFilter = new FileNameExtensionFilter("M4A File","m4a");
		fc.setFileFilter(mp3Filter);
		fc.addChoosableFileFilter(m4aFilter);
		int returnVal = fc.showOpenDialog(null);
		
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			String popupResult = fc.getSelectedFile().toString();
			
			switch(id) {
				case "btnInjects":
					txtInjects.setText(popupResult);
					break;
				case "btnCreepTumors":
					txtCreepTumors.setText(popupResult);
					break;
				case "btnOverlords":
					txtOverlords.setText(popupResult);
					break;
			}
		}	
	}
	
	public long[] getTimersList() {
		long[] list = new long[3];
		list[0] = Long.parseLong(txtInjects.getText());
		list[1] = Long.parseLong(txtCreepTumors.getText());
		list[2] = Long.parseLong(txtOverlords.getText());
		return list;
	}

}
