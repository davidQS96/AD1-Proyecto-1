package client;

import java.net.*;
import java.io.*;
import java.util.Scanner;
 
public class DotsClient {
 
    public void inicia() {
        //if (args.length < 2) return;
 
        String hostname = "localhost";
        int port = 65356;
 
        try (Socket socket = new Socket(hostname, port)) {
 
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
 
            
            String text;
 
            do {
                System.out.println("Mensaje a enviar: ");
                Scanner sc = new Scanner(System.in);
                
                text = sc.nextLine();;
 
                writer.println(text);
 
                InputStream input = socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
 
                String time = reader.readLine();
 
                System.out.println(time);
 
            } while (!text.equals("bye"));
 
            socket.close();
 
        } catch (UnknownHostException ex) {
 
            System.out.println("Server not found: " + ex.getMessage());
 
        } catch (IOException ex) {
 
            System.out.println("I/O error: " + ex.getMessage());
        }
    }
}