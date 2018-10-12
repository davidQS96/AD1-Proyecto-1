package dots;

import com.google.gson.*;

public class Main {
	
	
	public static void main(String [ ]args) {
		 	//Create Players
		Game game = new Game(4,4);
		game.getPlayer1();
		game.getPlayer2();
		
			//Add Dot to the matrix
		
		game.addSide(1,1,2,1,1);
		game.addSide(1,1,1,2,1);
		game.addSide(2,1,2,2,1);
		game.addSide(1,2,2,2,2);
                game.addSide(1,2,2,2,2);
		game.board.printBoard();
		game.listDots.printDots();
	
		game.listSides.printListSides();
                
                String json = game.getListSides();
                System.out.println(json);
                JsonObject jobj1 = new Gson().fromJson(json, JsonObject.class);
                JsonObject jobj = jobj1.get("listSides").getAsJsonObject().get("first").getAsJsonObject();
                System.out.println("Se convirtio en String");
                boolean pastFirst = false;
                String result = "";
                while(jobj.get("next") != null){
                if(!pastFirst){
                    //jobj = jobj.get("first").getAsJsonObject();
                    JsonObject first = jobj.get("data").getAsJsonObject();
                    String x1 = first.get("start").getAsJsonObject().get("x").getAsString();
                    String y1 = first.get("start").getAsJsonObject().get("y").getAsString();
                    String x2 = first.get("finish").getAsJsonObject().get("x").getAsString();
                    String y2 = first.get("finish").getAsJsonObject().get("y").getAsString();
                    String owner = first.get("start").getAsJsonObject().get("owner").getAsString();
                    result = x1+','+y1+','+x2+','+y2+','+owner;
                    System.out.println(result);
                    jobj = jobj.get("next").getAsJsonObject();
                }else{
                        JsonObject data = jobj.get("data").getAsJsonObject();
                        String x1 = data.get("start").getAsJsonObject().get("x").getAsString();
                        String y1 = data.get("start").getAsJsonObject().get("y").getAsString();
                        String x2 = data.get("finish").getAsJsonObject().get("x").getAsString();
                        String y2 = data.get("finish").getAsJsonObject().get("y").getAsString();
                        String owner = data.get("start").getAsJsonObject().get("owner").getAsString();
                        result = x1+','+y1+','+x2+','+y2+','+owner;
                        System.out.println(result);
                        jobj = jobj.get("next").getAsJsonObject();
                    }
                }
                /*JsonObject first = jobj.get("listSides").getAsJsonObject().get("first").getAsJsonObject().get("data").getAsJsonObject();
                System.out.println(first.toString());
                String others = jobj.get("listSides").getAsJsonObject().get("first").getAsJsonObject().get("next").getAsJsonObject().toString();
                System.out.println(others);*/
                
                //System.out.println(result);
		
		game.printListPolygons();
	}
}
