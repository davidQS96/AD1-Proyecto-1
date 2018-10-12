package client;


import GUI.GUILauncher;
import java.net.*;
import java.io.*;
import java.util.Scanner;
 
public class DotsClient {
    
    private boolean myTurn;
    private GUILauncher gui;
    private String hostname = "localhost";
    private int port = 65356;

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public GUILauncher getGui() {
        return gui;
    }

    public void setGui(GUILauncher gui) {
        this.gui = gui;
    }

    public boolean isMyTurn() {
        return myTurn;
    }

    public void setMyTurn(boolean myTurn) {
        this.myTurn = myTurn;
    }
 
    public void inicia() throws IOException {
        //if (args.length < 2) return;
        //gui.start(primaryStage);
 
        try (Socket socket = new Socket(hostname, port)) {
 
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
 
            
            String text;
 
            do {
                System.out.println("Mensaje a enviar: ");
                Scanner sc = new Scanner(System.in);
                
                text = sc.nextLine();
                
                if(text.startsWith("UPDATE")){
                    requestUpdate(text, writer);
                }else if(text.startsWith("LINE")){
                    drawPermission(text, writer);
                }else if(text.startsWith("QUIT")){
                    quitMe(text, writer);
                }else if(text.startsWith("TURN")){
                    turn(text);
                }
 
                InputStream input = socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
 
                String command = reader.readLine();
                System.out.println("Server sent: " + command);
                if(command.startsWith("BOARD")){
                    if(command.startsWith("BOARD ROW")){
                        giveRows(command);
                    }else if(text.startsWith("COLUMN", 6)){
                        giveColumns(command.substring(6));
                    requestDrawing(command);
                    }
                }else if(command.startsWith("DRAW")){
                    processLine(command);
                }
 
                
 
            } while (!text.equals("bye"));
 
            socket.close();
 
        } catch (UnknownHostException ex) {
 
            System.out.println("Server not found: " + ex.getMessage());
 
        } catch (IOException ex) {
 
            System.out.println("I/O error: " + ex.getMessage());
        }
    }
    
    private void requestUpdate(String command, PrintWriter typer){
         typer.println(command);
    }
    
    private void drawPermission(String command, PrintWriter typer){
        typer.println(command);
    }

    private void quitMe(String command, PrintWriter typer) {
        typer.println(command);
    }

    private void processLine(String command) {
        if(command.equals("DRAW INVALITE")){
            //If invalite send GUI a String "INVALITE"
        } else { 
            //If vaid pass coordinates to GUI's draw function
        }
    }

    private void requestDrawing(String command) {
        //Send grid in String to GUI
    }

    private void turn(String text) {
        System.out.println(text.substring(text.lastIndexOf(" ") + 1));
    }

    private void giveRows(String command) {
       //pass rows to GUI
    }

    private void giveColumns(String substring) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}