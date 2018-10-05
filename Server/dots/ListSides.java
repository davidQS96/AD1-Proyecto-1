package dots;

/*
 * Clase que contiene una lista generica con lados encontrados en la malla de juego
 * Adem�s de tener funciones que utilizan los datos de esta clase para otras funciones
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
	private List<Dot> PolygonsPlayer1 = new List<Dot>();
	private List<Dot> PolygonsPlayer2 = new List<Dot>();
	int checkLast = 0;
	int cantLines = 0;
	int score = 0;
	public void setScore(int score) {
		this.score = score;
	}

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
			System.out.println("Side= ["+x+","+y+"] and ["+x2+","+y2+"],");
			temp = temp.next;
		}
		int x = temp.data.start.x;
		int y = temp.data.start.y;
		int x2 = temp.data.finish.x;
		int y2 = temp.data.finish.y;
		System.out.println("Side= ["+x+","+y+"] and ["+x2+","+y2+"].");
	}
	
	
	/*
	 * Funcion que utiliza la lista Sides para buscar poligonos
	 */
	public void findPoligons(Dot dot) {
		List<Dot> listPolygons = new List<Dot>();
		int i = 0;
		Nodo<Side> temp = listSides.first;
		Side sideVerif = null;
		while(i < amountSides) {
			Side temp2 = this.findEqualStart(temp.data,dot);
			if (temp2 == null || sideVerif == temp2 || sideVerif == temp.data) {
				if (temp.next == null) {
					break;
				}else {
					temp = temp.next;
					i++;
				}continue;
				}
			//Por la derecha
			Side side = this.findNext(temp.data,dot);
			while(this.findNext(side,dot) != null) {
				side = this.findNext(side,dot);
			}
			//Por abajo
			Side side2 = this.findNext(temp2,dot);
			while(this.findNext(side2,dot) != null) {
				side2 = this.findNext(side2,dot);
			}
			
			if (side == null || side2 == null) {
				if (temp.next == null) {
					break;}
				temp = temp.next;
				i++;
				continue;
				}
			
			// Side 1
			int x1 = side.finish.x; 
			int y1 = side.finish.y;
			//side2
			int x2 = side2.finish.x; 
			int y2 = side2.finish.y;
			
			//verificar si se cierra la figura
			if (x1 == x2 && y1 == y2) {
				System.out.println("Poligono encontrado");
				score = (cantLines*100);
				sideVerif = temp.data;  //Problema, busca figura dos veces, con esto se soluciona 
				if (checkLast != 0) {
					System.out.println(cantLines);
					checkLast = 0;
					listPolygons.addLast(temp.data.start);
					listPolygons.addLast(side.finish);
					if (dot.owner == 1) {
						PolygonsPlayer1 = listPolygons;
					}if (dot.owner == 2){
						PolygonsPlayer2 = listPolygons;
					}
					List<Dot> newList = new List<Dot>();
					listPolygons = newList;
					System.out.println("Cantidad de lineas: " + cantLines);
				}
			}
			if (temp.next == null) {
				break;}
			cantLines = 0;
			temp = temp.next;
			i++;
			}
		
	}
	
	/*
	 *  Funcion que resibe un side, busca en la lista de Sides uno que tenga como start el un punto igual
	 * al finish del side en la entrada
	 * Retorna el punto encontrado o null si no lo hace
	 */
	private Side findNext(Side side, Dot checkDot) {
		if (side == null){
			return null;
		}
		if (side.finish== null || side.start == null){
			return null;
		}
		int i =0;
		Nodo<Side> temp= listSides.first;
		Dot dot1F = side.finish;
		int x1 = dot1F.x; 
		int y1 = dot1F.y;
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
			int x2 = dot2S.x;
			int y2 = dot2S.y;
			if (checkDot == side.start || checkDot == dot1F || checkDot == dot2S || checkDot == temp.data.finish ) {
				checkLast++;
			}
			if (x1 == x2 && y1 == y2) {
				cantLines++;
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
	private Side findEqualStart(Side side, Dot checkDot) {
		int i = 0;
		int x1 = side.start.x; 
		int y1 = side.start.y;
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
			int x2 = dot2.x;
			int y2 = dot2.y;
			if (checkDot == dot1 || checkDot == dot2) {
				checkLast++;
			}
			if (x1 == x2 && y1 == y2) {
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
	public void printListPolygons(int player) {
		Nodo<Dot> temp = null;
		if (player == 1) {
			temp = PolygonsPlayer1.first;
			if (temp == null) {
				System.out.println("       No tiene la poligonos");
				return;
			}
		}if (player == 2) {
			temp = PolygonsPlayer2.first;
			if (temp == null) {
				System.out.println("       No tiene la poligonos");
				return;
			}
		}
		if (temp != null) {
			while (temp.next != null) {
				System.out.println("      Poligono de: ["+temp.data.x+","+temp.data.y+"] a ["+temp.next.data.x+", "+temp.next.data.y+"] de: "+player);
				temp = temp.next;
				temp = temp.next;
				if(temp == null) {
					return;
				}
			}
		}else {
			return;
		}
	}
	
}
