package dots;



public class NodoQueue<T> {
	T data;
	NodoQueue<T> next;
	
	public NodoQueue(T data) {
		this.data = data;
		this.next = null;
	}
}
