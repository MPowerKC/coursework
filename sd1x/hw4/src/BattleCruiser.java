
public class BattleCruiser extends Ship {
	public BattleCruiser() {
		this.setLength(7);
		this.setHit(new boolean[8]);
	}
	
	@Override
	public String getShipType() {
		return "battlecruiser";
	}
}
