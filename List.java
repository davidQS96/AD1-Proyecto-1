
public class List<T> {
	Nodo<T> first = null;
	
public void addLast(T data) {
	if (first == null) {
		first = new Nodo<T>(data);
	}else {
		Nodo<T> temp = this.first;
		while(temp.next != null) {
			temp= temp.next;
		}
		temp.next = new Nodo<T>(data);
		}
	}

public boolean isEmpty() {
	return this.first == null;
}

}
