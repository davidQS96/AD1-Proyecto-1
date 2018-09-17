
package pruebadt2;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

//Hi

public class Client {
    
    private static int PORT = 65530;
    private Socket socket;
    private BufferedReader input;
    private PrintWriter output;
    
    public Client(String serverAddress) throws Exception{
        
        //Networking
        socket = new Socket(serverAddress, PORT);
        input = new BufferedReader(new InputStreamReader(
                socket.getInputStream()));
        output = new PrintWriter(socket.getOutputStream(), true);
        
        
    }
    
    
}
