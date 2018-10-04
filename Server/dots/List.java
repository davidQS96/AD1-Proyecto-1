package dots;

/*
 * Clase list: es una lista anidada y generica para uso general
 */
public class List<T> {
	/*
	 * @param firts : es el primer nodo de la lista, de aqui se empieza
	 * s reviar el resto de nodos para agregar punto o revisar datos.
	 */
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
	/*
	 * Funcion que retorna un boleano true si esta vacia
	 * false si no
	 */
public boolean isEmpty() {
	return this.first == null;
}

}
