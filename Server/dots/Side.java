/*
 * Clase side: representa una linea que esta en la malla
 * estas lines solo miden uno de largo, es decir solo tiene 
 * un punto inicial y uno final, entre ellos no hay ningún otro
 * punto
 */
public class Side {
	Dot start;
	Dot finish;
	
	/*
	 * @param start : es el punto inicial de la linea
	 * @param finish : es el punto en el que la linea termina
	 */
	public Side(Dot start, Dot finish) {
		this.start = start;
		this.finish = finish;
	}
	
}
