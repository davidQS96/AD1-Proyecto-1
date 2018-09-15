package pruebadt2;

public class NodoList<T> {
	List<T> data;
	NodoList<T> next;

	public NodoList(List<T> data2) {
		this.data = data2;
		this.next = null;
	}
}
