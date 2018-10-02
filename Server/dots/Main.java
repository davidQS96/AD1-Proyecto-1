import com.google.gson.*;

public class Main {
	
	
	public static void main(String [ ]args) {
		 	//Create Players
		Game game = new Game(new Player('1'), new Player('2'));
		game.getPlayer1();
		game.getPlayer2();
		 	//Create Board
		Board board = new Board(4,4);
		
		game.board = board;
		
			//Add Dot to the matrix
		
		game.addSide(1,1,2,1,1);
		game.addSide(1,1,1,2,1);
		game.addSide(2,1,2,2,1);
		game.addSide(1,2,2,2,2);
		game.board.printBoard();
		game.listDots.printDots();
	
		game.listSides.printListSides();
		
		game.printListPolygons();
	}
}
