
public class Main {
	public static void main(String [ ]args) {
		 	//Create Players
		Game game = new Game(new Player(1), new Player(2));
		game.getPlayer1();
		game.getPlayer2();
		 	//Create Board
		Board board = new Board(4,4);
		game.board = board;
		game.board.printBoard();
	}
}
