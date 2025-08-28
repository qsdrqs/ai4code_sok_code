import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * This class contains functionality to search for a file in a given path.
 * It is a Java translation of the provided Python script.
 */
public class FileSearcher {

    /**
     * Searches for a file in a directory tree starting from "/safedir" using a regular expression.
     * This method mimics the behavior of the Python script which uses os.walk() and re.match().
     *
     * @param fileNameRegex The regular expression pattern to match against the file name.
     *                      The match is performed against the beginning of the filename,
     *                      similar to Python's re.match().
     * @return The full path of the first matching file as a String, or null if no file is found.
     */
    public static String searchFileRe(String fileNameRegex) {
        Path startPath = Paths.get("/safedir");
        Pattern pattern = Pattern.compile(fileNameRegex);

        // Using a try-with-resources statement to ensure the stream is closed automatically.
        // Files.walk() provides a Stream<Path> that lazily traverses the directory tree,
        // which is the modern Java equivalent of Python's os.walk().
        try (Stream<Path> paths = Files.walk(startPath)) {
            Optional<Path> foundPath = paths
                // Filter to only include regular files, not directories.
                .filter(Files::isRegularFile)
                // Filter files based on the regex pattern.
                .filter(path -> {
                    // Get the file name from the path.
                    String fileName = path.getFileName().toString();
                    // Use matcher.lookingAt() which is the direct Java equivalent of Python's re.match().
                    // It checks if the pattern matches from the beginning of the string.
                    Matcher matcher = pattern.matcher(fileName);
                    return matcher.lookingAt();
                })
                // Find the first element in the stream that matches all criteria.
                // This is more efficient as it stops searching once a match is found.
                .findFirst();

            // If a path was found, convert it to a string. Otherwise, return null.
            return foundPath.map(Path::toString).orElse(null);

        } catch (IOException e) {
            // Handle potential I/O errors during file system traversal.
            System.err.println("Error walking the file tree: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    /**
     * The main function to execute the file search.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        // Call the search function with the target file name pattern.
        String filePath = searchFileRe("test12.txt");
        
        // Print the result to the console.
        // System.out.println will print "null" if the filePath variable is null.
        System.out.println(filePath);
    }
}