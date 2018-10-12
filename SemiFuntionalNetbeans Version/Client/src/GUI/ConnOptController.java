package GUI;

//Networking Imports
import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.OutputStream;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ConnOptController {

	//Networking variables
        private String host = "127.0.0.1";
        private int port = 65356;
        private InputStream input;
        private BufferedReader reader;
        private OutputStream output;
        private PrintWriter writer;
	
	@FXML private TextField ipField1;
	@FXML private TextField ipField2;
	@FXML private TextField ipField3;
	@FXML private TextField ipField4;
	@FXML private TextField portField;
	@FXML private Button saveConfigButton;
	@FXML private Button backButton;
	@FXML private Label errorIP;
	@FXML private Label errorPort;
	
	private	String[] ipStr = {"", "", "", ""};
	private String portStr = "";
	
	public void initialize() {
		errorIP.setOpacity(0);
		errorPort.setOpacity(0);
	}
	
	 public void setNetworkStuff(){
        
        }
	
	public void setIP_TxtField(String[] ipStr) {
		this.ipStr = ipStr;
		ipField1.setText(ipStr[0]);
		ipField2.setText(ipStr[1]);
		ipField3.setText(ipStr[2]);
		ipField4.setText(ipStr[3]);
	}
	
	public void setPort_TxtField(String portStr) {
		this.portStr = portStr;
		portField.setText(portStr);
	}
	
	private String[] getServerIP() throws Exception {
		String field1Str = ipField1.getText();
		String field2Str = ipField2.getText();
		String field3Str = ipField3.getText();
		String field4Str = ipField4.getText();
		
		if(isIP_InRightFormat(field1Str, field2Str, field3Str, field4Str)) {
			errorIP.setOpacity(0);			
			return new String[] {field1Str, field2Str, field3Str, field4Str};
		}else {
			errorIP.setOpacity(1);
			return null;
		}
	}
	
	private String getServerPort()throws Exception{
		String portStr = portField.getText();
		
		if(isPort_InRightFormat(portStr)) {
			errorPort.setOpacity(0);			
			return portStr;
		}else {
			errorPort.setOpacity(1);
			return null;
		}
	}
	
	private boolean isIP_InRightFormat(String field1Str, String field2Str, String field3Str, String field4Str) throws Exception {		
		//Revision para averiguar si son enteros positivos y no vacio
		if(!strIsInteger(field1Str)) {
			return false;
		}else if(!strIsInteger(field2Str)) {
			return false;			
		}else if(!strIsInteger(field3Str)) {
			return false;			
		}else if(!strIsInteger(field4Str)) {
			return false;			
		}
		
		int field1Int = Integer.parseInt(field1Str);
        int field2Int = Integer.parseInt(field2Str);
        int field3Int = Integer.parseInt(field3Str);
        int field4Int = Integer.parseInt(field4Str);
        
        //Revision para averiguar si su valor es correcto
        if(field1Int < 0 || field1Int > 255) {
			return false;
		}else if(field2Int < 0 || field2Int > 255) {
			return false;			
		}else if(field3Int < 0 || field3Int > 255) {
			return false;			
		}else if(field4Int < 0 || field4Int > 255) {
			return false;			
		}
		
		return true;
	}
	
	private boolean isPort_InRightFormat(String portStr) {
		if(!strIsInteger(portStr)) {
			return false;
		}
		
		int portNum = Integer.parseInt(portStr);
		if(portNum < 49152 || portNum > 65535) {
			return false;
		}
		
		return true;
		
	}
	
	//Metodo para determinar si un string contiene un numero entero positivo ó 0
	private boolean strIsInteger(String numStr) {
		if(numStr.equals("")) {
			return false;
		}
		for(int i = 0; i < numStr.length(); i++) {
	        if(Character.digit(numStr.charAt(i), 10) < 0) {
	        	return false;
	        }
	    }
	    return true;
	}
	
	public void saveAndGetConnConfig(ActionEvent event) throws Exception {
		String[] ipStrTemp = getServerIP();
		String portStrTemp = getServerPort();
		
		if (ipStrTemp != null && portStrTemp != null) {	
			ipStr = ipStrTemp;
			portStr = portStrTemp;		
			System.out.println(getSocketsInfo());
			
			Stage stage = (Stage) saveConfigButton.getScene().getWindow();
			stage.hide();
			
			loadMainMenuScreen();
		}
	}
	
	public void goBackToMainMenuScreen(ActionEvent event){
		try {
			Stage stage = (Stage) backButton.getScene().getWindow();
		    stage.hide();
			
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
			Logger.getLogger(ConnOptController.class.getName()).log(Level.SEVERE,  null,  ex);
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
	
	public String getSocketsInfo() {
		return ipStr[0] + "." + ipStr[1] + "." + ipStr[2] + "." +ipStr[3] + " @ " + portStr;
	}	

}
