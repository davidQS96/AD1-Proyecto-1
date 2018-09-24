package dots;

import java.net.ServerSocket;
import networking.ServerThread;


public class Player {
        ServerThread serverThread;
	int points;
	char number;
	Player Opponent;

    public Player(ServerThread serverThread) {
        this.serverThread = serverThread;
        this.serverThread.start();
       
    }

public void setOpponent(Player opponent) {
		Opponent = opponent;
	}

public Player(char number) {
	this.number = number;
	
}
	
}
