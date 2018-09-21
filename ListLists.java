/*
 * Clase ListList: es una lista generica de listas
 * el objetivo de esta clase es hace la matriz, 
 * el cual necesita una lista que se dedique a guaradar listas
 * como datos
 */
public class ListLists<T> {
	/*
	 * @param firts : es el primer nodo de la lista
	 * se utiliza para empezar a analizar los datos o
	 * agregar un nuevo elemento.
	 */
	NodoList<T> first = null;
	
	/*
	 * Funcion que recibe una lista para agragarla al final de
	 * la lista
	 * @param data : la lista que se desea agragar
	 */
public void addLast(ListNodoDot<T> data) {
	if (first == null) {
		first = new NodoList<T>(data);
	}else {
		NodoList<T> temp = this.first;
		while(temp.next != null) {
			temp= temp.next;
		}
		temp.next = new NodoList<T>(data);
		}
	}
/*
 * Funcion que retorna un boleano true si esta vacia
 * false si no
 */
public boolean isEmpty() {
	return this.first == null;
}

}
