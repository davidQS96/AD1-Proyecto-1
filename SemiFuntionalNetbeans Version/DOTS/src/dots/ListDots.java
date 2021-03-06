package dots;

/*
 * Clase que contiene una lista de puntos como parametro
 * esta clase utiliza esta lista para encontrar lineas y 
 * manejar los datos de la lista de puntos.
 */
public class ListDots {
	/*
	 * @param listNodoDot : Lista con los puntos de al malla
	 * @param amountDots : cantidad de elementos en la lista de puntos
	 */
	ListNodoDot<Dot> listNodoDot = new ListNodoDot<Dot>();
	int amountDots = 0;
	int checkLast = 0;
	
	
	public void setCheckLast(int checkLast) {
		this.checkLast = checkLast;
	}

	public int getCheckLast() {
		return checkLast;
	}

	public void addLast(Dot dot) {
		amountDots++;
		listNodoDot.addLast(dot);
	}
	
	/*
	 * Funcion que muestra en al consola todos los puntos que estan en la matrizs 
	 * se separan por una nueva linea
	 */
	public void printDots() {
		NodoDot temp = listNodoDot.first;
		while (temp.next != null) {
			System.out.println("X = "+ temp.data.x+ ", Y = "+ temp.data.y+", owener = "+ temp.data.owner+ ", " );
			temp = temp.next;
		}
		System.out.println("X = "+ temp.data.x+ ", Y = "+ temp.data.y+", owener = "+ temp.data.owner+"." );
	}	
	
	public boolean isDot(Dot dot) {
		int x = dot.x;
		int y = dot.y;
		int o = dot.owner;
		int i = 0;
		NodoDot temp = listNodoDot.first;
		if(temp == null) {
			return false;
		}
		while (i <= amountDots) {
			int x2 = temp.data.x;
			int y2 = temp.data.y;
			int o2 = temp.data.owner;
			if(x == x2 && y == y2 && o == o2) {
				return true;
				}
			if (temp.next!= null) {
			temp = temp.next;
			i++;
			}else {
				break;
			}
		}
		return false;
	}
	/*
	 * Funcion que utiliza los datos de la lista de puntos, para buscar lineas
	 * en la malla, si encuentra lineas, las reotrnara detro de una lista de lados.
	 */
	public ListSides findSides(Dot dot) {
		int i = 0;
		int check = 0;
		ListSides list = new ListSides();
		NodoDot temp = listNodoDot.first;
		while (i <= amountDots) {
			NodoDot temp2 = listNodoDot.first;
			int x = temp.data.x;
			int y = temp.data.y;
			int o = temp.data.owner;
			int j = 0;
			int block = 0;
			while (j <= amountDots) {
				int x2 = temp2.data.x;
				int y2 = temp2.data.y;
				int o2 = temp2.data.owner;
				if (x == x2 && y == y2 && o == o2) {
					if (temp2.next == null) {
						break;
					}else {
					temp2 = temp2.next;
					j++;
					}
					continue;
				//Lineas horizontales
				}if (x == x2 && (y+1) == y2) {
					Side side = new Side(temp.data, temp2.data);
					list.addLast(side);
					if (temp.data == dot || temp2.data == dot) {
						check++;
					}
					block++;
				//Lineas verticales
				}if ((x+1)== x2 && y == y2 ) {
					Side side = new Side(temp.data, temp2.data);
					list.addLast(side);
					if (temp.data == dot || temp2.data == dot) {
						check++;
					}
					block++;
				//L�neas Diamoganales pendiente Negativa
				}if ((x+1) == x2 && (y+1) == y2 && block == 0) {
					Side side = new Side(temp.data, temp2.data);
					list.addLast(side);
					if (temp.data == dot || temp2.data == dot) {
						check++;
					}
				//L�neas Diagonales Pendiente Positiva
				}if((x-1) == x2 && (y+1) == y2 && block == 0) {
					Side side = new Side(temp2.data, temp.data);
					list.addLast(side);
					if (temp.data == dot || temp2.data == dot) {
						check++;
					}
				}if (temp2.next == null) {
					break;
				}else{
				temp2 = temp2.next;
				j++;
				}
			}
			if(temp.next == null) {
				break;
			}else {
				block = 0;
				temp = temp.next;
				i++;
			}
		}
	if (check != 0) {
		checkLast++;
	}
	return list;	
	}

	
}
