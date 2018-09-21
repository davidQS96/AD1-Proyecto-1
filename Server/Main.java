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
		game.addDot(1,1,1);
		game.addDot(2,1,1);
		game.addDot(3,1,1);
		game.addDot(1,2,1);
		game.addDot(3,2,1);
		game.addDot(1,3,1);
		game.addDot(2,3,1);
		game.addDot(3,3,1);
		game.board.printBoard();
		game.listDots.printDots();
		Dot dot = game.board.getData(1, 2);
		System.out.println(dot.owner);
		game.findSides();
		game.listSides.printListSides();
		
		
			//Gson
		Gson gson = new Gson();
		String test = gson.toJson(board);
		System.out.println(test);
		game.listSides.findPoligons();
		game.listSides.printListPolygons();
	}
}
