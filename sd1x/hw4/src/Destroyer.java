
public class Destroyer extends Ship {
	public Destroyer() {
		this.setLength(4);
		this.setHit(new boolean[8]);
	}
	
	@Override
	public String getShipType() {
		return "destroyer";
	}

}
