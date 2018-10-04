package dots;



public class NodoQueue<T> {
	T data;
	NodoQueue<T> next;
	
	public NodoQueue(T data2) {
		this.data = data2;
		this.next = null;
	}
}
