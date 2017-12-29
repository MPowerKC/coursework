/*
 * SD2x Homework #8
 * This class represents the Presentation Tier in the three-tier architecture.
 * Implement the appropriate methods for this tier below. 
 * Also implement the start method as described in the assignment description.
 */
import java.util.Scanner;

public class PresentationTier {
	
	private LogicTier logicTier; // link to the Logic Tier
	private Scanner scanner; 
	
	public PresentationTier(LogicTier logicTier) {
		this.logicTier = logicTier;
		this.scanner = new Scanner(System.in);
	}
	
	public void start() {
		System.out.println("============= Menu =============");
		System.out.println("1. Show book titles by author");
		System.out.println("2. Show number of books in year");
		System.out.print("Enter option: ");
		String option = scanner.nextLine();
		
		switch(option) {
		  case "1":
			  showBookTitlesByAuthor();
			  break;
		  case "2":
			  showNumberOfBooksInYear();
			  break;
		  default:
			  System.out.println("Invalid option");
			  break;			
		}
		scanner.close();
	}
	
	public void showBookTitlesByAuthor() {
		System.out.print("Enter author name: ");
		String author = scanner.nextLine();
		for (String title : logicTier.findBookTitlesByAuthor(author)) {
			System.out.println("Title: " + title);
		}
	}
	
	public void showNumberOfBooksInYear() {
		System.out.print("Enter publication year: ");
		int year = scanner.nextInt();
		System.out.println("Found: " + logicTier.findNumberOfBooksInYear(year));
	}
}
