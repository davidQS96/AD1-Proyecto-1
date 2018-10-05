package networking;

import dots.List;
import dots.NodoQueue;
import java.net.Socket;


class Queue {
    List<Socket> listQueue = new List<Socket>();
    int amountNodes = 0;
    
    public Queue(){
        
    }
    
    public void addClient(Socket socket){
        listQueue.addLast(socket);
        amountNodes ++;
        System.out.println("Added to queue");
    }
    
    
}
