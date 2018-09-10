
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
			List<Object> list = new List<Object>();
			while(j != newcolumn ){
				int y = 0;
				list.addLast(y);
				j++;
			}
			matrix.addLast(list);
			j=0;
			i++;
		}
	}
	public void printBoard() {
		NodoList<Object> temp = matrix.first;
		while(temp.next != null) {
			System.out.println("  ");
			List<Object> n = temp.data;
			Nodo<Object> temp2 = n.first;
			if (temp2 != null) {
			while(temp2.next != null) {
				System.out.print(temp2.data + " ");
				temp2 = temp2.next;
				}
			temp= temp.next;
			}else {
				break;
			}
			}
			
		}
}
		

