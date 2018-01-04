
public class Battleship extends Ship {
	public Battleship() {
		this.setLength(8);
		this.setHit(new boolean[8]);
	}
	
	@Override
	public String getShipType() {
		return "battleship";
	}
}
