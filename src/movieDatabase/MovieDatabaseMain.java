package movieDatabase;


import java.io.IOException;

/**
 * Entry point for a movie database as part of an assignment
 * in the course Introduction to Programming with Java.
 * @author Gustav Selin
 */
public class MovieDatabaseMain {
    /**
     * Program entry point. Starts the movie database UI.
     *
     * @param args not used
     */
    public static void main(String[] args) throws IOException {
        //Construct and start the UI
       new MovieDatabaseUI().startUI();



    }
}