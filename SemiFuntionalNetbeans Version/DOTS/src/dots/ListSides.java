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
        
        String poliPlayer1 = null;
        String poliPlayer2 = null;
        
        
	int checkLast = 0;
	int cantLines = 0;
        int score = 0;
       private List<Dot> general = new List<Dot>();
        
        
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
	 */ //hi
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
                general = new List<Dot>(); 
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
                                    general = new List<Dot>(); 
                                    temp = temp.next;
                                    i++;
				}continue;
				}
                        general.addLast(temp.data.start);                       //AGREGAR DOTS A LISTA DE POLIGONOS
                        general.addLast(temp.data.finish);                      //AGREGAR DOTS A LISTA DE POLIGONOS
                        general.addLast(temp2.finish);                          //AGREGAR DOTS A LISTA DE POLIGONOS
			//Por la derecha
			Side side = this.findNext(temp.data,dot);
			while(this.findNext(side,dot) != null) {
                                general.addLast(side.finish);                   //AGREGAR DOTS A LISTA DE POLIGONOS
				cantLines++;
				side = this.findNext(side,dot);
			}
			//Por abajo
			Side side2 = this.findNext(temp2,dot);
			while(this.findNext(side2,dot) != null) {
                                general.addLast(side2.finish);                  //AGREGAR DOTS A LISTA DE POLIGONOS
				cantLines++;
				side2 = this.findNext(side2,dot);
			}
			
			if (side == null || side2 == null) {
				if (temp.next == null) {
					break;}
				temp = temp.next;
				i++;
				continue;
				}
                        general.addLast(side2.finish);                          //AGREGAR DOTS A LISTA DE POLIGONOS
			
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
                                                addDotsListPoligons(1);
						PolygonsPlayer1 = listPolygons;
					}if (dot.owner == 2){
                                                addDotsListPoligons(2);
						PolygonsPlayer2 = listPolygons;
					}
					List<Dot> newList = new List<Dot>();
					listPolygons = newList;
				}
			}
			if (temp.next == null) {
				break;}
                        general = new List<Dot>(); 
			temp = temp.next;
			i++;
			}
		
	}
        
        private void addDotsListPoligons(int jugador){
            int verif = 0; 
            if (jugador == 1){
                Nodo<Dot> temp = general.first;
                while(temp.next != null){
                    if (poliPlayer1 == null){
                        poliPlayer1 = temp.data.x + "," + temp.data.y;
                    }else if(verif == 0){
                        verif++;
                        poliPlayer1 = poliPlayer1 + temp.data.x + "," + temp.data.y;
                    }else{
                       poliPlayer1 = poliPlayer1 + "," + temp.data.x + "," + temp.data.y; 
                    }
                    verif++;
                    temp = temp.next;   
                }
                if (poliPlayer1 == null){
                        poliPlayer1 = temp.data.x + "," + temp.data.y;
                        }else if(verif == 0){
                        verif++;
                        poliPlayer1 = poliPlayer1 + temp.data.x + "," + temp.data.y;
                    }else{
                       poliPlayer1 = poliPlayer1 + "," + temp.data.x + "," + temp.data.y + "\\."; 
                    }
            }else if (jugador == 2){
                Nodo<Dot> temp = general.first;
                while(temp.next != null){
                    if (poliPlayer2 == null){
                        poliPlayer2 = temp.data.x + "," + temp.data.y;
                    }else if (verif == 0){
                        verif++;
                        poliPlayer2 = poliPlayer2 + temp.data.x + "," + temp.data.y;
                    }else{
                       poliPlayer2 = poliPlayer2 + "," + temp.data.x + "," + temp.data.y; 
                    }
                    verif++;
                    temp = temp.next;
            }
                if (poliPlayer2 == null){
                        poliPlayer2 = temp.data.x + "," + temp.data.y;
                    }else if (verif == 0){
                        verif++;
                        poliPlayer2 = poliPlayer2 + temp.data.x + "," + temp.data.y;
                        
                    }else{
                       poliPlayer2 = poliPlayer2 + "," + temp.data.x + "," + temp.data.y + "\\."; 
                    }
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
				//temp.data.start.printDot();
				//side.start.printDot(); Aqui se agrega a una lista
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
        
        public boolean isSide(Side side) {
		int i = 0;
		int x1 = side.start.x;
		int y1 = side.start.y;
		int x2 = side.finish.x;
		int y2 = side.finish.y;
		Nodo<Side> temp= listSides.first;
		while (i < amountSides) {
			int x3 = temp.data.start.x;
			int y3 = temp.data.start.y;
			int x4 = temp.data.finish.x;
			int y4 = temp.data.finish.y;
			if (x1 == x3 && y1 == y3 && x2 == x4 && y2 == y4) {
				return false;}
			if (temp.next == null) {
				break;
			}else {
				temp = temp.next;
				i++;
				continue;}
			}
		return true;
	}
        
        /*public String listToString(){
            Nodo<Side> temp = listSides.first;
            String listString = "";
            while (temp.next != null) {
                String x = String.valueOf(temp.data.start.x);
                String y = String.valueOf(temp.data.start.y);
                String x2 = String.valueOf(temp.data.finish.x);
                String y2 = String.valueOf(temp.data.finish.y);
                listString += "x : " x + ',' +; 
                //System.out.println("Side= ["+x+","+y+"] and ["+x2+","+y2+"],");
                
                temp = temp.next;
		}
            int x = temp.data.start.x;
            int y = temp.data.start.y;
            int x2 = temp.data.finish.x;
            int y2 = temp.data.finish.y;
            //System.out.println("Side= ["+x+","+y+"] and ["+x2+","+y2+"].");
        }*/
	
}
