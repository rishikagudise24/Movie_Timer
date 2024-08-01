public class SingleMovieImplementation implements SingleMovieInterface {

    private String title;
    private String genre;
    private int year;
    private String country;
    private int duration;

    public SingleMovie(String title, String genre, int year, String country, int duration) {
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.country = country;
        this.duration = duration;
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public String getGenre() {
        return this.genre;
    }

    @Override
    public int getYear() {
        return this.year;
    }

    @Override
    public String getCountry() {
        return this.country;
    }

    @Override
    public int getDuration() {
        return this.duration;
    }

    @Override
    public String summary() {
        return "Title: " + this.title +
                ", Genre: " + this.genre +
                ", Year: " + this.year +
                ", Country: " + this.country +
                ", Duration: " + this.duration + " minutes";
    }
}
