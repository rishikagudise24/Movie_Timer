import java.util.List;


public interface BackendInterface {
    /**
     * Constructor for classes implementing BackendInterface.
     *
     * @param collection An instance of IterableMultiKeySortedCollectionInterface
     *                   to be used as the data storage.
     */
    // BackendInterface(IterableMultiKeySortedCollectionInterface collection);

    /**
     * reads data from a CSV file and inserts into a Red-Black Tree
     * @param filePath The path to the CSV file containing the movie data
     * @return true if the data has been read and inserted, false if not
     */
    public boolean readDataFromFile(String filePath);

    /**
     * finds movies with the shortest duration time in the data set 
     * @return list of movies with the shortest duration time in the data set 
     */
    public List<String> getMinimumDuration();

    /**
     * sorts out the movies greater than the maximum duration or
     * shorter than the minimum duration
     * @param minDuration the minimum length of movie in minutes
     * @param maxDuration the maximum length of movie in minutes
     * @return list of movies in between the minimum and maximum duration
     */
    public List<String> getSpecifiedDuration(int minDuration, int maxDuration);
}
