package application;
	
import java.util.Scanner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/application/MainMenuScreen.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
						
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
//		Scanner reader = new Scanner(System.in);
//		int a;
//		
//		boolean sigue = true;
//		while(sigue) {
//			a = reader.nextInt();
//			System.out.println(a);
//			
//			if(a < 0) {
//				sigue = false;
//			}
//		}
//		sigue = true;
		
		launch(args);		
		
//		while(sigue) {
//			a = reader.nextInt();
//			System.out.println(a);
//			
//			if(a < 0) {
//				sigue = false;
//			}
//		}
//		
//		reader.close();
		
	}
}
