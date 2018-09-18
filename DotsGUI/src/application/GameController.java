package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class GameController {
	
	@FXML
	private AnchorPane gameArea; //Pane en donde se lleva a cabo el juego
	private Integer rowNum = 5; //Numero de filas de puntos
	private Integer colNum = 8; //Numero de columnas de puntos
	private Integer[] gameAreaSize = {784, 441}; //Dimensiones del area de juego
	private Integer[] distDots = new Integer[2];
	private Integer dotRadius = 4;
	private Integer lAllow = dotRadius + 8;
	private Integer hAllow =  dotRadius + 8;
	private boolean isDrawingLine = false;
	
	private Integer[] posInitLine = new Integer[2];
	
	public void initialize() { //Metodo que se llama cuando se abre la ventana de juego
		distDots[0] = Math.round(gameAreaSize[0]/(colNum - 1));
		distDots[1] = Math.round(gameAreaSize[1]/(rowNum - 1));
		
		for(int row = 0; row < rowNum; row++ ) {
			
			for(int column = 0; column < colNum; column++ ) {
				
				//Parte para crear dots
				double radius = 4;
				double posX = distDots[0] * column;
				double posY = distDots[1] * row;
				Paint fillColor = Color.BLACK;
				
				Circle dot_i = new Circle(posX, posY, radius, fillColor);
				//Button button_i = new Button();
				//button_i.prefHeight(radius + 4);
				//button_i.prefHeight(radius + 4);
				gameArea.getChildren().add(dot_i);
				
			}
		}		
	}

	@FXML
    private void beginLine(MouseEvent event){
		double posX = event.getX();
		double posY = event.getY();
		
		//Est condicion permite detectar si se hizo clic dentro de una zona permitida alrededor de un punto para comenzar una linea.
		if((posX + distDots[0] / 2) % distDots[0] > (distDots[0] - lAllow)/2  && (posX + distDots[0] / 2) % distDots[0] < (distDots[0] + lAllow)/2) {
			if((posY + distDots[1] / 2) % distDots[1] > (distDots[1] - hAllow)/2  && (posY + distDots[1] / 2) % distDots[1] < (distDots[1] + hAllow)/2) {
				 if(!isDrawingLine) {
					 isDrawingLine = true;
					 System.out.println("Hola");
					 						 
					 
				 }else {
					 isDrawingLine = false;
					 //Line tempLine = new Line(double startX, double startY, double endX, double endY);
				 }
			}
		}
		
    }
	
	

	
	
}
