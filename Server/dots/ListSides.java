/*
 * Clase que contiene una lista generica con lados encontrados en la malla de juego
 * Además de tener funciones que utilizan los datos de esta clase para otras funciones
 * como la busqueda de poligonos.
 */
public class ListSides {
	/*
	 * @param ListSides: Lista que contiene todos los lados encontrados en la malla
	 * @param amountSides: cantidad de lados en lista
	 * @param listPolygons: Lista con los poligonos encontrados
	 * 
	 */
	List<Side> listSides = new List<Side>();
	int amountSides = 0;
	private List<Dot> listPolygons = new List<Dot>();
	
	/*
	 * Fucnion que agrega un dato al final de la lista
	 * @param side : es el dato que se desea agregar.
	 */
	public void addLast(Side side) {
		amountSides++;
		listSides.addLast(side);
	}
	 
	/*
	 * Funcion que Imprime en la consola los lados encontrados en la malla
	 */
	public void printListSides() {
		Nodo<Side> temp = listSides.first;
		while (temp.next != null) {
			int x = temp.data.start.x;
			int y = temp.data.start.y;
			int x2 = temp.data.finish.x;
			int y2 = temp.data.finish.y;
			int owner = temp.data.start.owner;
			System.out.println("Side= ["+x+","+y+"] and ["+x2+","+y2+"] from "+ owner+ ",");
			temp = temp.next;
		}
		int x = temp.data.start.x;
		int y = temp.data.start.y;
		int x2 = temp.data.finish.x;
		int y2 = temp.data.finish.y;
		int owner = temp.data.start.owner;
		System.out.println("Side= ["+x+","+y+"] and ["+x2+","+y2+"] from "+ owner+ ".");
	}
	
	
	/*
	 * Funcion que utiliza la lista Sides para buscar poligonos
	 */
	public void findPoligons() {
		List<Dot> listPolygons = new List<Dot>();
		int i = 0;
		Nodo<Side> temp = listSides.first;
		Side sideVerif = null;
		while(i < amountSides) {
			Side temp2 = this.findEqualStart(temp.data);
			if (temp2 == null || sideVerif == temp2 || sideVerif == temp.data) {
				if (temp.next == null) {
					break;
				}else {
					temp = temp.next;
					i++;
				}continue;
				}
			//Por la derecha
			Side side = this.findNext(temp.data);
			while(this.findNext(side) != null) {
				side = this.findNext(side);
			}
			//Por abajo
			Side side2 = this.findNext(temp2);
			while(this.findNext(side2) != null) {
				side2 = this.findNext(side2);
			}
			
			if (side == null || side2 == null) {
				if (temp.next == null) {
					break;}
				temp = temp.next;
				i++;
				continue;
				}
			
			//verificar si se cierra la figura
			if (side.finish == side2.finish && side != side2) {
				System.out.println("Poligono encontrado");
				sideVerif = temp.data;  //Problema, busca figura dos veces, con esto se soluciona 
				listPolygons.addLast(temp.data.start);
				listPolygons.addLast(side.finish);
			}
			if (temp.next == null) {
				break;}
			temp = temp.next;
			i++;
			}
		this.listPolygons = listPolygons;
		
		
	}
	
	/*
	 *  Funcion que resibe un side, busca en la lista de Sides uno que tenga como start el un punto igual
	 * al finish del side en la entrada
	 * Retorna el punto encontrado o null si no lo hace
	 */
	private Side findNext(Side side) {
		int i =0;
		Nodo<Side> temp= listSides.first;
		Dot dot1F = side.finish;
		while (i < amountSides) {
			if (temp.data == side) {
				if (temp.next == null) {
					break;
				}else {
					temp = temp.next;
					i++;
					continue;}
				}
			Dot dot2S = temp.data.start;
			if (dot1F == dot2S) {
				temp.data.start.printDot();
				side.start.printDot();
				return temp.data;
				}
			if(temp.next == null) {
				break;
			}else {
				temp = temp.next;
				i++;
				}
			}
		return null;
	}
	/*
	 * Funcion que recibe un side y busca en la lista de sides, uno que posea un start igual al del side en
	 * la entrada.
	 * retorna el side encontrado.
	 */
	private Side findEqualStart(Side side) {
		int i = 0;
		Nodo<Side> temp= listSides.first;
		Dot dot1 = side.start;
		while (i < amountSides) {
			if (temp.data == side) {
				if (temp.next == null) {
					break;
				}else {
					temp = temp.next;
					i++;
					continue;}
				}
			Dot dot2 = temp.data.start;
			if (dot1 == dot2) {
				return temp.data;
			}
			if(temp.next == null) {
				break;
			}else {
				temp = temp.next;
				i++;
			}
		}
		return null;
	}
	
	/*
	 * Funcion para probar lista de poligonos
	 * Hace un print en la consola de los poligonos encontrados
	 */
	public void printListPolygons() {
		Nodo<Dot> temp = listPolygons.first;
		if (temp != null) {
			
		
		while (temp.next != null) {
			System.out.println("Poligono de: ["+temp.data.x+","+temp.data.y+"] a ["+temp.next.data.x+", "+temp.next.data.y+"] de: "+temp.data.owner);
			temp = temp.next;
			temp = temp.next;
			if(temp == null) {
				break;
			}
		}
		}
	}
	
}
