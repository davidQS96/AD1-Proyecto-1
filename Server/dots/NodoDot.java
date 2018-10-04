package dots;

/*
 * Clase NodoDot: clase necesaria para hacer listas de puntos
 * esta clase maneja especificamente datos tipo Dot
 */
public class NodoDot{
	/*
	 * @param data: datos tipo punto
	 * @param next: es el siguente en la lista
	 */
	Dot data;
	NodoDot next;
	
	/*
	 * Contructor
	 * @param data2 : es el punto que se desea tenga este nodo
	 */
public NodoDot(Dot data2) {
	this.data = data2;
	this.next = null;
}
}
