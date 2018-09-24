package dots;

import networking.ServerThread;

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
	public Player player1;
	public Player player2;
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
		//player1.setOpponent(player2);
		//player2.setOpponent(player1);
	}


	public Player getPlayer1() {
		System.out.println("Jugador 1");
		return player1;
	}

	public Player getPlayer2() {
		System.out.println("Jugador 2");
		return player2;
	}

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

	/*
	 * Funcion que agrega un punto a la malla
	 * esta funcion agregara el punto a la malla y a la lista de puntos
	 * @param x : la fila en que se desea el punto
	 * @param y : la columna en que se desea el punto
	 * @param owner: La pertenencia del punto
	 */
	public void addDot(int x, int y, int owner) {
		Dot dot = new Dot(x,y,owner);
		listDots.addLast(dot);
		board.addDot(x,y,dot);
	}
	
	/*
	 * Fucion que llama a otra dentro de la clase ListSides.
	 */
	public void findSides() {
		listSides = listDots.findSides();
	}
	
	
}
