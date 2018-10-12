package GUI;

import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class WaitingController {
	
	@FXML private Button backButton;
	@FXML private Label waitingLabel;
	@FXML private Label nextLabel;
	@FXML private Button tempButton;
	
	private	String[] ipStr = {"", "", "", ""};
	private String portStr = "";
	
	public void initialize() {
		nextLabel.setOpacity(0);
		tempButton.setOpacity(0.05);
	}
	
	public void setIP(String[] ipStr) {
		this.ipStr = ipStr;
	}
	
	public void setPort(String portStr) {
		this.portStr = portStr;
	}
	
	public void openGameScreen(){
		try {
			//((Node)event.getSource()).getScene().getWindow().hide();
			
			waitingLabel.setText("Oponente conectado.");
			nextLabel.setOpacity(1);
			
			loadGameScreen();
						
		} catch(Exception e)  {
			e.printStackTrace();
		}
	}
	
	public void Temp_openGameFromTemp(ActionEvent event) {
		openGameScreen();
	}
	
	public void goBackToMainMenuScreen(ActionEvent event){
		try {
			Stage stage = (Stage) backButton.getScene().getWindow();
		    stage.close();		    
			
			loadMainMenuScreen();
			
		} catch(Exception e)  {
			e.printStackTrace();
		}
	}
	
	private void loadMainMenuScreen() {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/GUI/MainMenuScreen.fxml"));
		try {
			loader.load();
		}catch(IOException ex) {
			Logger.getLogger(WaitingController.class.getName()).log(Level.SEVERE,  null,  ex);
		}
		
		MainMenuController mainMenu = loader.getController();
		mainMenu.setNumIP_Label(ipStr);
		mainMenu.setNumPort_Label(portStr);			
		
		Stage mainScene = new Stage();		
		
		Parent root = loader.getRoot();
		
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					
		mainScene.setScene(scene);
		mainScene.show();
	}
	
	private void loadGameScreen() {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/GUI/GameScreen.fxml"));
		try {
			loader.load();
		}catch(IOException ex) {
			Logger.getLogger(WaitingController.class.getName()).log(Level.SEVERE,  null,  ex);
		}
		
		GameController game = loader.getController();
		game.setIP(ipStr);
		game.setPort(portStr);			
		
		Stage gameScene = new Stage();		
		
		Parent root = loader.getRoot();
		
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					
		gameScene.setScene(scene);
		gameScene.show();
	}
	
}
