
public class ListDots {
	ListNodoDot<Dot> listNodoDot = new ListNodoDot<Dot>();
	int amountDots = 0;
	
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
			System.out.println("X = "+ temp.data.x+ ", Y = "+ temp.data.y+", owener = "+ temp.data.owner+ "; " );
			temp = temp.next;
		}
		System.out.println("X = "+ temp.data.x+ ", Y = "+ temp.data.y+", owener = "+ temp.data.owner+"." );
	}	
	
	public ListSides findSides() {
		int i = 0;
		ListSides list = new ListSides();
		NodoDot temp = listNodoDot.first;
		while (i <= amountDots) {
			NodoDot temp2 = listNodoDot.first;
			int x = temp.data.x;
			int y = temp.data.y;
			int o = temp.data.owner;
			int j = 0;
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
				}if (x == x2 && (y+1) == y2 && o == o2) {
					Side side = new Side(temp.data, temp2.data);
					list.addLast(side);
				//Lineas verticales
				}if ((x+1)== x2 && y == y2 && o == o2) {
					Side side = new Side(temp.data, temp2.data);
					list.addLast(side);
				}
				if (temp2.next == null) {
					break;
				}else{
				temp2 = temp2.next;
				j++;
				}
			}
			if(temp.next == null) {
				break;
			}else {
				temp = temp.next;
				i++;
			}
		}
	return list;	
	}
	
}
