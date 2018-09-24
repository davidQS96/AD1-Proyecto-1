package networking;

import dots.Game;
import dots.Player;
import java.net.*;
import java.io.*;


public class DotsServer {
    
    Game game;
    
    public static void main(String[] args) throws IOException{
        //if(args.length < 1) return;
        
        int port = 65356;
        
       try (ServerSocket serverSocket = new ServerSocket(port)) {
 
            System.out.println("Server is listening on port " + port);
 
            while (true) {
                Socket socket1 = serverSocket.accept();
                Player P1 = new Player(new ServerThread(socket1));
                String clientIP1 = socket1.getRemoteSocketAddress().toString();
                System.out.println("New client connected " + clientIP1);
                Socket socket2 = serverSocket.accept();
                Player P2 = new Player(new ServerThread(socket2));
                String clientIP2 = socket2.getRemoteSocketAddress().toString();
                System.out.println("New client connected " + clientIP2);
                
                Game game = new Game(P1, P2);
                
                
            }
 
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
}
    
}
