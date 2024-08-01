import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.List;

public class BackendDeveloperTests {

    @Test
    public void testReadDataFromFile() {
	//implementation
	BackendInterface backend = new PlaceholderBackend();
	if(!backend.readDataFromFile("test.csv")) {
	    Assertions.fail("did not read file");
	}
    }

    @Test
    public void testGetMinimumDuration() {
        //implementation
	BackendInterface backend = new PlaceholderBackend();
	
	List<String> result = backend.getMinimumDuration(90);
	if(result.size() != 2) {
	    Assertions.fail("size incorrect");
	}
	if(!result.contains("Movie1")) {
	    Assertions.fail("did not find the right movie");
	}
	if(!result.contains("Movie2")) {
	    Assertions.fail("did not find the right movie.");
	}
    }

    @Test
    public void testGetSpecifiedDuration() {
        //implementation
	BackendInterface backend = new PlaceholderBackend();
	
	List<String> result = backend.getSpecifiedDuration(60,130);
	if(result.size() != 2) {
	    Assertions.fail("incorrect size");
	}
	if(!result.contains("Movie1")){
	    Assertions.fail("did not find the right movie.");
	}
	if(!result.contains("Movie2")){
	    Assertions.fail("did not find the right movie.");
        }
    }

    @Test
    public void testSingleMovieGetters() {
        //implementation
	SingleMovieInterface movie = new PlaceholderSingleMovie();
	
	if(!movie.getTitle().equals("MovieTitle")) {
	    Assertions.fail("incorrect movie title");
	}
	if(!movie.getGenre().equals("Comedy")) {
            Assertions.fail("incorrect genre");
        }
	if(movie.getYear() != 2022) {
            Assertions.fail("incorrect production year");
        }
	if(!movie.getCountry().equals("USA")) {
            Assertions.fail("incorrect Country production");
        }
	if(movie.getDuration() != 140); {
	    Assertions.fail("incorrect movie duration");
	}
	
    }

    @Test
    public void testSingleMovieSummary() {
        //implementation
	SingleMovieInterface movie = new PlaceholderSingleMovie();
	if(!movie.summary().equals("Title - Action - 2019 - USA - 120 minutes")){
	    Assertions.fail("incorrect movie summary");
	}
    }
}
