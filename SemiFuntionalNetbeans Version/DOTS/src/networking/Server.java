
package networking;

import dots.Game;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Jon Gs
 */
public class Server {
    private ServerSocket server;
    private Queue gameQueue;
    private Queue waitQueue;
    private int port = 65356;
    private int minPlayers; 
    private Game game;

    Server(int port) {
        this.port = port;
    }
    
    public void startRunning(){
        try{
            server = new ServerSocket(port);
            game = null;
            while(true){
                if(game == null){
                    game = new Game(4, 4);
                }
                try{
                    if(!game.isIsFull()){
                        waitForConnection();
                    }
                    if(game.isIsFull()){
                        addConnection();
                    }
                }catch(EOFException e){
                    System.out.println("Connection has been finish");
                }
            }
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }

    private void waitForConnection() throws IOException {
        System.out.println("Waiting for connection on port: " + port + "\n");
        Socket socket = server.accept();
        System.out.println("Now connected to " + socket.getRemoteSocketAddress());
        ServerThread st = new ServerThread(socket, this.game);
        if(!this.game.getPlayer1().hasBeenAssigned()){
            st.assignedPlayer = game.getPlayer1();
            game.getPlayer1().setBeenAssigned(true);
        } else {
            st.assignedPlayer = game.getPlayer2();
            game.getPlayer2().setBeenAssigned(true);
        }
        this.game.addPlayerConnected();
        st.start();
    }
    
    private void addConnection() throws IOException {
        System.out.println("Waiting for connection on port: " + port + "\n");
        Socket socket = server.accept();
        System.out.println("Now connected to " + socket.getRemoteSocketAddress());
        ServerThread st = new ServerThread(socket, null);
    }
    
}
