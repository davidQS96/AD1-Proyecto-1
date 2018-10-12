package dots;

/*
 * Clase punto, esta es la base del juego, los jugadores crear�n este tipo de
 * objeto para formar lineas y figuras
 */
public class Dot {

	int x;
	int y;
	int owner;
	/*
	 * Contructor
	 * @param x : Posicion en la matriz con respecto a las fila
	 * @param y : Posicion en la matriz con respecto a las columna
	 * @param owner: pertenencia del punto (1 jugador 1, 2 juagador 2)
	 */
	public Dot(int x, int y, int owner) {
		this.x = x;
		this.y = y;
		this.owner = owner;
	}
	
	/*
	 * Funcion que imprime en al pantalla los atributos del punto.
	 */
	public void printDot() {
		System.out.println("["+x+", "+y+"] from "+owner );
	}
}
