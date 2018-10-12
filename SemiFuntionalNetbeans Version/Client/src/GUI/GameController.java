package GUI;

import client.DotsClient;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
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
import java.util.Timer;

import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

public class GameController {

//Networking variables
	private String host = "127.0.0.1";
	private int port = 65356;
	private Socket socket;
	private InputStream input;
	private BufferedReader reader;
	private OutputStream output;
	private PrintWriter writer;
	private char player = ' ';
	
//Atributos	
	private	String[] ipStr = {"", "", "", ""};
	private String portStr = "";
	
	//Atributos de nodes de JavaFX
	@FXML private AnchorPane gameArea; //Pane en donde se lleva a cabo el juego
	@FXML private Label scorePlayer; //Label que contiene score del jugador
	@FXML private Label scoreOpponent; //Label que contiene score del oponente
	@FXML private Label timeLabel; //Label que contiene el tiempo restante del juego
	@FXML private Button tempButton; //Label que contiene el tiempo restante del juego	
	
	//Atributos de geometria
	private int rowNum = 5; //Numero de filas de puntos
	private int colNum = 5; //Numero de columnas de puntos
	private double[] gameAreaSize = {784, 441}; //Dimensiones del area de juego
	private double[] distDots = new double[2]; //Distancia ortogonal entre dots
	private double[] gridAreaSize = new double[2]; //Dimensiones del grid dentro del gameArea
	private double[] padding = new double[2]; //X, Y; distancia entre extremo del �rea de juego y el del �rea del grid
	private double[] allowedArea = new double[2]; //{lAllow, hAllow}; �rea rectangular en la que un clic cuenta como presionar el dot
	private double dotRadius = 4; //Radio de dots
	
	//Atributos de posicion de lineas
	private double[] prevPos = new double[2]; //Coordenada anterior de una linea (punto inicial)
	private double[] newPos = new double[2]; //Coordenada siguiente de una linea (punto final)
	private int[] prevIndex = new int[2]; //�ndice de matriz anterior de una linea (punto inicial)
	private int[] newIndex = new int[2]; //�ndice de matriz siguiente de una linea (punto final)
	
	//Atributos de condicion
	private boolean isDrawingLine = false; //Condici�n de estar dibujando l�nea

    public boolean isIsDrawingLine() {
        return isDrawingLine;
    }
	
	//Atributos de tiempo
	int timeLeft = 0; //Tiempo total en segundos
	
	//Timer
	private Tempo timerTask = new Tempo(this, 0);

    public Tempo getTimerTask() {
        return timerTask;
    }

//Metodos

	public void setTimer(Tempo timerTask) {
		this.timerTask = timerTask;
	}

	public void setNetworkStuff(){

	}

	//Metodo que se llama cuando se abre la ventana de juego
	public void initialize() throws IOException {
            
                socket = new Socket(host, port);
		String command = askServer("UPDATE");
		Timer timer = new Timer(true);
		timer.scheduleAtFixedRate(timerTask, 0, 1000);
                
		tempButton.setOpacity(0.05);
		System.out.println(portStr);
		
		//Como los puntos tienen distancia constante, se considera el padding total fuera del gridArea en el cual aun se puede hacer clic y cuenta como haber comenzado una l�nea.
		//Tal padding tiene la misma longitud que la distancia constante, por eso se divide entre colNum y rowNum
		distDots[0] = gameAreaSize[0] / colNum;
		distDots[1] = gameAreaSize[1] / rowNum;
		allowedArea[0] = distDots[0];
		allowedArea[1] = distDots[1];
		
		System.out.println("distDots: " + distDots[0] + ", " + distDots[1]);
		
		//Esta �rea contempla �nicamente el �rea contemplada por puntos.
		//La malla que contiene los dots como v�rtices tiene n - 1 celdas, por eso se usa colNum - 1 y rowNum - 1
		gridAreaSize[0] = (colNum - 1) * distDots[0];
		gridAreaSize[1] = (rowNum - 1) * distDots[1];

		System.out.println("gridAreaSize: " + gridAreaSize[0] + ", " + gridAreaSize[1]);
		
		//El padding total ser� entonces el �rea de juego menos el �rea del grid
		//El padding a la izquierda o derecha (arriba o abajo), ser� la mitad del padding total, para que quede centrado
		padding[0] = (gameAreaSize[0] - gridAreaSize[0]) / 2;
		padding[1] = (gameAreaSize[1] - gridAreaSize[1]) / 2;

		System.out.println("padding: " + padding[0] + ", " + padding[1]);
		
		//Parte para crear dots
		for(int row = 0; row < rowNum; row++ ) {			
			for(int column = 0; column < colNum; column++ ) {	
				double[] dotPos = getGameAreaCoordinates(new int[] {column, row});
				Paint fillColor = Color.BLACK;
				
				Circle dot_i = new Circle(dotPos[0], dotPos[1], dotRadius, fillColor);
				gameArea.getChildren().add(dot_i);
				
			}
		}
		
		//Genera el tiempo inicial que se muestra en GUI
		updateTime(timeLeft);
		
	}
	
	public void setIP(String[] ipStr) {
		this.ipStr = ipStr;
	}
	
	public void setPort(String portStr) {
		this.portStr = portStr;
	}
	
	//M�todo que dibuja una l�nea del jugador, siempre que esta sea v�lida
	@FXML
    private void beginLine(MouseEvent event) throws Exception{
		double posX = event.getX();
		double posY = event.getY();
		
		System.out.println("X: " + posX + " Y: " + posY);
		
               
		//Estas variables permiten que haya una lectura de numero continua en la area alrededor del punto.
		double displacementX = (posX - padding[0] + distDots[0] / 2) % distDots[0];
		double displacementY = (posY - padding[1] + distDots[1] / 2) % distDots[1];
		
		//Esta condicion permite detectar si se hizo clic dentro de una zona permitida alrededor de un punto para comenzar una linea.
		if(displacementX > (distDots[0] - allowedArea[0]) / 2  && displacementX < (distDots[0] + allowedArea[0]) / 2) {
			if((displacementY > (distDots[1] - allowedArea[1]) / 2  && displacementY < (distDots[1] + allowedArea[1]) / 2)) {
				
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
                                         
                                        String x1 = String.valueOf(prevIndex[0]);
                                        String y1 = String.valueOf(prevIndex[1]);
					String x2 = String.valueOf(newIndex[0]);
					String y2 = String.valueOf(newIndex[1]);
                                         
                                        String lineCoords = x1 + "," + y1 + "," + x2 + "," + y2;
					 
					 if(!Arrays.equals(newPos, prevPos)) {
                                             if(askServer("LINE " + player + ' ' + lineCoords).equals("TRUE")){
						 Line tempLine = new Line(prevPos[0], prevPos[1], newPos[0], newPos[1]);						 
						 tempLine.setFill(Color.RED);
						 tempLine.setStroke(Color.RED);
						 gameArea.getChildren().add(tempLine);
                                             }else{
                                                 System.out.println("No hay linea por hacer");
                                             }
						 						 
					 }
				 }
			}
		}
		
		timeLeft -= 1;
		updateTime(timeLeft);
                
    }

	//M�todo que retorna las coordenadas de un punto dentro del �rea de juego bas�ndose en sus �ndices de matriz
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
	
	//M�todo que retorna los �ndices de matriz de un punto bas�ndose en sus coordenadas dentro del �rea de juego
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
	
	//M�todo que dibuja una l�nea con color seg�n el jugador
	public void drawLine(int[] initEndpoint, int[] finalEndpoint, boolean isPlayer) {
		double initCoordinate[] = getGameAreaCoordinates(initEndpoint);
		double finalCoordinate[] = getGameAreaCoordinates(finalEndpoint);				
				
		Line connectionLine = new Line(initCoordinate[0], initCoordinate[1], finalCoordinate[0], finalCoordinate[1]);
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
	
	//M�todo que dibuja un pol�gono completado con color seg�n el que lo haya completado
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
	
	//M�todo que encuentra las coordenadas reales del punto m�s cercano al mouse bas�ndose en la posici�n del mouse
	private double realDotSearch(int numDots, double mousePos, int axisNum) {
		//Se asegura que ninguna medida en �rea de juego resulte mayor a esta distancia m�nima.
		double minDist = gameAreaSize[axisNum] + allowedArea[axisNum] / 2;
		
		for(int dot_i = 0; dot_i <= numDots; dot_i++ ) {			
			double posDot = padding[axisNum] + distDots[axisNum] * dot_i;
			double mouseDist = Math.abs(posDot - mousePos);
			
			if (dot_i != numDots){
				//Se revisa un �rea rectangular alrededor del punto
				if(mouseDist < minDist) { 
					minDist = mouseDist;				
					
				}else {
					return posDot - distDots[axisNum];
					
				}
				
			}else {
				return posDot - distDots[axisNum];
				
			}			
		}
		
		return -1;
	}
	
	//Metodo para actualizar score de los jugadores.
	public void updateScore(int newScore, boolean isPlayer) {
		if(isPlayer) {
			scorePlayer.setText("" + newScore);
		}else {
			scoreOpponent.setText("" + newScore);
		}
	}
	
	//Metodo para actualizar tiempo faltante del juego, antes que acabe.                 
	//totalTimeLeft in seconds
	//Tiene formato hhhhhhhhh:mm:ss
	private void updateTime(int totalTimeLeft) {
		String timeStr;
		
		if (totalTimeLeft > 0) {
			int secondsLeft = totalTimeLeft % 60;
			totalTimeLeft = (totalTimeLeft - secondsLeft) / 60;
			int minutesLeft = totalTimeLeft % 60;
			int hoursLeft = (totalTimeLeft - minutesLeft) / 60;
			timeStr = getRightTimeFormat(hoursLeft, minutesLeft, secondsLeft);
			
		}else {
			timeStr = "00:00";
			
		}
		
		timeLabel.setText(timeStr);
		
	}
	
	//Metodo para obtener formato de tiempo correcto.
	//Tiene formato hhhhhhhhh:mm:ss
	public String getRightTimeFormat(int hoursLeft, int minutesLeft, int secondsLeft) {	
		if(hoursLeft >= 0 && minutesLeft >= 0 && secondsLeft >= 0) {
			if (minutesLeft < 60 && secondsLeft < 60) {
				String timeStr = getRightTimeSpacing(minutesLeft, 2) + ":" + getRightTimeSpacing(secondsLeft, 2);
				
				if (hoursLeft != 0) {
					if(hoursLeft < 10) {
						timeStr = getRightTimeSpacing(hoursLeft, 2) + ":" + timeStr;
						
					}else {
						timeStr = "" + hoursLeft + ":" + timeStr;
						
					}
				}
				
				return timeStr;
			
			}
		}
		
		return null;
		
	}
	
	//Metodo para obtener el espaciado correcto de tiempo
	//timePortion es un numero que simboliza segundos, minutos u horas
	private String getRightTimeSpacing(int timePortion, int numDigits) {
		if(timePortion >= 0 && numDigits >= 0) {
			String timeStr = "" + timePortion;
			
			while(timeStr.length() < numDigits) {
				timeStr = "0" + timeStr;			
			}
			
			return timeStr;	
			
		}
		
		return null;
		
	}
	
	public void Temp_openWinnerFromTemp(ActionEvent event) {
		openWinnerScreen(true);
	}
	
	public void openWinnerScreen(boolean playerWon) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/GUI/WinnerScreen.fxml"));
		try {
			loader.load();
		}catch(IOException ex) {
			Logger.getLogger(GameController.class.getName()).log(Level.SEVERE,  null,  ex);
		}
		
		WinnerController winner = loader.getController();
		winner.setIP(ipStr);
		winner.setPort(portStr);
		winner.setPlayerState(playerWon);
		
		Stage winnerScene = new Stage();		
		
		Parent root = loader.getRoot();
		
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					
		winnerScene.setScene(scene);
		winnerScene.show();
	}
	
	private String askServer(String text) throws IOException {
		//try(Socket socket = new Socket(host, port)){
		System.out.println(socket.isClosed());
		System.out.println("Logro asignar el socket");
		System.out.println("Entro a la funcion");

		System.out.println(socket.isClosed());

		OutputStream output = socket.getOutputStream();
		PrintWriter writer = new PrintWriter(output, true);

		System.out.println("Creo el output");

		String command;

		writer.println(text);
		writer.flush();

		System.out.println("Envio el mensaje");


		InputStream input = socket.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(input));

		command = reader.readLine();

		if(command.startsWith("UPDATE")){
			player = command.charAt(7);
			colNum = Integer.parseInt(String.valueOf(command.charAt(9)));
			rowNum = Integer.parseInt(String.valueOf(command.charAt(11)));
                        JsonObject jobj1 = new Gson().fromJson(command.substring(13), JsonObject.class);
                        if(jobj1.get("listSides").getAsJsonObject().has("first")){
                        JsonObject jobj = jobj1.get("listSides").getAsJsonObject().get("first").getAsJsonObject();
                        boolean pastFirst = false;
                String result = "";
                while(jobj.get("next") != null){
                if(!pastFirst){
                    //jobj = jobj.get("first").getAsJsonObject();
                    JsonObject first = jobj.get("data").getAsJsonObject();
                    Integer x1 = first.get("start").getAsJsonObject().get("x").getAsInt();
                    Integer y1 = first.get("start").getAsJsonObject().get("y").getAsInt();
                    int[] start = {x1,y1};
                    Integer x2 = first.get("finish").getAsJsonObject().get("x").getAsInt();
                    Integer y2 = first.get("finish").getAsJsonObject().get("y").getAsInt();
                    int[] finish = {x2,y2};
                    Integer owner = first.get("start").getAsJsonObject().get("owner").getAsInt();
                    boolean owner_V;
                    if(owner.equals(1)){
                        owner_V = true;
                    }else{
                        owner_V = false;
                    }
                        
                    drawLine(start, finish, owner_V);
                    jobj = jobj.get("next").getAsJsonObject();
                }else{
                        JsonObject data = jobj.get("data").getAsJsonObject();
                        Integer x1 = data.get("start").getAsJsonObject().get("x").getAsInt();
                        Integer y1 = data.get("start").getAsJsonObject().get("y").getAsInt();
                        int[] start = {x1,y1};
                        Integer x2 = data.get("finish").getAsJsonObject().get("x").getAsInt();
                        Integer y2 = data.get("finish").getAsJsonObject().get("y").getAsInt();
                        int[] finish = {x2,y2};
                        Integer owner = data.get("start").getAsJsonObject().get("owner").getAsInt();
                        boolean owner_V;
                        if(owner.equals(1)){
                        owner_V = true;
                        }else{
                        owner_V = false;
                        }
                        drawLine(start, finish, owner_V);
                        
                        jobj = jobj.get("next").getAsJsonObject();
                    }
			System.out.println(command);
			return command;
		}
                }else{
                            return null;
                        }
                }
                else if(command.startsWith("DRAW")){
			command = command.substring(5);
			if(!command.equals("FALSE")){
				//socket.close();
				System.out.println(command);
				return "TRUE";
			} else {
				System.out.println(command);
				return "FALSE";
			}
		}else if(command.startsWith("TURN")){
			command = command.substring(5);
			//socket.close();
			System.out.println(command);
			return command;
		}else if(command.startsWith("NUMBER")){
			reader.close();
			System.out.println(command);
			this.player = command.charAt(7);
		}
		//}



		return null;

	}

	public void requestUPDATE() throws IOException{
                System.out.println("Voy a pedir update");
		askServer("UPDATE");
	}
	
}














