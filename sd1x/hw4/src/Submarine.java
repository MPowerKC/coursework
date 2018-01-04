
public class Submarine extends Ship {
	public Submarine() {
		this.setLength(3);
		this.setHit(new boolean[8]);
	}
	
	@Override
	public String getShipType() {
		return "submarine";
	}

}
