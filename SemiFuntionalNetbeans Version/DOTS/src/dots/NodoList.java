package dots;

/*
 * clase NodoList: es necesaria para crear listas con listas como datos
 * el dato que contiene son listas con dots como datos.
 */
public class NodoList<T> {
	/*
	 * @param data: es el dato que manejan estos nodos
	 * @param next: es el nodo al cual estan conectados, es el siguiente.
	 */
	ListNodoDot<T> data;
	NodoList<T> next;

	/*
	 * Construcor
	 * @param data2 : es el dato que maneja este nodo.
	 */ 
	public NodoList(ListNodoDot<T> data2) {
		this.data = data2;
		this.next = null;
	}
}
