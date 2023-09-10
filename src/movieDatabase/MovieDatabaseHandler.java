package movieDatabase;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * A handler to read and write from a movie database.
 * @author Gustav Selin
 */
public class MovieDatabaseHandler {

    /**
     * Adds a new movie to the database
     * @param title
     * @param reviewScore
     * @throws IOException
     */
    public void addMovieToDatabase(String title, int reviewScore) throws IOException {
        Path path = getDatabasePath();
        List<String> movies = getMovies();
        movies.add(title + "," + reviewScore);
        Files.write(path, movies);

        System.out.println("Movie was added to library!");
    }

    /**
     * Get a list of movies from database.
     * @return A list of movies.
     * @throws IOException
     */
    public List<String> getMovies() throws IOException {
        Path path = getDatabasePath();

        List<String> movies = Files.readAllLines(path);

        return movies;
    }

    /**
     * Get the path to the movie library.
     * @return the path.
     * @throws IOException
     */
    public Path getDatabasePath() throws IOException {
        Path path = Paths.get("MovieDatabase.txt");

        if(!Files.exists(path)) {
            Files.createFile(path);
        }

        return path;
    }
}
