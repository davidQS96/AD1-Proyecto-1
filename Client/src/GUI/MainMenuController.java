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

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class MainMenuController {
	@FXML private Button playButton;	
	@FXML private Button connOptButton;
	@FXML private Button exitButton;
	@FXML private Label numIPLabel;
	@FXML private Label numPortLabel;
	@FXML private Label errorLabel;	
	
	//Networking variables
        private String host = "127.0.0.1";
        private int port = 65356;

	private Socket socket;
    private InputStream input;
    private BufferedReader reader;
    private OutputStream output;
    private PrintWriter writer;

	private	String[] ipStr = {"", "", "", ""};
	private String portStr = "";
	
	public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
        
    public void setNetworkStuff(){
            
    }
	
	public void initialize() {
		numIPLabel.setOpacity(0);
		numIPLabel.setText("");
		numPortLabel.setOpacity(0);
		numPortLabel.setText("");
		errorLabel.setOpacity(0);
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
	
	public void openGameScreen(ActionEvent event){
		try {
			((Node)event.getSource()).getScene().getWindow().hide();
			
			loadWaitingScreen();
						
		} catch(Exception e)  {
			e.printStackTrace();
		}
	}
	
	public void openConnOptScreen(ActionEvent event){
		try {
			((Node)event.getSource()).getScene().getWindow().hide();
			
			loadConnOptScreen(); 
		} catch(Exception e) {
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
	
	private void loadConnOptScreen() {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/application/ConnOptScreen.fxml"));
		try {
			loader.load();
		}catch(IOException ex) {
			Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE,  null,  ex);
		}
		
		ConnOptController connOpt = loader.getController();
		connOpt.setIP_TxtField(ipStr);
		connOpt.setPort_TxtField(portStr);			
		
		Stage connOptScene = new Stage();		
		
		Parent root = loader.getRoot();
		
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					
		connOptScene.setScene(scene);
		connOptScene.show();
	}
	
	public void exitApplication(ActionEvent event) {
		Stage stage = (Stage) exitButton.getScene().getWindow();
	    stage.close();
		
	}

}
