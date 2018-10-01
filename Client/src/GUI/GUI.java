package GUI;
	
import client.DotsClient;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class GUI extends Application {
        private DotsClient client;
        private InputStream input;
        private BufferedReader br;
        private OutputStream output;
        private PrintWriter pr;
        
        private MainMenuController mainMenuController;
        private ConnOptController connOptController;
        private GameController gameController;

    public void setMainMenuController(MainMenuController mainMenuController) {
        this.mainMenuController = mainMenuController;
    }

    public void setConnOptController(ConnOptController connOptController) {
        this.connOptController = connOptController;
    }

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    public DotsClient getClient() {
        return client;
    }

    public void setClient(DotsClient client) {
        this.client = client;
    }
	@Override
	public void start(Stage primaryStage) throws IOException {
            mainMenuController = new MainMenuController();
            connOptController = new ConnOptController();
            gameController = new GameController();
            try {
                    Parent root = FXMLLoader.load(getClass().getResource("/GUI/MainMenuScreen.fxml"));
                    Scene scene = new Scene(root);
                    scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

                    primaryStage.setScene(scene);
                    primaryStage.show();
            } catch(Exception e) {
                    e.printStackTrace();
            }
	}
	
	public static void main() {
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
		
		launch();		
		
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
