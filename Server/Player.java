/*
 * clase player: clase que contiene la informacion de los juagares es partida
 * cada vez que un jugador se conecte se creara un objeto para poder manejar
 * los puntos, lineas y figuras a su nombre, al igual que el puntaje que reciba.
 */
public class Player {
	/*
	 * Attributes
	 * @param points: son los puntos actuales del objeto
	 * @param number: es el numero que se relaciona con este jugador (1 o 2)
	 * @param oponent : es el jugador que esta en contra, el otro cliente
	 */
	int points;
	char number;
	Player Opponent;

public void setOpponent(Player opponent) {
		Opponent = opponent;
	}
/*
 * Contructor
 * @param number: es el numero que se relaciona con este jugador
 */
public Player(char number) {
	this.number = number;
	
}
	
}
