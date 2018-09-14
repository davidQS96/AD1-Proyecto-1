
public class Main {
	public static void main(String [ ]args) {
		 	//Create Players
		Game game = new Game(new Player(1), new Player(2));
		game.getPlayer1();
		game.getPlayer2();
		 	//Create Board
		Board board = new Board(4,4);
		game.board = board;
		
			//Add Dot to the matrix
		game.addDot(1,2,1);
		game.addDot(1,3,1);
		game.addDot(0,0,2);
		game.listDots.printDots();
		game.board.printBoard();
		game.board.getData(1, 2);
	}
}
