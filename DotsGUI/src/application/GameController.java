package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class GameController {
	
	@FXML
	private AnchorPane gameArea; //Pane en donde se lleva a cabo el juego
	private Integer rowNum = 5; //Numero de filas de puntos
	private Integer colNum = 8; //Numero de columnas de puntos
	private Integer[] gameAreaSize = {784, 441}; //Dimensiones del area de juego
	private Integer[] padding = {0, 0}; //X, Y
	private Integer[] distDots = new Integer[2];
	private Integer dotRadius = 4;
	private Integer lAllow = dotRadius + 20;
	private Integer hAllow =  dotRadius + 20;
	private boolean isDrawingLine = false;
	private double[] prevPos = new double[2];
	
	public void initialize() { //Metodo que se llama cuando se abre la ventana de juego
		distDots[0] = Math.round((gameAreaSize[0] - 2 * padding[0])/(colNum - 1));
		distDots[1] = Math.round((gameAreaSize[1] - 2 * padding[1])/(rowNum - 1));
		
		for(int row = 0; row < rowNum; row++ ) {
			
			for(int column = 0; column < colNum; column++ ) {
				
				//Parte para crear dots
				double radius = 4;
				double posX = padding[0] + distDots[0] * column;
				double posY = padding[1] + distDots[1] * row;
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
		
		//Esta condicion permite detectar si se hizo clic dentro de una zona permitida alrededor de un punto para comenzar una linea.
		if((posX + distDots[0] / 2) % distDots[0] > (distDots[0] - lAllow)/2  && (posX + distDots[0] / 2) % distDots[0] < (distDots[0] + lAllow)/2) {
			if((posY + distDots[1] / 2) % distDots[1] > (distDots[1] - hAllow)/2  && (posY + distDots[1] / 2) % distDots[1] < (distDots[1] + hAllow)/2) {
				 if(!isDrawingLine) {
					 isDrawingLine = true;
					 System.out.println("Hola");
					 
					 prevPos[0] = realDotSearch(colNum, posX, 0);
					 prevPos[1] = realDotSearch(rowNum, posY, 1); 				 						 
					 
				 }else {
					 isDrawingLine = false;
					 Line tempLine = new Line(prevPos[0], prevPos[1], realDotSearch(colNum, posX, 0), realDotSearch(rowNum, posY, 1));
					 tempLine.setStyle("-fx-stroke: red;");
					 gameArea.getChildren().add(tempLine);
				 }
			}
		}
    }
	
	private double realDotSearch(Integer numDots, double mousePos, Integer axisNum) {
		double minDist = gameAreaSize[axisNum];
		
		for(int dot_i = 0; dot_i < numDots; dot_i++ ) {			
			double posDot = padding[axisNum] + distDots[axisNum] * dot_i;
			double mouseDist = Math.abs(posDot - mousePos);
			
			if(mouseDist < minDist) {
				minDist = mouseDist;				
				
			}else {
				return posDot - distDots[axisNum];	
			}
		}
		
		return -1;
	}
}
