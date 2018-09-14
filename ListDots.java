
public class ListDots {
	List<Dot> list = new List<Dot>();
	
	public void addLast(Dot dot) {
		list.addLast(dot);
	}
	
	/*
	 * Funcion que muestra en al consola todos los puntos que estan en la matrizs 
	 * se separan por una nueva linea
	 */
	public void printDots() {
		Nodo<Dot> temp = list.first;
		while (temp.next != null) {
			System.out.println("X = "+ temp.data.x+ ", Y = "+ temp.data.Y+", owener = "+ temp.data.owner+ "; " );
			temp = temp.next;
		}
		System.out.println("X = "+ temp.data.x+ ", Y = "+ temp.data.Y+", owener = "+ temp.data.owner+"." );
	}	
}
