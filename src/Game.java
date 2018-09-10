
public class Game {
	//Attributes
	Player player1;
	Player player2;
	Board board;
	
	
	
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
	
	
}
