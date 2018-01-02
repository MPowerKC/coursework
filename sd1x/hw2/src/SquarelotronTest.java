import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SquarelotronTest {
	static int SIZE = 4;
	Squarelotron tron;

	@BeforeEach
	void setUp() throws Exception {
		tron = new Squarelotron(SIZE);
	}

	@Test
	void testConstructor() {
		assertNotNull(tron);
		assertNotNull(tron.squarelotron);
		assertEquals(SIZE, tron.size);
		assertEquals(SIZE, tron.squarelotron.length);
		assertEquals(SIZE, tron.squarelotron[0].length);
	}

	@Test
	void testUpsideDownFlip() {
		//System.out.println("Before upsideDownFlip: ");
		//tron.print();
		Squarelotron upd = tron.upsideDownFlip(1);
		//System.out.println("After upsideDownFlip: ");
		//upd.print();

		assertNotNull(upd);
		assertNotNull(upd.squarelotron);
		assertEquals(SIZE, upd.size);
		assertEquals(SIZE, upd.squarelotron.length);
		assertEquals(SIZE, upd.squarelotron[0].length);
		
		assertEquals(13, upd.squarelotron[0][0]);
		assertEquals(14, upd.squarelotron[0][1]);
		assertEquals(15, upd.squarelotron[0][2]);
		assertEquals(16, upd.squarelotron[0][3]);

		assertEquals(9, upd.squarelotron[1][0]);
		assertEquals(12, upd.squarelotron[1][3]);

		assertEquals(5, upd.squarelotron[2][0]);
		assertEquals(8, upd.squarelotron[2][3]);

		assertEquals(1, upd.squarelotron[3][0]);
		assertEquals(2, upd.squarelotron[3][1]);
		assertEquals(3, upd.squarelotron[3][2]);
		assertEquals(4, upd.squarelotron[3][3]);
	}

	@Test
	void testMainDiagonalFlip() {
		//System.out.println("Before mainDiagonalFlip: ");
		//tron.print();
		Squarelotron upd = tron.mainDiagonalFlip(1);
		//System.out.println("After mainDiagonalFlip: ");
		//upd.print();

		assertNotNull(upd);
		assertNotNull(upd.squarelotron);
		assertEquals(SIZE, upd.size);
		assertEquals(SIZE, upd.squarelotron.length);
		assertEquals(SIZE, upd.squarelotron[0].length);
		
		assertEquals(1, upd.squarelotron[0][0]);
		assertEquals(5, upd.squarelotron[0][1]);
		assertEquals(9, upd.squarelotron[0][2]);
		assertEquals(13, upd.squarelotron[0][3]);

		assertEquals(2, upd.squarelotron[1][0]);
		assertEquals(14, upd.squarelotron[1][3]);

		assertEquals(3, upd.squarelotron[2][0]);
		assertEquals(15, upd.squarelotron[2][3]);

		assertEquals(4, upd.squarelotron[3][0]);
		assertEquals(8, upd.squarelotron[3][1]);
		assertEquals(12, upd.squarelotron[3][2]);
		assertEquals(16, upd.squarelotron[3][3]);	
	}

	@Test
	void testRotateRightBy1() {
		tron.rotateRight(1);

		assertNotNull(tron);
		assertNotNull(tron.squarelotron);
		assertEquals(SIZE, tron.size);
		assertEquals(SIZE, tron.squarelotron.length);
		assertEquals(SIZE, tron.squarelotron[0].length);
		
		assertEquals(13, tron.squarelotron[0][0]);
		assertEquals(9, tron.squarelotron[0][1]);
		assertEquals(5, tron.squarelotron[0][2]);
		assertEquals(1, tron.squarelotron[0][3]);

		assertEquals(14, tron.squarelotron[1][0]);
		assertEquals(2, tron.squarelotron[1][3]);

		assertEquals(15, tron.squarelotron[2][0]);
		assertEquals(3, tron.squarelotron[2][3]);

		assertEquals(16, tron.squarelotron[3][0]);
		assertEquals(12, tron.squarelotron[3][1]);
		assertEquals(8, tron.squarelotron[3][2]);
		assertEquals(4, tron.squarelotron[3][3]);	
	}

	@Test
	void testRotateRightBy4() {
		tron.rotateRight(4);

		assertNotNull(tron);
		assertNotNull(tron.squarelotron);
		assertEquals(SIZE, tron.size);
		assertEquals(SIZE, tron.squarelotron.length);
		assertEquals(SIZE, tron.squarelotron[0].length);
		
		assertEquals(1, tron.squarelotron[0][0]);
		assertEquals(2, tron.squarelotron[0][1]);
		assertEquals(3, tron.squarelotron[0][2]);
		assertEquals(4, tron.squarelotron[0][3]);

		assertEquals(5, tron.squarelotron[1][0]);
		assertEquals(8, tron.squarelotron[1][3]);

		assertEquals(9, tron.squarelotron[2][0]);
		assertEquals(12, tron.squarelotron[2][3]);

		assertEquals(13, tron.squarelotron[3][0]);
		assertEquals(14, tron.squarelotron[3][1]);
		assertEquals(15, tron.squarelotron[3][2]);
		assertEquals(16, tron.squarelotron[3][3]);	
	}

	@Test
	void testRotateRightByNegative1() {
		tron.rotateRight(-1);

		assertNotNull(tron);
		assertNotNull(tron.squarelotron);
		assertEquals(SIZE, tron.size);
		assertEquals(SIZE, tron.squarelotron.length);
		assertEquals(SIZE, tron.squarelotron[0].length);
		
		assertEquals(4, tron.squarelotron[0][0]);
		assertEquals(8, tron.squarelotron[0][1]);
		assertEquals(12, tron.squarelotron[0][2]);
		assertEquals(16, tron.squarelotron[0][3]);

		assertEquals(3, tron.squarelotron[1][0]);
		assertEquals(15, tron.squarelotron[1][3]);

		assertEquals(2, tron.squarelotron[2][0]);
		assertEquals(14, tron.squarelotron[2][3]);

		assertEquals(1, tron.squarelotron[3][0]);
		assertEquals(5, tron.squarelotron[3][1]);
		assertEquals(9, tron.squarelotron[3][2]);
		assertEquals(13, tron.squarelotron[3][3]);	
	}

	@Test
	void testRotateRightByNegative4() {
		tron.rotateRight(-4);

		assertNotNull(tron);
		assertNotNull(tron.squarelotron);
		assertEquals(SIZE, tron.size);
		assertEquals(SIZE, tron.squarelotron.length);
		assertEquals(SIZE, tron.squarelotron[0].length);
		
		assertEquals(1, tron.squarelotron[0][0]);
		assertEquals(2, tron.squarelotron[0][1]);
		assertEquals(3, tron.squarelotron[0][2]);
		assertEquals(4, tron.squarelotron[0][3]);

		assertEquals(5, tron.squarelotron[1][0]);
		assertEquals(8, tron.squarelotron[1][3]);

		assertEquals(9, tron.squarelotron[2][0]);
		assertEquals(12, tron.squarelotron[2][3]);

		assertEquals(13, tron.squarelotron[3][0]);
		assertEquals(14, tron.squarelotron[3][1]);
		assertEquals(15, tron.squarelotron[3][2]);
		assertEquals(16, tron.squarelotron[3][3]);	
	}

}
