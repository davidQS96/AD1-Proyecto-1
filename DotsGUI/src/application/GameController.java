package application;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class GameController {
	
	@FXML
	private AnchorPane gameArea; //Pane en donde se lleva a cabo el juego
	private Integer rowNum = 7; //Numero de filas de puntos
	private Integer colNum = 3; //Numero de columnas de puntos
	
	//private ArrayList<ArrayList<Integer[]>> posMatrix = new ArrayList<ArrayList<Integer[]>>() ;//Aqui va matriz con posiciones (noc si va con listas creadas o se pueden usar las de java)

	public void initialize() {
		for(int row = 0; row <= rowNum; row++ ) {
			//posMatrix.add(new ArrayList<Integer>());
			
			for(int column = 0; column <= colNum; column++ ) {
				//Integer[] temp_array = new Integer[2];
				
				double radius = 4;
				double posX = row * 100;
				double posY = 20 + column * 100;
				Paint fillColor = Color.BLACK;
				
				//posMatrix.get();
				
				Circle dot_i = new Circle(posX, posY, radius, fillColor);
				gameArea.getChildren().add(dot_i);	
				
			}
		}		
	}
	
	public void beginLine(MouseEvent event) {
		System.out.println(event.getX());
		System.out.println(event.getY());	
		
	}
	
}
