/*
 * SD2x Homework #8
 * This class represents the Data Tier in the three-tier architecture.
 * Implement the appropriate methods for this tier below.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataTier {
	
	private String fileName; // the name of the file to read
	
	public DataTier(String inputSource) {
		fileName = inputSource;
	}
	
	public List<Book> getAllBooks() throws IOException {
		List<Book> books = new ArrayList<Book>();
		String line;
		BufferedReader reader = null;
		
		try {
			reader = new BufferedReader(new FileReader(fileName));
			while ((line = reader.readLine()) != null) {
				books.add(parseLine(line));
			}
		}
		catch (Exception ex) {
			System.out.println("Error in DataTier.getAllBooks: " + ex);
		}
		finally {
			if (reader != null)
				reader.close();
		}
		
		return books;
	}
	
	protected Book parseLine(String line) {
		String[] tokens = line.split("\t");
		return new Book(cleanStr(tokens[0]), cleanStr(tokens[1]), cleanInt(tokens[2]));
	}
	
	protected String cleanStr(String str) {
		return str;
	}
	
	protected int cleanInt(String str) {
		return Integer.parseInt(str);
	}
}
