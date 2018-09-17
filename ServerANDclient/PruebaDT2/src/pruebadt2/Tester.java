
package pruebadt2;

import com.google.gson.*;


public class Tester {
    public static void main(String [ ]args) {
        Board board = new Board(4,4);
        String test = new Gson().toJson(board);
        System.out.println(test);
        

    }
}
