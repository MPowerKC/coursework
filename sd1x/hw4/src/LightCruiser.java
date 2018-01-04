
public class LightCruiser extends Ship {
	public LightCruiser() {
		this.setLength(5);
		this.setHit(new boolean[8]);
	}
	
	@Override
	public String getShipType() {
		return "light cruiser";
	}
}
