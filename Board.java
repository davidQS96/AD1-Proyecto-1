
public class Board {
	ListLists<Object> matrix = new ListLists<Object>();
	int raw;
	int column;
	
	public Board(int raw, int column) {
		this.raw = raw;
		this.column = column;
		int newraw = raw + 1;
		int newcolumn = column +1;
		int i=0;
		int j=0;
		while( i!= newraw ){
			ListNodoDot<Object> list = new ListNodoDot<Object>();
			while(j != newcolumn ){
				Dot y = null;
				list.addLast(y);
				j++;
			}
			matrix.addLast(list);
			j=0;
			i++;
		}
	}
	
	/*
	 * Funcion que muestra la matriz y sus datos en la consola
	 */
	public void printBoard() {
		NodoList<Object> temp = matrix.first;
		while(temp.next != null) {
			System.out.println("  ");
			ListNodoDot<Object> n = temp.data;
			NodoDot temp2 = n.first;
			if (temp2 != null) {
			while(temp2.next != null) {
				if (temp2.data == null) {
					System.out.print("0" + " ");
					temp2 = temp2.next;
				}else {
					System.out.print(temp2.data.owner + " ");
					temp2 = temp2.next;
				}	
				}
			temp= temp.next;
			}else {
				break;
			}
			
			}
		System.out.println(" ");	
		}
	/*
	 * Funcion que agrega un DOT en la posicion x,y
	 */
	public void addDot(int x, int y, Dot dot) {
		if (x >= raw && y >= column) {
			System.out.println("ERROR");
		}else {
			NodoList<Object> temp = matrix.first;
			int i = 0;
			while (i != x) {
				temp= temp.next;
				i++;
			}
			int j = 0;
			ListNodoDot<Object> n = temp.data;
			NodoDot temp2 = n.first;
			while (j != y) {
				temp2 = temp2.next;
				j++;
			}
			temp2.data = dot;
				
		}
	}
	
	
	/*
	 * Funcion que dada una "x" y "y", retorna lo que este
	 * contenido en esa posicion.
	 */
	public Dot getData(int x, int y) {
		if (x >= raw && y >= column) {
			System.out.println("ERROR");
			return null;
		}else {
			NodoList<Object> temp = matrix.first;
			int i = 0;
			while (i != x) {
				temp= temp.next;
				i++;
			}
			int j = 0;
			ListNodoDot<Object> n = temp.data;
			NodoDot temp2 = n.first;
			while (j != y) {
				temp2 = temp2.next;
				j++;
			}
			if(temp2.data == null) {
				return null;
			}else {
			Dot dot = temp2.data;
			return dot;
		}
		}
	}
}
		

