package networking;

import com.google.gson.GsonBuilder;
import dots.Board;
import dots.Game;
import dots.Player;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;


public class ServerThread extends Thread {
    private Game game;
    private Player assignedPlayer;
    private Socket socket;

    public ServerThread(Socket socket, Game game) {
        this.socket = socket;
        this.game = game;
    }
    
    public void setAssignedPlayer(Player player){
        this.assignedPlayer = player;
    }
    
    public void run(){
        try {
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
 
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
 
 
            String text;
 
            do {
                text = reader.readLine();
                String clientIP = socket.getRemoteSocketAddress().toString();
                if(text.startsWith("UPDATE")){
                    String command = "UPDATE";
                    String updateJson = new GsonBuilder().create().toJson(game.getBoard());
                    System.out.println(updateJson);
                    System.out.println("Server sending: " + command + " to " + clientIP);
                    writer.println("BOARD " + updateJson);
                }else if(text.startsWith("LINE")){
                    String command = "DRAW x1,y,x2,y2";
                    System.out.println("Server sending: " + command + " to " + clientIP);
                    writer.println(command);
                }else{
                    System.out.println("Wasn't able to resolve: " + "(" + text + ")" + " from " + clientIP);
                    writer.println("RESEND");
                }
 
            }while (!text.equals("QUIT"));
 
            socket.close();
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    
}
