//import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Squarelotron {
	public int[][] squarelotron;
	public int size;
	
	class MatrixPoint {
		int x;
		int y;
		
		MatrixPoint(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public Squarelotron(int size) {
		int value = 0;
		this.squarelotron = new int[size][size];

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				squarelotron[i][j] = ++value;
			}
		}
		
		this.size = size;
	}
	
	public Squarelotron upsideDownFlip(int ring) {
		Squarelotron tron = new Squarelotron(this.size);
		
		int top = ring - 1;
		int bottom = this.size - ring;
		int left = ring - 1;
		int right = this.size - ring;
		
		for (int i = 0; i < this.size; i++) {
			for (int j = 0; j < this.size; j++) {
				if (i < top || i > bottom || j < left || j > right ||
						(i > top && i < bottom && j > left && j < right)) {
					tron.squarelotron[i][j] = this.squarelotron[i][j];
				}
				else if (i == top) {
					tron.squarelotron[i][j] = this.squarelotron[bottom][j];
				}
				else if (i == bottom) {
					tron.squarelotron[i][j] = this.squarelotron[top][j];
				}
				else if (j == left || j == right) {
					int idx = bottom - (i - top);
					tron.squarelotron[i][j] = this.squarelotron[idx][j];					
				}
			}
		}
		return tron;
	}
	
	public Squarelotron mainDiagonalFlip(int ring) {
		Squarelotron tron = new Squarelotron(this.size);
		
		int top = ring - 1;
		int bottom = this.size - ring;
		int left = ring - 1;
		int right = this.size - ring;
		
		for (int i = 0; i < this.size; i++) {
			for (int j = 0; j < this.size; j++) {
				if (i < top || i > bottom || j < left || j > right ||
						(i > top && i < bottom && j > left && j < right)) {
					tron.squarelotron[i][j] = this.squarelotron[i][j];
				}
				else if (i == top) {
					tron.squarelotron[i][j] = this.squarelotron[i + (j -left)][left];
				}
				else if (i == bottom) {
					tron.squarelotron[i][j] = this.squarelotron[i + (j - right)][right];
				}
				else if (j == left) {
					int idx = left + (i - top);
					tron.squarelotron[i][j] = this.squarelotron[top][idx];					
				}
				else if (j == right) {
					int idx = right + (i - bottom);
					tron.squarelotron[i][j] = this.squarelotron[bottom][idx];					
				}
			}
		}
		return tron;
	}
	
	public void rotateRight(int numberOfTurns) {
		if (numberOfTurns == 0) return;
		
		ArrayList<MatrixPoint> points;
		int rings = (int)Math.ceil(this.size / 2.0);
		
		for (int ring = 0; ring < rings; ring++) {
			int top = ring;
			int bottom = this.size - ring - 1;
			int left = ring;
			int right = this.size - ring - 1;
			
			for (int loop = 0; loop < Math.abs(numberOfTurns); loop++) {
				for (int j = left; j < right; j++) {
					points = new ArrayList<MatrixPoint>();
					int offset = j - left;

					points.add(new MatrixPoint(ring, j));
					points.add(new MatrixPoint(top + offset, right));
					points.add(new MatrixPoint(bottom, right - offset));
					points.add(new MatrixPoint(bottom - offset, left));
					if (numberOfTurns < 0) Collections.reverse(points);
					
					rotate(points);
				}	
			}
		}		
	}
	
	private void rotate(ArrayList<MatrixPoint> points) {
		MatrixPoint prevPoint = points.get(points.size() - 1);
		int prevVal = this.squarelotron[prevPoint.x][prevPoint.y];
		for (int i = 0; i < points.size(); i++) {
			MatrixPoint point = points.get(i);
			int temp = this.squarelotron[point.x][point.y];
			this.squarelotron[point.x][point.y] = prevVal;
			prevVal = temp;
		}
	}
	
	public void print() {
		for (int i = 0; i < this.size; i++) {
			for (int j = 0; j < this.size; j++) {
				System.out.print(String.format("%3s", this.squarelotron[i][j]));
			}
			System.out.println();
		}
	}
}
