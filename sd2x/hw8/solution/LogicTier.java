/*
 * SD2x Homework #8
 * This class represents the Logic Tier in the three-tier architecture.
 * Implement the appropriate methods for this tier below.
 */
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class LogicTier {
	
	private DataTier dataTier; // link to the Data Tier
	
	public LogicTier(DataTier dataTier) {
		this.dataTier = dataTier;
	}
	
	public List<String> findBookTitlesByAuthor(String name) {
		try {
			return dataTier.getAllBooks()
					.stream()
					.filter(book -> book.getAuthor().toLowerCase().contains(name.toLowerCase()))
					.sorted(new BookComparator())
					.map(book -> book.getTitle())
					.collect(Collectors.toList());
		}
		catch (IOException ex) {
			System.out.println("Unable to books from dataTier: " + ex);
			return null;
		}
	}

	public int findNumberOfBooksInYear(int year) {
		try {
			return dataTier.getAllBooks()
					.stream()
					.filter(book -> book.getPublicationYear() == year)
					.sorted(new BookComparator())
					.collect(Collectors.toList())
					.size();
		}
		catch (IOException ex) {
			System.out.println("Unable to books from dataTier: " + ex);
			return 0;
		}
	}
	
	protected class BookComparator implements Comparator<Book> {
		@Override
		public int compare(Book book1, Book book2) {
			return book1.getPublicationYear() == book2.getPublicationYear()
					? book1.getTitle().compareTo(book2.getTitle())
					: book1.getPublicationYear() - book2.getPublicationYear();
		}		
	}
}
