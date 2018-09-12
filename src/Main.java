
public class Main {
	public static void main(String [ ]args) {
		 	//Create Players
		Game game = new Game(new Player(1), new Player(2));
		game.getPlayer1();
		game.getPlayer2();
		
		 	//Create Board
		Board board = new Board(5,5);
		game.board = board;
		//game.board.printBoard();   //Method to print the matrix in the console
		
		
			//Add dots to the matrix
		game.addDot(0,1,1);
		game.board.printBoard();
		game.listDots.printList();
	}
}
