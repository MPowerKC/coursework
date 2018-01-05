import java.util.Scanner;

public class BattleshipGame {
	private Ocean ocean;
	private boolean userQuitGame;
	
	public BattleshipGame() {
		ocean = new Ocean();
		ocean.placeAllShipsRandomly();
		userQuitGame = false;
	}
	
	public Ocean getOcean() {
		return this.ocean;
	}
	
	public void start() {
		printGameHeader();
		
		while(!userQuitGame && !ocean.isGameOver()) {
			for(String shot : getUserShots()) {
				if (shot.toLowerCase().equals("q")) {
					userQuitGame = true;
					break;
				}
				
				String[] tokens = shot.split(",");
				if (tokens.length == 2) {
					int row = Integer.parseInt(tokens[0].trim());
					int col = Integer.parseInt(tokens[1].trim());
					if (ocean.shootAt(row, col)) {
						System.out.println("hit");
						Ship ship = ocean.getShipArray()[row][col];
						if(ship.isSunk())
							System.out.println("You just sank a " + ship.getShipType() + ".");
					}
					else 
						System.out.println("miss");
					
					//ocean.print();
				}
				
				if (ocean.getShipsSunk() == Ocean.TOTAL_SHIPS) {
					break;
				}
			}
		}
	}
	
	private void printGameHeader() {
		System.out.println();
		System.out.println(" ========================================================================= ");
		System.out.println("===========================================================================");
		System.out.println("===                           BATTLESHIP                                ===");
		System.out.println("===========================================================================");
		System.out.println(" ========================================================================= ");
		System.out.println();
		System.out.println("Enter your shots in the format {row},{col}, separated by semi-colons(;).");
		System.out.println("For example: 1, 1; 0, 3; 7, 3");
		System.out.println();
	}
	
	public String[] getUserShots() {
		Scanner scanner = null;

		ocean.print();

		System.out.print("Fire away: ");
		
		try {
			scanner = new Scanner(System.in);
			String response = scanner.nextLine();
			
			return response.split(";");
		}
		finally {
			System.out.println();
			//scanner.close();
		}
	}
	
	// =========================================
	// Static methods
	// =========================================

	public static void main(String[] args) {
		boolean continuePlaying = true;
		BattleshipGame game;
		
		while(continuePlaying) {
			game = new BattleshipGame();
			game.start();
			displayGameResults(game);
			
			continuePlaying = promptToPlayAgain();
		}
	}
	
	public static boolean promptToPlayAgain() {
		System.out.print("Do you want to play again? (Y/N) ");
		Scanner scanner = null;
		
		try {
			scanner = new Scanner(System.in);
			String response = scanner.nextLine();
			
			return response.trim().toUpperCase().equals("Y");
		}
		finally {
			System.out.println();
			//in.close();
		}
	}
	
	public static void displayGameResults(BattleshipGame game) {
		game.getOcean().print();
		System.out.println();
		
		if (game.getOcean().getShipsSunk() == Ocean.TOTAL_SHIPS)
			System.out.println("You won, nice work! :D");
		else
			System.out.println("Better luck next time. :|");
		
		System.out.println("Shots fired: " + game.getOcean().getShotsFired());
		System.out.println("Total hits : " + game.getOcean().getHitCount());
		System.out.println("Ships sunk : " + game.getOcean().getShipsSunk());
	}
}
