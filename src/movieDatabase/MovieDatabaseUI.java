package movieDatabase;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * A command line user interface for a movie database.
 * @author Gustav Selin
 */
public class MovieDatabaseUI {
    private Scanner _scanner;
    private MovieDatabaseHandler _movieDatabaseHandler;

    /**
     * Construct a MovieDatabaseUI.
     */
    public MovieDatabaseUI() {

    }
    /**
     * Start the movie database UI.
     * @throws IOException
     */
    public void startUI() throws IOException {
        _scanner = new Scanner(System.in);
        _movieDatabaseHandler = new MovieDatabaseHandler();

        int input;
        boolean quit = false;

        System.out.println("** MOVIE DATABASE **");

        while(!quit) {
            input = getNumberInput(_scanner, 1, 4, getMainMenu());

            switch(input) {
                case 1: searchTitle(); break;
                case 2: searchReviewScore(); break;
                case 3: addMovie(); break;
                case 4: quit = true;
            }
        }
        //Close scanner to free resources
        _scanner.close();
    }
    /**
     * Get input and translate it to a valid number.
     *
     * @param scanner the Scanner we use to get input
     * @param min the lowest correct number
     * @param max the highest correct number
     * @param message message to user
     * @return the chosen menu number
     */
    private int getNumberInput(Scanner scanner, int min, int max, String message) {
        int input = -1;

        while(input < 0) {
            System.out.println(message);
            try {
                input = Integer.parseInt(scanner.nextLine().trim());
            }
            catch(NumberFormatException nfe) {
                input = -1;
            }
            if(input < min || input > max) {
                System.out.println("Invalid value");
                input = -1;
            }
        }
        return input;
    }
    /**
     * Get search string from user, search title in the movie
     * database and present the search result.
     * @throws IOException
     */
    private void searchTitle() throws IOException {
        System.out.print("Enter key word: ");
        String input = _scanner.nextLine().trim();

        Movie[] movieArray = getMovieArray();

        for(int i = 0; i < movieArray.length; i++) {
            if(movieArray[i].Title.toLowerCase().contains(input.toLowerCase())) {
                System.out.println("Title: " + movieArray[i].Title + " Review score: " + movieArray[i].ReviewScore + "/5");
            }
        }
    }
    /**
     * Get search string from user, search review score in the movie
     * database and present the search result.
     * @throws IOException
     */
    private void searchReviewScore() throws IOException {
        int reviewInput = getNumberInput(_scanner, 1, 5, "Enter minumum review score (1 - 5): ");

        Movie[] movieArray = getMovieArray();

        for(int i = 0; i < movieArray.length; i++) {
            if(Integer.parseInt(movieArray[i].ReviewScore) >= reviewInput) {
                System.out.println("Title: " + movieArray[i].Title + " Review score: " + movieArray[i].ReviewScore + "/5");
            }
        }
    }

    /**
     * Get information from user on the new movie and add
     * it to the database.
     * @throws IOException
     */
    private void addMovie() throws IOException {
        System.out.print("Title: ");
        String title = _scanner.nextLine().trim();
        int reviewScore = getNumberInput(_scanner, 1, 5, "Review score (1 - 5): ");

        _movieDatabaseHandler.addMovieToDatabase(title, reviewScore);
    }

    /**
     * Reads a text file and saves each line in an array as a Movie object.
     * @return Array of movies.
     * @throws IOException
     */
    private Movie[] getMovieArray() throws IOException {
        List<String> movies = _movieDatabaseHandler.getMovies();

        Movie[] movieArray = new Movie[movies.size()];

        for(int i = 0; i < movies.size(); i++) {
            String[] parts = movies.get(i).split(",");
            Movie movie =  new Movie();
            movie.Title = parts[0];
            movie.ReviewScore = parts[1];
            movieArray[i] = movie;
        }

        return movieArray;
    }

    /**
     * Return the main menu text.
     *
     * @return the main menu text
     */
    private String getMainMenu() {
        return  "-------------------\n" +
                "1. Search title\n" +
                "2. Search review score\n" +
                "3. Add movie\n" +
                "-------------------\n" +
                "4. Close program";
    }
}