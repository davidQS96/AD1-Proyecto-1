package dots;

/*
 *Clase ListDots: Lista anidada usando especificamente nodos con puntos como datos
 */
public class ListNodoDot<T> {
	/*
	 * @param first : primer nodo de la lista
	 * se utiliza para empezar a analizar la lista o para agrgar un nuevo elemento
	 * al final. 
	 */
	NodoDot first = null;
	
	/*
	 * Funcion que recibe un dot como entrada, para agregarlo al final de la lista.
	 */
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
/*
 * Funcion que retorna un boleano true si esta vacia
 * false si no
 */
public boolean isEmpty() {
	return this.first == null;
}

}
	

