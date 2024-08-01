import java.util.List;
import java.util.Scanner;

public class FrontendImplementation implements FrontendInterface {

    private BackendInterface backend;
    private Scanner scanner;

    public FrontendImplementation(BackendInterface backend, Scanner scanner){
		this.backend = backend;
		this.scanner = scanner; 
	}


    @Override
    public void startMainCommandLoop() {
        System.out.println("Welcome to Movie Timer!");
	Scanner scanner = new Scanner(System.in);
	while(scanner.hasNextLine()) {
		System.out.println("Enter a command: ");
		System.out.println("1. Specify and load data");
		System.out.println("2. List Movies with the shortest duration");
		System.out.println("3. List Movies between specified durations");
		System.out.println("4. Exit");
		int choice = scanner.nextInt();
		scanner.nextLine();

		switch(choice){
			case 1:
				System.out.println("Enter the file path: ");
				String filePath = scanner.nextLine();
				specifyAndLoadData(filePath);
				break;
			case 2:
				listMoviesShortestDur();
				break;
			case 3:
				System.out.println("enter the minimum duration: ");
				int minDur = scanner.nextInt();
				System.out.println("enter the maximum duration: ");
				int maxDur = scanner.nextInt();
				listMoviesBetweenDuration(minDur, maxDur);
				break;
			case 4:
				exitApp();
				return;
			default:
				System.out.println("Invalid choice! Please try again!");
		}
		
	}



    }

    @Override
    public void specifyAndLoadData(String filePath) {
        boolean success = backend.readDataFromFile(filePath);
	if (success) {
		System.out.println("Data loaded successfully!");
	} else {
		System.out.println("Failed to load data.");
	}
    }

    @Override
    public void listMoviesShortestDur() {
        List<String> movies = backend.getMinimumDuration();
        System.out.println("List of movies with the shortest duration:");
        for (String movie : movies) {
            System.out.println(movie);
        }
    }

    @Override
    public void listMoviesBetweenDuration(int minDuration, int maxDuration) {
        List<String> movies = backend.getSpecifiedDuration(minDuration, maxDuration);
	System.out.println("List of movies between " + minDuration + " and " + maxDuration + " minutes: ");
	for(String movie : movies){
		System.out.println(movie);
	}
    }

    @Override
    public void exitApp() {
	System.out.println("Exiting application.");
//	shouldExit = true;
       // System.exit(0);
    }
}
