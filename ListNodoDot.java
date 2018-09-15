
public class ListNodoDot<T> {
	NodoDot first = null;
	
public void addLast(Dot data) {
	if (first == null) {
		first = new NodoDot(data);
	}else {
		NodoDot temp = this.first;
		while(temp.next != null) {
			temp= temp.next;
		}
		temp.next = new NodoDot(data);
		}
	}

public boolean isEmpty() {
	return this.first == null;
}

}
	

