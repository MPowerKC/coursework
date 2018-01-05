import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

class OceanTest {
	Ocean ocean;

	@BeforeEach
	void setUp() throws Exception {
		ocean = new Ocean();
	}

	@Test
	void testConstants() {
		assertEquals(20, Ocean.SIZE);
		assertEquals(13, Ocean.TOTAL_SHIPS);
	}

	@Test
	void testConstructor() {
		assertEquals(0, ocean.getHitCount());
		assertEquals(20, ocean.getShipArray().length);
		assertEquals(20, ocean.getShipArray()[0].length);
		assertEquals(0, ocean.getShipsSunk());
		assertEquals(0, ocean.getShotsFired());
		assertEquals(false, ocean.isGameOver());
	}
	
	@Test
	void testPlaceAllShipsRandomly() {
		Set<Ship> ships = new HashSet<Ship>();

		ocean.placeAllShipsRandomly();
		
		for (int i = 0; i < Ocean.SIZE; i++) {
			for (int j = 0; j < Ocean.SIZE; j++) {
				Ship ship = ocean.getShipArray()[i][j];
				assertTrue(ship.getLength() > 0);

				if (!ship.getShipType().equals("empty")) {
					assertTrue(ship.getBowRow() >= 0 && ship.getBowRow() < Ocean.SIZE);
					assertTrue(ship.getBowColumn() >= 0 && ship.getBowColumn() < Ocean.SIZE);
					ships.add(ship);
				}
			}
		}
		
		assertEquals(Ocean.TOTAL_SHIPS, ships.size());
	}
	
	@Test
	void testShootAt() {
		ocean.shootAt(0, 0);
		ocean.shootAt(0, 1);
		ocean.shootAt(0, 2);
		ocean.shootAt(1, 18);
		ocean.shootAt(1, 19);
		
		assertEquals(5, ocean.getShotsFired());
		assertEquals(0, ocean.getHitCount());
	}
}
