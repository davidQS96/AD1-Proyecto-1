package dots;

import com.google.gson.*;

/*
 * Clase que contiene los datos generales del juego, adem�s es el 
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
	Player player1 = new Player('1');
	Player player2 = new Player('2');
	char currentPlayer = '1';
	Board board;
        private int playersConnected = 0;
        private boolean isFull = false;
        private boolean hasFinished = false;

    public boolean HasFinished() {
        return hasFinished;
    }

    public void setHasFinished(boolean hasFinished) {
        this.hasFinished = hasFinished;
    }

    public boolean isIsFull() {
        return isFull;
    }
	ListDots listDots = new ListDots();
	ListSides listSides = new ListSides();
        
        public void addPlayerConnected() {
        if(playersConnected <= 1){
        this.playersConnected += 1;
        }if(playersConnected == 2){
            isFull = true;
        }
    }
	
	
	public void setBoard(Board board) {
		this.board = board;
	}

	/*
	 * Contructor
	 */
	public  Game(int row, int column) {
            this.board = new Board(row, column);
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

	public boolean addSide(int x1, int y1, int x2, int y2, int owner) {
		Dot dot1 = addDot(x1,y1,owner);
		Dot dot2 = addDot(x2,y2,owner);
		Side side = null;
                boolean j = false;
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
		if(side == null) {
			return false;
		}else {
			j = listSides.isSide(side);
		}if (j == true) {
		listSides.addLast(side);
		listSides.findPoligons(dot2);
		if (owner == 1) {
			player1.setPoints(listSides.score);
			listSides.setScore(0);
		}else if (owner == 2) {
			player2.setPoints(listSides.score);
			listSides.setScore(0);	
		}
                return true;
		}
		return false;
	}

    public Board getBoard() {
        return this.board;
    }

    public String getListSides() {
        return new Gson().toJson(this.listSides);
    }
	
	
}
