public interface FrontendInterface{

    /**
     * Constructor
     * Parameters: BackendInterface, Scanner
     * IndividualFrontendInterface(BackendInterface backend, Scanner scanner); 
     **/

    /**
     * Method to start the Command Loop.
     **/
    public void startMainCommandLoop();


    /**
     * Method to specify and load a data file.
     * @params: filePath the path to the data file.
     **/
    public void specifyAndLoadData(String filePath); 
    
    /**
     * Method to list the movies with the shortest duration in the data set.
     **/
    public void listMoviesShortestDur(); 

    /**
     * Method to list movies wiht a duration between specified thresholds.
     * @params: minDuration - minimum duration threshold.
     * @params: maxDuration - max duration threshold
     **/
    public void listMoviesBetweenDuration(int minDuration, int maxDuration); 

    /**
     * Method to exit the application.
     **/
    public void exitApp(); 
}
