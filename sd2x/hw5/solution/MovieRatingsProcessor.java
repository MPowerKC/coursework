/*
 * SD2x Homework #5
 * Implement the methods below according to the specification in the assignment description.
 * Please be sure not to change the method signatures!
 */

import java.util.List;
import java.util.Map.Entry;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.TreeMap;


public class MovieRatingsProcessor {

	public static List<String> getAlphabeticalMovies(TreeMap<String, PriorityQueue<Integer>> movieRatings) {
		List<String> movies = new ArrayList<String>();
		
		if (movieRatings == null || movieRatings.isEmpty()) return movies;
		
		for (String movie : movieRatings.keySet()) {
			movies.add(movie);
		}
		return movies;
	}

	public static List<String> getAlphabeticalMoviesAboveRating(TreeMap<String, PriorityQueue<Integer>> movieRatings, int rating) {
		List<String> movies = new ArrayList<String>();
		
		if (movieRatings == null || movieRatings.isEmpty()) return movies;
		
		for (Entry<String, PriorityQueue<Integer>> entry : movieRatings.entrySet()) {
			if (entry.getValue().peek() > rating)
				movies.add(entry.getKey());
		}
		
		return movies;
	}
	
	public static TreeMap<String, Integer> removeAllRatingsBelow(TreeMap<String, PriorityQueue<Integer>> movieRatings, int rating) {
		TreeMap<String, Integer> removed = new TreeMap<String, Integer>();
		List<String> removeFromMap = new ArrayList<String>();
		
		try {
			if (movieRatings == null || movieRatings.isEmpty()) return removed;
			
			for (Entry<String, PriorityQueue<Integer>> entry : movieRatings.entrySet()) {
				String movie = entry.getKey();
				PriorityQueue<Integer> ratings = entry.getValue();
				
				while(!ratings.isEmpty() && ratings.peek() < rating) {
					ratings.remove();
					if (removed.containsKey(movie)) {
						int current = removed.get(movie);
						removed.put(movie, current + 1);
					}
					else {
						removed.put(movie, 1);
					}
				}
				
				if (ratings == null || ratings.isEmpty()) {
					removeFromMap.add(movie);
				}
			}
			
			for(String movie : removeFromMap) {
				movieRatings.remove(movie);
			}
			
			return removed;
		}
		catch (Exception ex) {
			System.out.println(ex);
			return null;
		}
	}
}
