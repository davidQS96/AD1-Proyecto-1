
public class Player {
	//Attributes:
	int points;
	int Time;
	int number;	

public Player(int number) {
	this.number = number;
}
	

	//Power ups
	Boolean skip = true;
	
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public int getTime() {
		return Time;
	}
	public void setTime(int time) {
		Time = time;
	}
	public Boolean getSkip() {
		return skip;
	}
	public void setSkip(Boolean skip) {
		this.skip = skip;
	}
	public Boolean getLineDestroyer() {
		return lineDestroyer;
	}
	public void setLineDestroyer(Boolean lineDestroyer) {
		this.lineDestroyer = lineDestroyer;
	}
	Boolean lineDestroyer = true;
	
}
