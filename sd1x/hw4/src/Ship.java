
public abstract class Ship {
	private int bowRow;
	private int bowColumn;
	private int length;
	private boolean horizontal;
	private boolean[] hit;
	
	public int getBowRow() {
		return bowRow;
	}
	public void setBowRow(int bowRow) {
		this.bowRow = bowRow;
	}
	public int getBowColumn() {
		return bowColumn;
	}
	public void setBowColumn(int bowColumn) {
		this.bowColumn = bowColumn;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public boolean isHorizontal() {
		return horizontal;
	}
	public void setHorizontal(boolean horizontal) {
		this.horizontal = horizontal;
	}
	public boolean[] getHit() {
		return hit;
	}
	public void setHit(boolean[] hit) {
		this.hit = hit;
	}
	
	public abstract String getShipType();
	
	public boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean) {
		// Ship extends off right side of ocean
		if (horizontal && column > (Ocean.SIZE - this.length)) {
			return false;
		}
		
		// Ship extends off lower side of ocean
		if (!horizontal && row > (Ocean.SIZE - this.length)) {
			return false;
		}
		
		// Check neighboring ships
		if (horizontal) {
			for (int i = Math.max(0, row - 1); i < Math.min(Ocean.SIZE, row + 2); i++) {
				for (int j = Math.max(0, column - 1); j < Math.min(Ocean.SIZE, column + this.length + 1); j++) {
					if (!ocean.getShipArray()[i][j].getShipType().equals("empty"))
						return false;
				}
			}
		}
		else {
			for (int i = Math.max(0, row - 1); i < Math.min(Ocean.SIZE, row + this.length + 1); i++) {
				for (int j = Math.max(0, column - 1); j < Math.min(Ocean.SIZE, column + 2); j++) {
					if (!ocean.getShipArray()[i][j].getShipType().equals("empty"))
						return false;
				}
			}
		}
		
		return true;
	}
	
	public void placeShipAt(int row, int column, boolean horizontal, Ocean ocean) {
		this.bowRow = row;
		this.bowColumn = column;
		this.horizontal = horizontal;
		
		if (horizontal) {
			for (int j = column; j <= column + this.length - 1; j++) {
				ocean.getShipArray()[row][j] = this;
			}
		}
		else {
			for (int i = row; i <= row + this.length - 1; i++) {
				ocean.getShipArray()[i][column] = this;
			}			
		}
	}
	
	public boolean shootAt(int row, int column) {
		if (this.horizontal) {
			if (this.bowRow != row)
				return false;
			else if (column < this.bowColumn || column >= this.bowColumn + this.length) {
				return false;
			}
		}
		else {
			if (this.bowColumn != column)
				return false;
			else if (row < this.bowRow || row >= this.bowRow + this.length) {
				return false;
			}
		}
		
		if (!isSunk()) {
			if (horizontal)
				this.hit[column - bowColumn] = true;
			else
				this.hit[row - bowRow] = true;
			return true;
		}
		return false;
	}
	
	public boolean isSunk() {
		for (int i = 0; i < this.length; i++) {
			if (!this.hit[i])
				return false;
		}
		return true;
	}
	
	@Override
	public String toString() {
		return isSunk() ? "x" : "S";
	}
}
