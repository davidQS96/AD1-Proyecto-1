
package pruebadt2;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import com.google.gson.*;

public class Client {
    
    private static int PORT = 65530;
    private Socket socket;
    private BufferedReader input;
    private PrintWriter output;
    private String currentBoard;
    
    public Client(String serverAddress) throws Exception{
        
        //Networking
        socket = new Socket(serverAddress, PORT);
        input = new BufferedReader(new InputStreamReader(
                socket.getInputStream()));
        output = new PrintWriter(socket.getOutputStream(), true);
        
        
    }
    
    /**
     * Runs the client as an application.
     */
    public static void main(String[] args) throws Exception {
        while (true) {
            String serverAddress = (args.length == 0) ? "localhost" : args[1];
            Client client = new Client(serverAddress);
            client.play();
            /*if (!client.wantsToPlayAgain()) {
                break;*/
        }
    }
    
    public void play() throws Exception{
        String response;
        try{
            while(true){
                response = input.readLine();
                if(response.startsWith("VALID")){
                    // Insert code for valid move here
                }else if(response.startsWith("OPPONENT TURN")){
                    //Insert code for other's turn
                }else if(response.startsWith("YOU WIN")){
                    //Insert code for winning situation here
                    break;
                }else if(response.startsWith("YOU LOSE")){
                    //Same as winning situation
                    break;
                }else if(response.startsWith("DRAW")){
                    //Draw situation here
                    break;
                }
            }
            output.println("QUIT");
        }finally{
            socket.close();  
        }
    }
}
