package pruebadt2;
/*(package pruebadt2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {
	/**
     * Runs the application. Pairs up clients that connect.
     */
/*
    public static void main(String[] args) throws Exception {
        // Using 
        ServerSocket listener = new ServerSocket(65530);
        System.out.println("Poligon Battle is Running");
        try {
            while (true) {
                //Create Players
                Game game = new Game(new Player('1'), new Player('2'));
		game.getPlayer1();
		game.getPlayer2();
                game.player1.Opponent = game.player2;
                game.player2.Opponent = game.player1;
                game.Current = game.player1;
		 	//Create Board
		Board board = new Board(4,4);
		game.board = board;
                //Start threads for player 1 & 2
                game.player1.start();
                game.player2.start();
		
			//Add Dot to the matrix
		/*game.addDot(1,2,1);
		game.addDot(1,3,1);
		game.addDot(0,0,2);
		game.listDots.printDots();
		game.board.printBoard();
		game.board.getData(1, 2);
                */
/*
            }
        } finally {
            listener.close();
        }
    }
}
*/