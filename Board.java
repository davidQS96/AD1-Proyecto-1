/*
 * Clase que contiene una lista de listas, esta la encargada de contener los nodos de 
 * listas, es la matriz, la malla principal del guego.
 */
public class Board {
	/*
	 * @param matix : es la lista que contiene listas, para poder hacer una matrix con listas anidadas
	 * @param row : es la cantidad de filas que tiene la matriz
	 * @param column: es la cantidad de columnas que tiene la matriz
	 */
	ListLists<Object> matrix = new ListLists<Object>();
	int row;
	int column;
	
	/*
	 * Board es el contructor de la clase
	 * @param row :largo 
	 * @param column : ancho
	 * se creará del tamaño que se le indique.
	 */
	public Board(int row, int column) {
		this.row = row;
		this.column = column;
		int newrow = row  ;
		int newcolumn = column ;
		int i=0;
		int j=0;
		while( i!= newrow ){
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
		int i = 0;
		while(i != row) {
			int j = 0;
			System.out.println("  ");
			ListNodoDot<Object> n = temp.data;
			NodoDot temp2 = n.first;
			while(j != column) {
				if (temp2.data == null) {
					System.out.print("0" + " ");
				}else {
					System.out.print(temp2.data.owner + " ");
				}	
				temp2 = temp2.next;
				j++;
				}
			temp= temp.next;
			i++;
			}
		System.out.println(" ");	
		}
	/*
	 * Funcion que agrega un DOT en la posicion x,y
	 * @param x : posicion según el fila.
	 * @param y : posicion según la columna
	 * @param dot : punto que se desea agregar
	 */
	public void addDot(int x, int y, Dot dot) {
		if (x >= row && y >= column) {
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
	 * @param x : posicion según el raw.
	 * @param y : posicion según la column
	 */
	public Dot getData(int x, int y) {
		if (x >= row && y >= column) {
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
		

