import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MovieDatabase {
	ArrayList<Movie> movieList;
	ArrayList<Actor> actorList;
	
	public MovieDatabase() {
		movieList = new ArrayList<Movie>();
		actorList = new ArrayList<Actor>();
	}
	
	public ArrayList<Movie> getMovieList() {
		return movieList;
	}
	public ArrayList<Actor> getActorList() {
		return actorList;
	}
	
	public void addMovie(String name, String[] actors) {
		Movie newMovie = new Movie();
		newMovie.setName(name);
		if (!movieList.contains(newMovie)) {
			movieList.add(newMovie);
			for (String actorName : actors) {
				Actor actor = new Actor();
				actor.name = actorName;
				
				int idx = actorList.indexOf(actor);
				if (idx >= 0)
					actor = actorList.get(idx);
				else 
					actorList.add(actor);
				
				actor.getMovies().add(newMovie);
				newMovie.getActors().add(actor);
			}
		}
	}
	
	public void addRating(String name, double rating) {
		Movie movie = new Movie();
		movie.setName(name);
		int idx = movieList.indexOf(movie);
		if (idx >= 0)
			movieList.get(idx).setRating(rating);
	}

	public void updateRating(String name, double rating) {
		Movie movie = new Movie();
		movie.setName(name);
		int idx = movieList.indexOf(movie);
		if (idx >= 0)
			movieList.get(idx).setRating(rating);
	}
	
	public String getBestActor() {
		String bestName = "";
		double bestRating = -1;
		
		for (Actor actor : actorList) {
			int ratingTotal = 0;
			for (Movie movie : actor.getMovies()) {
				ratingTotal += movie.getRating();
			}
			double averageRating = ratingTotal / (double)actor.getMovies().size();
			if (averageRating > bestRating) {
				bestName = actor.getName();
				bestRating = averageRating;
			}
		}
		
		return bestName;
	}
	
	public String getBestMovie() {
		String bestName = "";
		double bestRating = -1;
		
		for (Movie movie : movieList) {
			if (movie.getRating() > bestRating) {
				bestName = movie.getName();
				bestRating = movie.getRating();
			}
		}
		
		return bestName;
	}
	
	public void loadMovies(String fileName) throws FileNotFoundException, IOException {
		BufferedReader reader = new BufferedReader(new FileReader(fileName));
		String line;
		
		try {
			while((line = reader.readLine()) != null) {
				String[] tokens = line.split(",");
				String actorName = "";
				String movieName = "";
				Actor actor = null;
				
				for (int i = 0; i < tokens.length; i++) {
					if (i == 0) {
						actorName = tokens[i].trim();
						actor = new Actor();
						actor.setName(actorName);
						int idx = actorList.indexOf(actor);
						if (idx >= 0) 
							actor = actorList.get(idx);
						else
							actorList.add(actor);
					}
					else {
						movieName = tokens[i].trim();
						Movie movie = new Movie();
						movie.setName(movieName);
						int idx = movieList.indexOf(movie);
						if (idx >= 0)
							movie = movieList.get(idx);
						else {
							movieList.add(movie);
						}
						movie.getActors().add(actor);
						actor.getMovies().add(movie);
					}
				}
			}
		}
		finally {
			if (reader != null)
				reader.close();
		}
	}
	
	public void addMovieRatings(String fileName) throws FileNotFoundException, IOException {
		BufferedReader reader = new BufferedReader(new FileReader(fileName));
		String line;
		
		try {
			while((line = reader.readLine()) != null) {
				String[] tokens = line.split("\t");
				if (tokens.length == 2) {
					String movieName = tokens[0];
					String rating = tokens[1];
					Movie movie = new Movie();
					movie.setName(movieName);
					
					int idx = movieList.indexOf(movie);
					if (idx >= 0) {
						addRating(movieName, Double.parseDouble(rating));
					}
				}
			}
		}
		finally {
			if (reader != null)
				reader.close();
		}
	}
	
	public static void main(String[] args) {
		String moviesFile = "movies.txt";
		String ratingsFile = "ratings.txt";
		
		try {
			MovieDatabase db = new MovieDatabase();
			db.loadMovies(moviesFile);
			db.addMovieRatings(ratingsFile);
			
			System.out.println("Best actor: " + db.getBestActor());
			System.out.println("Best movie: " + db.getBestMovie());
		}
		catch (FileNotFoundException ex) {
			System.out.println("Exception: " + ex);
		}
		catch (IOException ex) {
			System.out.println("Exception: " + ex);
		}
	}
}
