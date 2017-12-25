/*
 * SD2x Homework #5
 * Implement the method below according to the specification in the assignment description.
 * Please be sure not to change the method signature!
 */

import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class MovieRatingsParser {

	public static TreeMap<String, PriorityQueue<Integer>> parseMovieRatings(List<UserMovieRating> allUsersRatings) {
		TreeMap<String, PriorityQueue<Integer>> movieRatings = new TreeMap<String, PriorityQueue<Integer>>();
		
		if (allUsersRatings == null || allUsersRatings.isEmpty()) return movieRatings;
		
		for(UserMovieRating rating : allUsersRatings) {
			if (rating == null || rating.movie == null || rating.movie.length() == 0 || rating.userRating < 0) {
				continue;
			}
			
			if (movieRatings.containsKey(rating.movie.toLowerCase())) {
				PriorityQueue<Integer> ratingsQueue = movieRatings.get(rating.movie.toLowerCase());
				if (!ratingsQueue.contains(rating.userRating))
						ratingsQueue.add(rating.userRating);
			}
			else {
				PriorityQueue<Integer> ratingsQueue = new PriorityQueue<Integer>();
				ratingsQueue.add(rating.userRating);
				movieRatings.put(rating.movie.toLowerCase(), ratingsQueue);
			}
		}
		
		return movieRatings;
	}

}
