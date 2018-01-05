import java.util.Random;
import java.util.Set;
import java.util.HashSet;
import java.util.Objects;

public class Ocean {
	public static final int SIZE = 20;
	public static final int TOTAL_SHIPS = 13;
	
	private Ship[][] ships;
	private int shotsFired;
	private int hitCount;
	private int shipsSunk;
	
	private Set<Shot> shots;
	
	class Shot implements Comparable<Shot> {
		private int row;
		private int column;
		
		Shot(int row, int column) {
			this.row = row;
			this.column = column;
		}

		@Override
		public int compareTo(Shot o) {
			return this.row == o.row
				? this.column - o.column
				: this.row - o.row;
		}
		
		@Override
		public boolean equals(Object obj) {
			Shot shot = (Shot)obj;
			return this.row == shot.row &&
				   this.column == shot.column;
		}
		
		@Override
		public int hashCode() {
			return Objects.hash(row, column);
		}
	}
	
	public Ocean() {
		ships = new Ship[SIZE][SIZE];
		shotsFired = 0;
		hitCount = 0;
		shipsSunk = 0;
		
		shots = new HashSet<Shot>();
		
		// Initialize ships grid
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				ships[i][j] = new EmptySea();
			}
		}
	}
	
	public void placeAllShipsRandomly() {
		Random rand = new Random();
		
		placeBattleships(rand, 1);
		placeBattleCruisers(rand, 1);
		placeCruisers(rand, 2);
		placeLightCruisers(rand, 2);
		placeDestroyers(rand, 3);
		placeSubmarines(rand, 4);
	}
	
	private void placeBattleships(Random rand, int totalShips) {
		int count = 0;
		
		while(count < totalShips) {
			if (addShip(rand, new BattleShip()))
				count++;
		}
	}
	
	private void placeBattleCruisers(Random rand, int totalShips) {
		int count = 0;
		
		while(count < totalShips) {
			if (addShip(rand, new BattleCruiser()))
				count++;
		}
	}
	
	private void placeCruisers(Random rand, int totalShips) {
		int count = 0;
		
		while(count < totalShips) {
			if (addShip(rand, new Cruiser()))
				count++;
		}
	}
	
	private void placeLightCruisers(Random rand, int totalShips) {
		int count = 0;
		
		while(count < totalShips) {
			if (addShip(rand, new LightCruiser()))
				count++;
		}
	}
	
	private void placeDestroyers(Random rand, int totalShips) {
		int count = 0;
		
		while(count < totalShips) {
			if (addShip(rand, new Destroyer()))
				count++;
		}
	}
	
	private void placeSubmarines(Random rand, int totalShips) {
		int count = 0;
		
		while(count < totalShips) {
			if (addShip(rand, new Submarine()))
				count++;
		}
	}
	
	private boolean addShip(Random rand, Ship ship) {
		int row = rand.nextInt(Ocean.SIZE);
		int col = rand.nextInt(Ocean.SIZE);
		boolean horizontal = rand.nextBoolean();

		if (ship.okToPlaceShipAt(row, col, horizontal, this)) {
			ship.placeShipAt(row, col, horizontal, this);
			//System.out.println(ship.getShipType() + " at " + row + "," + col + " " + horizontal);
			return true;
		}
		
		return false;
	}
	
	public boolean isOccupied(int row, int column) {
		return !ships[row][column].getShipType().equals("empty");
	}
	
	public boolean shootAt(int row, int column) {
		shotsFired++;
		shots.add(new Shot(row, column));
		
		if (row < 0 || row >= Ocean.SIZE || column < 0 || column >= Ocean.SIZE)
			return false;
		
		if (ships[row][column].shootAt(row, column)) {
			this.hitCount++;
			if (ships[row][column].isSunk()) {
				this.shipsSunk++;				
			}
			return true;
		}
		return false;
	}
	
	public boolean isGameOver() {
		return this.shipsSunk == TOTAL_SHIPS;
	}
	
	public void print() {
		System.out.println("                         == Game Board ==");
		System.out.print("    ");
		for (int i = 0; i < SIZE; i++) {
			System.out.print(String.format("%02d ", i));
		}
		System.out.println();

		System.out.print("    ");
		for (int i = 0; i < SIZE; i++) {
			System.out.print("__ ");
		}
		System.out.println();
		
		for (int i = 0; i < SIZE; i++) {
			System.out.print(String.format("%02d |", i));
			for (int j = 0; j < SIZE; j++) {
				if (!shots.contains(new Shot(i, j)))
					System.out.print(" . ");
				else
					System.out.print(" " + ships[i][j] + " ");
			}
			System.out.println();
		}
	}

	public int getShotsFired() {
		return shotsFired;
	}

	public int getHitCount() {
		return hitCount;
	}

	public int getShipsSunk() {
		return shipsSunk;
	}

	public Ship[][] getShipArray() {
		return ships;
	}
}
