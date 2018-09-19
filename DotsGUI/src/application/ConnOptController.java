package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ConnOptController {
	
	@FXML private TextField ipField1;
	@FXML private TextField ipField2;
	@FXML private TextField ipField3;
	@FXML private TextField ipField4;
	@FXML private TextField portField;
	
	public String getServerIP() {		
		return ipField1.getText() + "." + ipField2.getText() + "." + ipField3.getText() + "." + ipField4.getText();		
	}
	
	public String saveAndGetConnConfig(ActionEvent event) {
		String ipStr = getServerIP();
		String portStr = portField.getText();
		
		System.out.println(ipStr + " @ " + portStr);
		
		return ipStr + " @ " + portStr;
	}

}
