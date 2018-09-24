package networking;

import dots.Player;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;


public class ServerThread extends Thread {
    private Socket socket;

    public ServerThread(Socket socket) {
        this.socket = socket;
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
                    String command = "Actualizacion de malla";
                    System.out.println("Server sending: " + command + " to " + clientIP);
                    writer.println(command);
                }else if(text.startsWith("LINE")){
                    String command = "DRAW x1,y,x2,y2";
                    System.out.println("Server sending: " + command + " to " + clientIP);
                    writer.println(command);
                }else{
                    System.out.println("Wasn't able to resolve: " + "(" + text + ")" + " from " + clientIP);
                    writer.println("RESEND");
                }
 
            } while (!text.equals("QUIT"));
 
            socket.close();
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    
}
