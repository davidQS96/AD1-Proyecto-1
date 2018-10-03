package GUI;

import client.DotsClient;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Arrays;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;

public class GameController {
    //Networking variables
    private String host = "127.0.0.1";
    private int port = 65356;
    private InputStream input;
    private BufferedReader reader;
    private OutputStream output;
    private PrintWriter writer;
    private char player = ' ';

    //Atributos
	@FXML
	private AnchorPane gameArea; //Pane en donde se lleva a cabo el juego
	private int rowNum = 6; //Numero de filas de puntos
	private int colNum = 7; //Numero de columnas de puntos
	private double[] gameAreaSize = {784, 441}; //Dimensiones del area de juego
	private double[] gridAreaSize = new double[2]; //Dimensiones del grid dentro del gameArea
	private double[] padding = {0, 0}; //X, Y; distancia entre extremo del área de juego y el del área del grid
	private double[] distDots = new double[2]; //Distancia ortogonal entre dots
	private double dotRadius = 4; //Radio de dots
	private double[] allowedArea = {dotRadius + 75, dotRadius + 75}; //{lAllow, hAllow}; área en la que un clic cuenta como presionar el dot
	private boolean isDrawingLine = false; //Condición de estar dibujando línea
	private double[] prevPos = new double[2]; //Coordenada anterior de una linea (punto inicial)
	private double[] newPos = new double[2]; //Coordenada siguiente de una linea (punto final)
	private int[] prevIndex = new int[2]; //Índice de matriz anterior de una linea (punto inicial)
	private int[] newIndex = new int[2]; //Índice de matriz siguiente de una linea (punto final)
	    
    public void setNetworkStuff(){
        
    }
	
	public void initialize() throws IOException { //Metodo que se llama cuando se abre la ventana de juego
            
            String command = askServer("UPDATE");
            System.out.println(player);
            
            gridAreaSize[0] = gameAreaSize[0] - 2 * padding[0];
    		gridAreaSize[1] = gameAreaSize[1] - 2 * padding[1];
    		
    		distDots[0] = gridAreaSize[0]/(colNum - 1);
    		distDots[1] = gridAreaSize[1]/(rowNum - 1);
		
		for(int row = 0; row < rowNum; row++ ) {
			
			for(int column = 0; column < colNum; column++ ) {
				
				//Parte para crear dots
				int radius = 4;
				double[] dotPos = getGameAreaCoordinates(new int[] {column, row});
				Paint fillColor = Color.BLACK;
				
				Circle dot_i = new Circle(dotPos[0], dotPos[1], radius, fillColor);
				gameArea.getChildren().add(dot_i);
				
			}
		}		
	}

	//Método que dibuja una línea del jugador, siempre que esta sea válida
		@FXML
	    private void beginLine(MouseEvent event) throws Exception{
			double posX = event.getX();
			double posY = event.getY();
			
			//Esta condicion permite detectar si se hizo clic dentro de una zona permitida alrededor de un punto para comenzar una linea.
			if((posX + distDots[0] / 2) % distDots[0] > (distDots[0] - allowedArea[0])/2  && (posX + distDots[0] / 2) % distDots[0] < (distDots[0] + allowedArea[0])/2) {
				if((posY + distDots[1] / 2) % distDots[1] > (distDots[1] - allowedArea[1])/2  && (posY + distDots[1] / 2) % distDots[1] < (distDots[1] + allowedArea[1])/2) {
					
//					double a = realDotSearch(colNum, posX, 0);
//					double b = realDotSearch(rowNum, posY, 1);
//					int[] c = getDotMatrixIndex(new double[] {a,b});
//					
//					System.out.print("Mouse: " + posX + ", " + posY + " -> ");
//					System.out.print("(" + c[0] + ", " + c[1] + ") -> ");
//					System.out.println("x: " + a + ", y: " + b);
					
					 if(!isDrawingLine) {
						 isDrawingLine = true;
						 
						 prevPos[0] = realDotSearch(colNum, posX, 0);
						 prevPos[1] = realDotSearch(rowNum, posY, 1);
						 prevIndex = getDotMatrixIndex(prevPos);
						 
					 }else {
						 isDrawingLine = false;
						 
						 newPos[0] = realDotSearch(colNum, posX, 0);
						 newPos[1] = realDotSearch(rowNum, posY, 1);	
						 newIndex = getDotMatrixIndex(newPos);
						 
						 if(!Arrays.equals(newPos, prevPos)) {
							 Line tempLine = new Line(prevPos[0], prevPos[1], newPos[0], newPos[1]);
							 System.out.println("Inicial: x=" + prevPos[0] + " (" + prevIndex[0] + "), y=" + prevPos[1] + " (" + prevIndex[1] + ")");
							 System.out.println("Final: x=" + newPos[0] + " (" + newIndex[0] + "), y=" + newPos[1] + " (" + newIndex[1] + ")");
							 System.out.println("");
							 
							 tempLine.setFill(Color.RED);
							 tempLine.setStroke(Color.RED);
							 gameArea.getChildren().add(tempLine);
							 
							 //drawPolygon(new int[][] {{1,1},{1,2},{2,2}});	
							 //drawLine(new int[][] {{1,4},{4,4}});
							 
						 }
					 }
				}
			}
	    }
	
//	public void receiveData(String command) {
//		if()
//	}
	
		//Método que retorna las coordenadas de un punto dentro del área de juego basándose en sus índices de matriz
		private double[] getGameAreaCoordinates(int[] dotIndex) {
			if (dotIndex.length == 2) {
				int xIndex = dotIndex[0];
				int yIndex = dotIndex[1];
				double[] dotCoordinates = new double[2];
				
				dotCoordinates[0] = padding[0] + distDots[0] * xIndex;
				dotCoordinates[1] = padding[1] + distDots[1] * yIndex;
				
				return dotCoordinates;
			}else {
				return null;
			}
			
		}
		
		//Método que retorna los índices de matriz de un punto basándose en sus coordenadas dentro del área de juego
		public int[] getDotMatrixIndex(double[] dotGameAreaCoordinates) {
			if (dotGameAreaCoordinates.length == 2) {
				double xCoord = dotGameAreaCoordinates[0];
				double yCoord = dotGameAreaCoordinates[1];
				int[] dotIndex = new int[2];
				
				dotIndex[0] = (int) Math.round((xCoord - padding[0]) / distDots[0]);
				dotIndex[1] = (int) Math.round((yCoord - padding[1]) / distDots[1]);
							
				return dotIndex;
			}else {
				return null;
			}	
					
		}
	
		//Método que dibuja una línea con color según el jugador
		public void drawLine(int[] initEndpoint, int[] finalEndpoint, boolean isPlayer) {
			Line connectionLine = new Line(initEndpoint[0], initEndpoint[1], finalEndpoint[0], finalEndpoint[1]);
			connectionLine.setStrokeWidth(1);
			
			if(isPlayer) {
				connectionLine.setFill(Color.RED);
				connectionLine.setStroke(Color.RED);
				
			}else {
				connectionLine.setFill(Color.BLUE);
				connectionLine.setStroke(Color.BLUE);
			}
					
			gameArea.getChildren().add(connectionLine);
		}
	
		//Método que dibuja un polígono completado con color según el que lo haya completado
		public void drawPolygon(int[][] vertexArray, boolean isPlayer) throws Exception {
			Double[] polygonInput = new Double[2 * vertexArray.length];
			for(int i = 0; i < vertexArray.length; i++ ) {
				double[] vertexCoordinates = getGameAreaCoordinates(vertexArray[i]);			
				polygonInput[2 * i] =  vertexCoordinates[0];
				polygonInput[2 * i + 1] = vertexCoordinates[1];
			}
					
			Polygon closedShape = new Polygon();
			closedShape.getPoints().addAll(polygonInput);
			
			if(isPlayer) {
				closedShape.setFill(Color.RED);
				
			}else {
				closedShape.setFill(Color.BLUE);
			}
			
			closedShape.setStrokeWidth(2);
			closedShape.setStroke(Color.BLACK);	
					
			gameArea.getChildren().add(closedShape);
			
		}
	
		//Método que encuentra las coordenadas reales del punto más cercano al mouse basándose en la posición del mouse
		private double realDotSearch(int numDots, double mousePos, int axisNum) {
			//Se asegura que ninguna medida en área de juego resulte mayor a esta distancia mínima.
			double minDist = gameAreaSize[axisNum] + allowedArea[axisNum] / 2;
			
			for(int dot_i = 0; dot_i <= numDots; dot_i++ ) {			
				double posDot = padding[axisNum] + distDots[axisNum] * dot_i;
				double mouseDist = Math.abs(posDot - mousePos);
				
				if (dot_i != numDots){
					//Se revisa un área rectangular alrededor del punto
					if(mouseDist < minDist) { 
						minDist = mouseDist;				
						
					}else {
						//System.out.println(posDot - distDots[axisNum]);
						return posDot - distDots[axisNum];
						
					}
					
				}else {
					return posDot - distDots[axisNum];
					
				}			
			}
			
			return -1;		
		}
	}

    private String askServer(String text) throws IOException {
        try(Socket socket = new Socket(host, port)){
                System.out.println(socket.isClosed());
                System.out.println("Logro asignar el socket");
                System.out.println("Entro a la funcion");
            
            System.out.println(socket.isClosed());
                
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
            
            System.out.println("Creo el output");
            
            String command;
 
            writer.println(text);
            
            System.out.println("Envio el mensaje");
            

            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            command = reader.readLine();

            if(command.startsWith("UPDATE")){
                player = command.charAt(7);
                colNum = Integer.parseInt(String.valueOf(command.charAt(9)));
                rowNum = Integer.parseInt(String.valueOf(command.charAt(11)));
                output.close();
                input.close();
                writer.close();
                reader.close();
                socket.close();
                return command;
            }else if(command.startsWith("DRAW")){
                command = command.substring(5);
                output.close();
                input.close();
                writer.close();
                reader.close();
                socket.close();
                return command;
            }else if(command.startsWith("TURN")){
                command = command.substring(5);
                output.close();
                input.close();
                writer.close();
                reader.close();
                socket.close();
                return command;
            }else if(command.startsWith("NUMBER")){
                output.close();
                input.close();
                writer.close();
                reader.close();
                this.player = command.charAt(7);
            }
            }
            
        
            
            return null;
        
    }
	
	
	
	
}








