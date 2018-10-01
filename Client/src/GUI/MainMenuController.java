package GUI;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainMenuController {
    //Networking variables
        private String host = "127.0.0.1";
        private int port = 65356;

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
        private Socket socket;
        private InputStream input;
        private BufferedReader reader;
        private OutputStream output;
        private PrintWriter writer;
    
	@FXML
	private Button playButton;
	
	@FXML
	private Button connOptButton;
	
	public void openGameScreen(ActionEvent event){
		try {
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("/GUI/GameScreen.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
						
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e)  {
			e.printStackTrace();
		}
	}
        
        
        public void setNetworkStuff(){
            
        }
	
	public void openConnOptScreen(ActionEvent event){
		try {
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("/GUI/ConnOptScreen.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
						
			primaryStage.setScene(scene);
			primaryStage.show();  
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
