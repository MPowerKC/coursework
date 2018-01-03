import java.util.ArrayList;

public class Movie {
	String name;
	ArrayList<Actor> actors;
	double rating;
	
	public Movie() {
		name = "";
		actors = new ArrayList<Actor>();
		rating = 0;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Actor> getActors() {
		return actors;
	}

	public void setActors(ArrayList<Actor> actors) {
		this.actors = actors;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}
	
	@Override
	public boolean equals(Object obj) {
		Movie other = (Movie)obj;
		return this.name.equals(other.name);
	}
}
