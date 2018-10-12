
package networking;


public class Initializer {
    public static void main(String[] args){
        Server server = new Server(65365);
        server.startRunning();
    }
}
