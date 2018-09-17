package pruebadt2;

public class ListLists<T> {
	NodoList<T> first = null;
	
    public void addLast(ListNodoDot<T> data) {
        if (first == null) {
            first = new NodoList<T>(data);
        }else {
            NodoList<T> temp = this.first;
            while(temp.next != null) {
                    temp= temp.next;
            }
            temp.next = new NodoList<T>(data);
            }
        }

    public boolean isEmpty() {
        return this.first == null;
    }

}