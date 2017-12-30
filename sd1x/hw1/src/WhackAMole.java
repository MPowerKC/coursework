import java.util.Random;
import java.util.Scanner;

public class WhackAMole {
	private static int MAX_ATTEMPTS = 50;
	private static int DEFAULT_GRID_SIZE = 10;
	private static int TOTAL_MOLES = 10;
	private static char EMPTY_CELL = '*';
	private static char MOLE_CELL = 'M';
	private static char WHACK_CELL = 'W';
	
	public static void main(String[] args) { 
		boolean exitRequested = false;
		Scanner scanner = new Scanner(System.in);
		WhackAMole game = new WhackAMole(WhackAMole.MAX_ATTEMPTS, WhackAMole.DEFAULT_GRID_SIZE);
		game.loadRandomMoles(WhackAMole.TOTAL_MOLES);
		
		System.out.println("========================================================");
		System.out.println("===                   WHACK A MOLE                  ====");
		System.out.println("========================================================");
		System.out.println();
		System.out.println("You have a maximum of 50 attempts to get all the moles.");
		System.out.println("Moles that have been whacked will display as 'W'");
		System.out.println("Enter each guess as a set of x,y coordinates");
		System.out.println("Enter coordinates -1,-1 to exit the game");
		System.out.println();
		
		while(game.getAttemptsLeft() > 0 
				&& game.getMolesLeft() > 0
				&& !exitRequested) {
			game.printGridToUser();
			
			System.out.print("Enter your guess: ");
			String guess = scanner.nextLine();
			String[] tokens = guess.split(",");
			if (tokens.length != 2) {
				System.out.println("Sorry, that's an invalid guess.");
				System.out.println("Please enter your guess as a set of x,y coordinates.");
			}
			else {
				try {
					int x = Integer.parseInt(tokens[0]);
					int y = Integer.parseInt(tokens[1]);
					
					if (x == -1 && y == -1) {
						exitRequested = true;
					}
					else {
						game.whack(x,  y);						
					}
				}
				catch (NumberFormatException ex) {
					System.out.println("Sorry, that's an invalid guess.");
					System.out.println("Please enter your guess as a set of x,y coordinates.");
				}
			}
		}

		scanner.close();
		
		if (exitRequested) {
			System.out.println("Too bad you didn't stick with it.  Here are the remaing moles.");
			game.printGrid();			
		}
		else if (game.getMolesLeft() == 0) {
			System.out.println("Great job, you won!");
		}
		else {
			System.out.println("Sorry, you ran out of attempts. You didn't win this time.");
			game.printGrid();
		}
	}
	/*
	private int score;
	private int molesLeft;
	private int attemptsLeft;
	private char[][] moleGrid;
	*/
	
	public int score;
	public int molesLeft;
	public int attemptsLeft;
	public char[][] moleGrid;

	
	public WhackAMole(int numAttempts, int gridDimension) {
		score = 0;
		molesLeft = 0;
		attemptsLeft = numAttempts;
		moleGrid = new char[gridDimension][gridDimension];
		
		for (int i = 0; i < moleGrid.length; i++) {
			for (int j = 0; j < moleGrid[i].length; j++) {
				moleGrid[i][j] = WhackAMole.EMPTY_CELL;
			}
		}
	}
	
	public int getScore() {
		return score;
	}
	
	public int getMolesLeft() {
		return molesLeft;
	}
	
	public int getAttemptsLeft() {
		return attemptsLeft;
	}
	
	public boolean place(int x, int y) {
		if (moleGrid[x][y] == WhackAMole.MOLE_CELL)
			return false;

		moleGrid[x][y] = WhackAMole.MOLE_CELL;
		molesLeft++;
		return true;
	}
	
	public void whack(int x, int y) {
		if (moleGrid[x][y] == WhackAMole.MOLE_CELL) {
			moleGrid[x][y] = WhackAMole.WHACK_CELL;
			molesLeft--;
			score++;
		}
		
		attemptsLeft--;
	}
	
	public void printGridToUser() {
		System.out.println("Score: " + score);
		System.out.println("----- Mole Grid -----");

		for (int i = 0; i < moleGrid.length; i++) {
			for (int j = 0; j < moleGrid[i].length; j++) {
				char valueToPrint = moleGrid[i][j] == WhackAMole.WHACK_CELL 
						? moleGrid[i][j] 
						: WhackAMole.EMPTY_CELL;
				System.out.print(valueToPrint + " ");
			}
			System.out.println();
		}
	}

	public void printGrid() {
		System.out.println("Score: " + score);
		System.out.println("----- Mole Grid -----");

		for (int i = 0; i < moleGrid.length; i++) {
			for (int j = 0; j < moleGrid[i].length; j++) {				
				System.out.print(moleGrid[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public void loadRandomMoles(int totalMoles) {
		Random rand = new Random();
		
		while(molesLeft < totalMoles) {
			int x = rand.nextInt(moleGrid.length);
			int y = rand.nextInt(moleGrid.length);
			this.place(x, y);
		}
	}
}
