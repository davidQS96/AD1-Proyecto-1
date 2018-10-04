package dots;

/*
 * Clase nodo, clase necesaria para hacer listas anidadas
 * esta clase utiliza generics de java, por lo tanto no posee
 * un dato especifico el cual maneja.
 */
public class Nodo<T> {

	T data;
	Nodo<T> next;
	
	/*
	 * Constructor
	 * @param data : es el dato que contien el nodo de la lista
	 * @param next : aputna al siguente nodo, es nulo si no hay un siguente
	 */
public Nodo(T data2) {
	this.data = data2;
	this.next = null;
}
}
