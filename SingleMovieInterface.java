import java.util.List;

public interface SingleMovieInterface extends Comparable<Movie>{
    //public SingleMovie(String title, String genre, int year, String country, int duration);

    /**
     *
     * @return title of the movie
     */
    public String getTitle();

    /**
     *
     * @return the genre the movie is in
     */
    public String getGenre();

    /**
     *
     * @return the year the movie was produced
     */
    public int getYear();

    /**
     *
     * @return the country the movie was produced
     */
    public String getCountry();

    /**
     *
     * @return duration of movie in minutes
     */
    public int getDuration();

    /**
     *
     * @return a summary of the movie information including the movie title, genre, yaer, country, and duration
     */
    public String summary(); //similar to a toString
}
