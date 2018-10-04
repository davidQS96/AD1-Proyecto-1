package dots;

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
	private int points = 0;
	private final char number;
	private Player Opponent;
        private boolean myTurn = false;

    public void setMyTurn(boolean myTurn) {
        this.myTurn = myTurn;
    }
        private boolean beenAssigned = false;

    public boolean hasBeenAssigned() {
        return beenAssigned;
    }

    public void setBeenAssigned(boolean beenAssigned) {
        this.beenAssigned = beenAssigned;
    }
        
        public void setPoints(int points) {
            this.points = points;
	}

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

        public String getNumber() {
            return String.valueOf(this.number);
        }
	
}
