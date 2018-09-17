
public class Game {
	//Attributes
	Player player1;
	Player player2;
	Board board;
	ListDots listDots = new ListDots();
	ListSides listSides = new ListSides();
	
	
	
	public void setBoard(Board board) {
		this.board = board;
	}


	public  Game(Player player1, Player player2) {
		this.player1 = player1;
		this.player2 = player2;
	}


	public Player getPlayer1() {
		System.out.println("Jugador 1");
		return player1;
	}

	public Player getPlayer2() {
		System.out.println("Jugador 2");
		return player2;
	}


	public void addDot(int x, int y, int owner) {
		Dot dot = new Dot(x,y,owner);
		listDots.addLast(dot);
		board.addDot(x,y,dot);
	}
	public void findSides() {
		listSides = listDots.findSides();
	}
	
	
}
