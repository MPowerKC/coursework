
public class Cruiser extends Ship {
	public Cruiser() {
		this.setLength(6);
		this.setHit(new boolean[8]);
	}
	
	@Override
	public String getShipType() {
		return "cruiser";
	}
}
