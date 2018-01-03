import java.util.ArrayList;

public class Actor {
	String name;
	ArrayList<Movie> movies;
	
	public Actor() {
		name = "";
		movies = new ArrayList<Movie>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Movie> getMovies() {
		return movies;
	}

	public void setMovies(ArrayList<Movie> movies) {
		this.movies = movies;
	}
	
	@Override
	public boolean equals(Object obj) {
		Actor other = (Actor)obj;
		return this.name.equals(other.name);
	}

}
