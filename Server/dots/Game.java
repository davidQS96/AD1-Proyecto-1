/*
 * Clase que contiene los datos generales del juego, además es el 
 * encoargado de llamar el resto gran cantidad de clases.
 */
public class Game {
	/*
	 * @param player1 : Es el primero en conectarse al server por medio de sockets
	 * @param player2 : Es el Segundo en conectarse al server por medio de sockets
	 * @param currentPlayer : es el siguente en poner punto en la malla.
	 * @param board : es la clase encargada de manejar la matriz del juego o malla.
	 * @param listDots : es la lista que contiene todos los puntos que los jugadores
	 * colocan en la malla
	 * @param listSides : son todos los lados encontrados, generados por una funcion.
	 */
	Player player1;
	Player player2;
	char currentPlayer;
	Board board;
	ListDots listDots = new ListDots();
	ListSides listSides = new ListSides();
	
	
	public void setBoard(Board board) {
		this.board = board;
	}

	/*
	 * Contructor
	 */
	public  Game(Player player1, Player player2) {
		this.player1 = player1;
		this.player2 = player2;
		player1.setOpponent(player2);
		player2.setOpponent(player1);
	}


	public Player getPlayer1() {
		System.out.println("Jugador 1");
		return player1;
	}

	public Player getPlayer2() {
		System.out.println("Jugador 2");
		return player2;
	}

	/*
	 * Funcion que agrega un punto a la malla
	 * esta funcion agregara el punto a la malla y a la lista de puntos
	 * @param x : la fila en que se desea el punto
	 * @param y : la columna en que se desea el punto
	 * @param owner: La pertenencia del punto
	 */
	public Dot addDot(int x, int y, int owner) {
		Dot dot = new Dot(x,y,owner);
		if(listDots.isDot(dot) == false) {
			listDots.addLast(dot);
			board.addDot(x,y,dot);
		}
		return dot;
	}
	
	/*
	 * Fucion que llama a otra dentro de la clase ListSides.
	 */
	public boolean findSides(Dot dot) {
		listSides = listDots.findSides(dot);
		if (listDots.getCheckLast() != 0) {
			listDots.setCheckLast(0);
			return true;
		}
		return false;
		
	}

	public void printListPolygons() {
		System.out.println("Poligonos de Player 1");
		listSides.printListPolygons(1);
		System.out.println("Poligonos de Player 2");
		listSides.printListPolygons(2);
	}

	public void addSide(int x1, int y1, int x2, int y2, int owner) {
		Dot dot1 = addDot(x1,y1,owner);
		Dot dot2 = addDot(x2,y2,owner);
		Side side = null;
		//horizontales
		if(x1 == x2) {
			if (y1+1 ==y2) {
				side = new Side(dot1,dot2);
			}else if (y1-1 == y2){
				side = new Side(dot2,dot1);
			}
		//verticales
		}else if (y1 == y2) {
			if (x1+1  == x2) {
				side = new Side(dot1,dot2);
			}else if (x1-1 == x2){
				side = new Side(dot2,dot1);
			}
		//Diagonal negativa y negativa
		}else if (x1 < x2 && y1 < y2 && x1+1 == x2 && y1+1 ==y2) {
			side = new Side(dot1,dot2);
		}else if(x1 > x2 && y1 > y2 && x1-1 == x2 && y1-1 == y2){
			side = new Side(dot2,dot1);
		//Diagonales Positivas
		}else if (x1 > x2 && y1 < y2 && x1-1 == x2 && y1+1 == y2) {
			side = new Side(dot1,dot2);
		}else if(x1 < x2 && y1 > y2 && x1+1 == x2 && x2-1 == y2) {
			side = new Side(dot2,dot1);
		}
		listSides.addLast(side);
		listSides.findPoligons(dot2);
	}
	
	
}
