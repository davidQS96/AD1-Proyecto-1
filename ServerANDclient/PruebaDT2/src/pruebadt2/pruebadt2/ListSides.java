
package pruebadt2;


public class ListSides {
    List<Side> listSides = new List<Side>();

    public void addLast(Side side) {
            listSides.addLast(side);
    }

    public void printListSides() {
        Nodo<Side> temp = listSides.first;
        while (temp.next != null) {
            int x = temp.data.start.x;
            int y = temp.data.start.y;
            int x2 = temp.data.finish.x;
            int y2 = temp.data.finish.y;
            int owner = temp.data.start.owner;
            System.out.println("Side= ["+x+","+y+"] and ["+x2+","+y2+"] from"+ owner+ ",");
            temp = temp.next;
        }
        int x = temp.data.start.x;
        int y = temp.data.start.y;
        int x2 = temp.data.finish.x;
        int y2 = temp.data.finish.y;
        int owner = temp.data.start.owner;
        System.out.println("Side= ["+x+","+y+"] and ["+x2+","+y2+"] from "+ owner+ ".");
    }
}