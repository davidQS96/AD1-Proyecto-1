package projecto;
import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Scanner;

public class Server{
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private ServerSocket server;
    private Socket connection;
    
    public Server(){
                
    }
    
    //Server Setup
    public void startRunning(){
        try{
            server = new ServerSocket(6789, 100);
            while(true){
                try{
                    //connect and play
                    waitForConnection();
                    setupStreams();
                    whilePlaying();
                }catch(EOFException eofException){
                    System.out.println("/n Server ended the connection.");
                }finally{
                    closeSocket();
                }
            }
        }catch(IOException ioException){
            ioException.printStackTrace();
        }
    }
    
    //wait for connection, then give connection info
    private void waitForConnection() throws IOException{
        System.out.println("Waiting...\n");
        connection = server.accept();
        System.out.println("Now connected to " + connection.getInetAddress().getHostAddress());
    }
    
    private void setupStreams() throws IOException{
        output = new ObjectOutputStream(connection.getOutputStream());
        output.flush();
        input = new ObjectInputStream(connection.getInputStream());
        System.out.println("Streams now setup.");
    }
    
    //Game code goes here
    private void whilePlaying() throws IOException{
        System.out.println("Connection Successful");
        Scanner scanner = new Scanner(System.in);
        String message = scanner.nextLine();
        do{
            //send messages
            try{
                message = (String) input.readObject();
                System.out.println("\n " + message);
            }catch(ClassNotFoundException classNotFoundException){
                System.out.println("\n wtf");
            }
        }while(!message.equals("END"));
        
    }
    
    private void closeSocket(){
        System.out.println("Closing connections...");
        try{
            output.close();
            input.close();
            connection.close();
        }catch(IOException ioException){
            ioException.printStackTrace();
        }
    }
    
    //Sends message to client
    private void sendMessage(){
        
    }
    
}

