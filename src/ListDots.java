
public class ListDots {
	List<Dot> list = new List<Dot>();
	
	 
	public void addLast(Dot Dot) {
		this.list.addLast(Dot);
}
	
	public void printList() {
		System.out.println("   ");
		System.out.println("Printing list");
		Nodo<Dot> temp = list.first;
		if(temp.next == null) {
			System.out.print("X = "+temp.data.x+", y = "+temp.data.y +", owner = "+temp.data.owner);
		}else {
		while(temp.next != null) {
			System.out.print("X = "+temp.data.x+", y = "+temp.data.y +", owner = "+temp.data.owner);
			temp = temp.next;
		}
		}
	}
}