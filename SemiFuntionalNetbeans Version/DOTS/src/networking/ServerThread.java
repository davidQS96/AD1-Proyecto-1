package networking;

import com.google.gson.Gson;
import dots.Board;
import dots.Dot;
import dots.Game;
import dots.Player;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ServerThread extends Thread {
    private Game game;
    Player assignedPlayer;
    private Socket socket;

    public ServerThread(Socket socket, Game game) {
        this.socket = socket;
        this.game = game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
    
    public void setAssignedPlayer(Player player){
        this.assignedPlayer = player;
    }
    
    @Override
    public void run(){
        String clientIP = socket.getRemoteSocketAddress().toString();
        System.out.println("New client connected " + clientIP);
        
        try {
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
 
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
 
 
            String text;
 
            do {
                text = reader.readLine();
                System.out.println("ServerThread " + text);
                if(text.startsWith("UPDATE")){
                    String command = "UPDATE";
                    String columnRow = game.getBoard().getColumnRow();
                    String sides = game.getListSides();
                    System.out.println("Server sending: " + command + " to " + clientIP);
                    System.out.println(columnRow);
                    System.out.println(sides);
                    writer.println(command + ' ' + assignedPlayer.getNumber() + ' ' + columnRow + ' ' + sides);
                }else if(text.startsWith("LINE")){
                    String command = validateLine(text.substring(5)) ;
                    if (!command.equals("DRAW FALSE")){
                    int owner = Integer.parseInt(String.valueOf(command.charAt(5)));
                    System.out.println(owner);
                    int x1 = Integer.parseInt(String.valueOf(command.charAt(7)));
                    System.out.println(x1);
                    int y1 = Integer.parseInt(String.valueOf(command.charAt(9)));
                    System.out.println(y1);
                    int x2 = Integer.parseInt(String.valueOf(command.charAt(11)));
                    System.out.println(x2);
                    int y2 = Integer.parseInt(String.valueOf(command.charAt(13)));
                    System.out.println(y2);
                    game.addSide(x1, y1, x2, y2, owner);
                    }
                    
                    System.out.println("Command = " + command);
                    System.out.println("Server sending: " + command.substring(0, 4) + " to " + clientIP);
                        
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
//hi
    private String validateLine(String substring) {
        int ID = Integer.parseInt(String.valueOf(substring.charAt(0)));
        int x1 = Integer.parseInt(String.valueOf(substring.charAt(2)));
        int y1 = Integer.parseInt(String.valueOf(substring.charAt(4)));
        int x2 = Integer.parseInt(String.valueOf(substring.charAt(6)));
        int y2 = Integer.parseInt(String.valueOf(substring.charAt(8)));
        Dot dot1 = game.getBoard().getData(x1, y1);
        Dot dot2 = game.getBoard().getData(x2, y2);
        System.out.println(dot1);
        System.out.println(dot2);
        /*if((dot1 == null) | (dot2 == (null))){
            if(dot1 == null){
            game.addDot(x1, y1, ID);
            }
            if(dot2 == null){
            game.addDot(x2, y2, ID);
            }
            return ("DRAW " + ID + ' ' + x1 + ',' + y1 + ',' + x2 + ',' + y2 );
            
        }else{
            return ("DRAW " + "FALSE" );
        }*/
        game.addSide(x1, y1, x2, y2, ID);
        return ("DRAW " + ID + ' ' + x1 + ',' + y1 + ',' + x2 + ',' + y2 );
    }
    
}
