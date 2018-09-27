

public class Queue<T> {
	NodoQueue<T> first = null;
	int count;

	public void add(T data) {
		count++;
		if (first == null) {
			first = new NodoQueue<T>(data);
		}else {
			NodoQueue<T> temp = this.first;
			while(temp.next != null) {
				temp= temp.next;
			}
			temp.next = new NodoQueue<T>(data);
			}
		}
	
	
	public T pull() {
		if (first == null) {
			return null;
		}
		T data = first.data;
		if (first.next == null) {
			this.first = null;
		}else {
			this.first = first.next;
		}
		count--;
		return data;
	}
}
