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
	
	@FXML
	private AnchorPane gameArea; //Pane en donde se lleva a cabo el juego
	private Integer rowNum; //Numero de filas de puntos
	private Integer colNum; //Numero de columnas de puntos
	private Integer[] gameAreaSize = {784, 441}; //Dimensiones del area de juego
	private Integer[] padding = {0, 0}; //X, Y
	private Integer[] distDots = new Integer[2];
	private Integer dotRadius = 4;
	private Integer lAllow = dotRadius + 20;
	private Integer hAllow =  dotRadius + 20;
	private boolean isDrawingLine = false;
	private double[] prevPos = new double[2];
        
        public void setNetworkStuff(){
            
        }
	
	public void initialize() throws IOException { //Metodo que se llama cuando se abre la ventana de juego
            
            String command = askServer("UPDATE");
            System.out.println(player);
            
            
		distDots[0] = Math.round((gameAreaSize[0] - 2 * padding[0])/(colNum - 1));
		distDots[1] = Math.round((gameAreaSize[1] - 2 * padding[1])/(rowNum - 1));
		
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

	@FXML
    private void beginLine(MouseEvent event) throws Exception {
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
					 if(true) {
						 tempLine.setStyle("-fx-stroke: red;");
					 }else {
						 tempLine.setStyle("-fx-stroke: blue;");
					 }
					 gameArea.getChildren().add(tempLine);
					 
					 
					 
					 
				 }
			}
		}
    }
	
//	public void receiveData(String command) {
//		if()
//	}
	
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
	
	public void drawLine(int[][] vertexArray) {
            
		Double[] lineInput = new Double[4];
		for(int i = 0; i < 2; i++ ) {
			double[] vertexCoordinates = getGameAreaCoordinates(vertexArray[i]);			
			lineInput[2 * i] =  vertexCoordinates[0];
			lineInput[2 * i + 1] = vertexCoordinates[1];
		}
				
		Line connetionLine = new Line(lineInput[0], lineInput[1], lineInput[2], lineInput[3]);
		
		connetionLine.setFill(Color.RED);
		connetionLine.setStroke(Color.RED);
		
		System.out.println("Esta a punto de dibujar un poligono");
		
		gameArea.getChildren().add(connetionLine);
	}
	
	public void drawPolygon(int[][] vertexArray) throws Exception {
		Double[] polygonInput = new Double[2 * vertexArray.length];
		for(int i = 0; i < vertexArray.length; i++ ) {
			double[] vertexCoordinates = getGameAreaCoordinates(vertexArray[i]);			
			polygonInput[2 * i] =  vertexCoordinates[0];
			polygonInput[2 * i + 1] = vertexCoordinates[1];
		}
				
		Polygon closedShape = new Polygon();
		closedShape.getPoints().addAll(polygonInput);
		
		closedShape.setFill(Color.RED);
		closedShape.setStrokeWidth(3);
		closedShape.setStroke(Color.BLACK);	
		
		System.out.println("Esta a punto de dibujar un poligono");
		
		gameArea.getChildren().add(closedShape);
		
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








