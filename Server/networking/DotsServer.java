package networking;

import dots.Game;
import dots.Player;
import java.net.*;
import java.io.*;


public class DotsServer {
    
    Game game;
    Queue queue = new Queue();
    
    
    public static void main(String[] args) throws IOException{
        DotsServer dotsServer = new DotsServer();
        dotsServer.startServer(65356);
    }
    
    public void startServer(int port){
        
       try (ServerSocket serverSocket = new ServerSocket(port)) {
           game = null;
 
            System.out.println("Server is listening on port " + port);
 
            // acepta la coneccion y lo agrega a la cola
            
            
           
                /*Game game = new Game(4, 4);
                Socket socket1 = serverSocket.accept();
                ServerThread st1 = new ServerThread(socket1, game);
                st1.setAssignedPlayer(game.getPlayer1());
                st1.start();
                Socket socket2 = serverSocket.accept();
                ServerThread st2 = new ServerThread(socket2, game);
                st2.setAssignedPlayer(game.getPlayer2());
                st2.start();*/
                
            while(true){
                if(game == null){
                    game = new Game(4, 4);
                    System.out.println("Creo juego");
                } else if(game.HasFinished()){
                    game = new Game(4, 4);
                }
                System.out.println("Player 1 is: " + game.getPlayer1().hasBeenAssigned());
                System.out.println("Player 2 is: " + game.getPlayer2().hasBeenAssigned());
                System.out.println("Game is: " + game.isFull());
                try{
                    System.out.println("Waiting for connection on port: " + port + "\n");
                    Socket socket = serverSocket.accept();
                    ServerThread st = new ServerThread(socket, null);
                    if (!game.isFull()){
                        if(!game.getPlayer1().hasBeenAssigned()){
                            st.setAssignedPlayer(game.getPlayer1());
                            game.getPlayer1().setBeenAssigned(true);
                            st.setGame(game);
                            st.start();
                            System.out.println("Asigno un jugador y  su respectivo juego");
                        } else {
                            st.setAssignedPlayer(game.getPlayer2());
                            game.getPlayer2().setBeenAssigned(true);
                            st.setGame(game);
                            st.start();
                            System.out.println("Asigno un jugador y  su respectivo juego");
                        }
                        game.addPlayerConnected();
                    } else {
                        queue.addClient(socket);
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
                
                
            
 
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    
}
