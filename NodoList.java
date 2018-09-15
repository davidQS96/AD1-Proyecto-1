
public class NodoList<T> {
	ListNodoDot<T> data;
	NodoList<T> next;

	public NodoList(ListNodoDot<T> data2) {
		this.data = data2;
		this.next = null;
	}
}
