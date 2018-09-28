package GUI;

import client.DotsClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ConnOptController {
	
	@FXML private TextField ipField1;
	@FXML private TextField ipField2;
	@FXML private TextField ipField3;
	@FXML private TextField ipField4;
	@FXML private TextField portField;
	@FXML private Button saveConfigButton;
        private DotsClient client;

    public DotsClient getClient() {
        return client;
    }

    public void setClient(DotsClient client) {
        this.client = client;
    }
	
	public String getServerIP() {
		int field1 = Integer.getInteger(ipField1.getText());
        int field2 = Integer.getInteger(ipField2.getText());
        int field3 = Integer.getInteger(ipField3.getText());
        int field4 = Integer.getInteger(ipField4.getText());
        if((0 < field1 && field1 < 255) && (0 < field2 && field2 < 255) && (0 < field3 && field3 < 255) && (0 < field4 && field4 < 255)){
            client.setHostname(ipField1.getText() + "." + ipField2.getText() + "." + ipField3.getText() + "." + ipField4.getText());
            return ipField1.getText() + "." + ipField2.getText() + "." + ipField3.getText() + "." + ipField4.getText();
        }else{ return null;}
	}
	
	public String saveAndGetConnConfig(ActionEvent event) {
		String ipStr = getServerIP();
		String portStr = portField.getText();
		
		System.out.println(ipStr + " @ " + portStr);
		
		Stage stage = (Stage) saveConfigButton.getScene().getWindow();
		stage.close();
		
		return ipStr + " @ " + portStr;
	}

}
