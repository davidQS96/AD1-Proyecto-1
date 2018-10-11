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

public class WinnerController {
	
	private	String[] ipStr = {"", "", "", ""};
	private String portStr = "";
	private boolean playerWon = false;
	
	@FXML private Button playAgainButton;
	@FXML private Button playWithOtherButton;
	@FXML private Button goToMainButton;
	@FXML private Label stateLabel;
	@FXML private Label numIPLabel;
	@FXML private Label numPortLabel;
	@FXML private Label waitingPlayerLabel;


	public void setIP(String[] ipStr) {
		this.ipStr = ipStr;
	}
	
	public void setPort(String portStr) {
		this.portStr = portStr;
	}
	
	public void setPlayerState(boolean playerWon) {
		this.playerWon = playerWon;
		if(playerWon) {
			stateLabel.setText("Ganaste!");
		}else {
			stateLabel.setText("Perdiste :c");
		}
	}
	
	public void initialize() {		
		setNumIP_Label(ipStr);
		setNumPort_Label(portStr);
		
		waitingPlayerLabel.setOpacity(0);
	}
	
	public void setNumIP_Label(String[] ipStr) {
		this.ipStr = ipStr;
		numIPLabel.setText(ipStr[0] + "." + ipStr[1] + "." + ipStr[2] + "." +ipStr[3]);
		if(!Arrays.equals(ipStr, new String[] {"", "", "", ""})) {
			numIPLabel.setOpacity(1);
		}else {
			numIPLabel.setOpacity(0);
		}
	}
	
	public void setNumPort_Label(String portStr) {
		this.portStr = portStr;
		numPortLabel.setText(portStr);
		if(!portStr.equals("")) {
			numPortLabel.setOpacity(1);
		}else {
			numPortLabel.setOpacity(0);
		}
	}
	
	public void waitForOtherPlayer(ActionEvent event) {
		
	}
	
	public void playWithAnotherOpponent(ActionEvent event) {
		try {
			((Node)event.getSource()).getScene().getWindow().hide();
			
			loadWaitingScreen();
						
		} catch(Exception e)  {
			e.printStackTrace();
		}
	}
	
	private void loadWaitingScreen() {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/application/WaitingScreen.fxml"));
		try {
			loader.load();
		}catch(IOException ex) {
			Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE,  null,  ex);
		}
		
		WaitingController waiting = loader.getController();
		waiting.setIP(ipStr);
		waiting.setPort(portStr);			
		
		Stage waitingScene = new Stage();		
		
		Parent root = loader.getRoot();
		
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					
		waitingScene.setScene(scene);
		waitingScene.show();
	}
	
	public void goBackToMainMenuScreen(ActionEvent event){
		try {
			Stage stage = (Stage) goToMainButton.getScene().getWindow();
		    stage.close();		    
			
			loadMainMenuScreen();
			
		} catch(Exception e)  {
			e.printStackTrace();
		}
	}
	
	private void loadMainMenuScreen() {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/application/MainMenuScreen.fxml"));
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
	
}
