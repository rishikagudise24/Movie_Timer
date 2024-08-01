import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Scanner;


public class FrontendDeveloperTests {
	@Test
	public void testStartMainCommandLoop() {
	Scanner scanner = new Scanner(System.in);
	String input = "1\n4\n"; // Simulating user typing 'q' to exit
        TextUITester tester = new TextUITester(input);

        // Simulate calling startMainCommandLoop
	BackendImplementationP backend = new BackendImplementationP();
        FrontendImplementation frontend = new FrontendImplementation(backend, scanner); 
        frontend.startMainCommandLoop();

        // Check whether the output matches expectations
        String output = tester.checkOutput();
        assertTrue(output.contains("Welcome"));
        assertTrue(output.contains("Enter a command:"));
//        assertTrue(output.contains("Exiting application."));
	}

	@Test
	public void testSpecifyAndLoadData(){
	Scanner scanner = new Scanner(System.in);
	String input = "movies.csv\n"; // Simulating user specifying a file
        TextUITester tester = new TextUITester(input);

        // Simulate calling specifyAndLoadData
	BackendImplementationP backend = new BackendImplementationP();
        FrontendImplementation frontend = new FrontendImplementation(backend, scanner);
        frontend.specifyAndLoadData("movies.csv");

        // Check whether the output matches expectations
        String output = tester.checkOutput();
        System.out.println(output);
	assertTrue(output.contains("Data loaded successfully!"));

	}

	@Test
	public void testListMoviesBetweenDuration(){
	Scanner scanner = new Scanner(System.in);
	String input = "80\n120\nq\n"; // Simulating user specifying duration thresholds and 'q' to exit
        TextUITester tester = new TextUITester(input);

        // Simulate calling listMoviesBetweenDuration
	BackendImplementationP backend = new BackendImplementationP();
        FrontendImplementation frontend = new FrontendImplementation(backend, scanner);
        frontend.listMoviesBetweenDuration(80, 120);

        // Check whether the output matches expectations
        String output = tester.checkOutput();
        assertTrue(output.contains("List of movies between 80 and 120 minutes:"));
	}

	@Test
	public void testListMoviesShortestDur(){
	Scanner scanner = new Scanner(System.in);
	String input = "q\n"; // Simulating user typing 'q' to exit
        TextUITester tester = new TextUITester(input);

        // Simulate calling listMoviesShortestDur
        BackendImplementationP backend = new BackendImplementationP();
	FrontendImplementation frontend = new FrontendImplementation(backend, scanner);
        frontend.listMoviesShortestDur();

        // Check whether the output matches expectations
        String output = tester.checkOutput();
        assertTrue(output.contains("List of movies with the shortest duration:"));
	}

	@Test
	public void testExitApp(){
	Scanner scanner = new Scanner(System.in);
	String input = "q\n"; // Simulating user typing 'q' to exit
        TextUITester tester = new TextUITester(input);

        // Simulate calling exitApp
        BackendImplementationP backend = new BackendImplementationP();
        FrontendImplementation frontend = new FrontendImplementation(backend, scanner);
        frontend.exitApp();

        // Check whether the output matches expectations
        String output = tester.checkOutput();
        assertTrue(output.contains("Exiting application."));
	}

	@Test
	public void testIntegrationShortDur(){
        Scanner scanner = new Scanner(System.in);
        String input = "q\n"; // Simulating user typing 'q' to exit
        TextUITester tester = new TextUITester(input);

        // Simulate calling listMoviesShortestDur
        BackendInterface backend = new BackendInterface();
        FrontendImplementation frontend = new FrontendImplementation(backend, scanner);
        frontend.listMoviesShortestDur();

        // Check whether the output matches expectations
        String output = tester.checkOutput();
        assertTrue(output.contains("List of movies with the shortest duration:"));
        }



	@Test
        public void testIntegrationListMoviesBetweenDuration(){
        Scanner scanner = new Scanner(System.in);
        String input = "90\n110\nq\n"; // Simulating user specifying duration thresholds and 'q' to exit
        TextUITester tester = new TextUITester(input);

        // Simulate calling listMoviesBetweenDuration
        BackendInterface backend = new BackendInterface();
        FrontendImplementation frontend = new FrontendImplementation(backend, scanner);
        frontend.listMoviesBetweenDuration(90, 110);

        // Check whether the output matches expectations
        String output = tester.checkOutput();
        assertTrue(output.contains("List of movies between 90 and 110 minutes:"));
        }


}

