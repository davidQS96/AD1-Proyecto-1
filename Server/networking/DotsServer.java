package networking;

import dots.Game;
import dots.Player;
import java.net.*;
import java.io.*;


public class DotsServer {
    
    Game game;
    
    public static void main(String[] args) throws IOException{
        DotsServer dotsServer = new DotsServer();
        dotsServer.startServer(65356);
    }
    
    public void startServer(int port){
        
       try (ServerSocket serverSocket = new ServerSocket(port)) {
 
            System.out.println("Server is listening on port " + port);
 
            while (true) {
                Game game = new Game();
                Socket socket1 = serverSocket.accept();
                ServerThread st1 = new ServerThread(socket1, game);
                String clientIP1 = socket1.getRemoteSocketAddress().toString();
                System.out.println("New client connected " + clientIP1);
                st1.setAssignedPlayer(game.getPlayer1());
                st1.start();
                Socket socket2 = serverSocket.accept();
                ServerThread st2 = new ServerThread(socket2, game);
                String clientIP2 = socket2.getRemoteSocketAddress().toString();
                System.out.println("New client connected " + clientIP2);
                st2.setAssignedPlayer(game.getPlayer2());
                st2.start();
                
                
            }
 
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    
}
