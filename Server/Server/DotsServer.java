package networking;

import java.net.*;
import java.io.*;


public class DotsServer {
    
    public static void main(String[] args) throws IOException{
        //if(args.length < 1) return;
        
        int port = 65356;
        
       try (ServerSocket serverSocket = new ServerSocket(port)) {
 
            System.out.println("Server is listening on port " + port);
 
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected");
 
                new ServerThread(socket).start();
            }
 
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
}
    
}
