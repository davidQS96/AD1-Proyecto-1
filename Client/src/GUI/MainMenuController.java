package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainMenuController {
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
