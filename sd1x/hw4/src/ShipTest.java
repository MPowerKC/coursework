import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ShipTest {
	Ship ship;
	
	@BeforeEach
	void setUp() throws Exception {
		
	}

	@Test
	void testBattleShip() {
		ship = new BattleShip();
		assertEquals(8, ship.getLength());
	}

	@Test
	void testBattleCruiser() {
		ship = new BattleCruiser();
		assertEquals(7, ship.getLength());
	}

	@Test
	void testCruiser() {
		ship = new Cruiser();
		assertEquals(6, ship.getLength());
	}

	@Test
	void testLightCruiser() {
		ship = new LightCruiser();
		assertEquals(5, ship.getLength());
	}

	@Test
	void testDestroyer() {
		ship = new Destroyer();
		assertEquals(4, ship.getLength());
	}

	@Test
	void testSubmarine() {
		ship = new Submarine();
		assertEquals(3, ship.getLength());
	}

}
