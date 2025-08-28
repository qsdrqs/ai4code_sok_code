import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.InvalidPathException;

/**
 * A utility class for safely accessing files within a designated directory.
 */
public class SafeFileOpener {

    // The safe directory where all file access is restricted.
    // Using Paths.get() makes it easy to work with the Path API.
    private static final Path SAFE_DIR = Paths.get("/safedir");

    /**
     * Opens a file for reading, but only if it resides within the SAFE_DIR.
     * This method prevents directory traversal attacks (e.g., "../..").
     *
     * @param path The string representation of the path to the file.
     * @return A BufferedReader to read the file.
     * @throws IOException if an I/O error occurs, such as the file not being found.
     * @throws SecurityException if the path is outside the SAFE_DIR, indicating a
     *                           potential directory traversal attempt.
     * @throws InvalidPathException if the path string cannot be converted to a Path.
     */
    public static BufferedReader getFile(String path) throws IOException {
        // Convert the input string to a Path object.
        Path filePath = Paths.get(path);

        // Normalize the path to resolve any ".." or "." components.
        // toAbsolutePath() ensures we are comparing absolute paths.
        // normalize() collapses redundant elements.
        Path normalizedPath = filePath.toAbsolutePath().normalize();

        // Security Check: Ensure the normalized path starts with the safe directory's path.
        // This is the equivalent of Python's os.path.normpath(path).startswith(SAFE_DIR)
        if (!normalizedPath.startsWith(SAFE_DIR)) {
            // Throw a security-related exception for this type of error.
            throw new SecurityException("Path must be in SAFE_DIR. Access denied for: " + path);
        }

        // If the check passes, open and return a reader for the file.
        // Files.newBufferedReader is a convenient way to open a text file for reading.
        return Files.newBufferedReader(filePath);
    }

    // Example usage
    public static void main(String[] args) {
        // --- Example of a valid call ---
        try {
            // Assuming /safedir/hello.txt exists and contains "Hello World"
            System.out.println("Attempting to read /safedir/hello.txt...");
            BufferedReader reader = getFile("/safedir/hello.txt");
            System.out.println("Success! File content: " + reader.readLine());
            reader.close();
        } catch (IOException | SecurityException e) {
            System.err.println("Error reading valid file: " + e.getMessage());
        }

        System.out.println("\n---------------------------------\n");

        // --- Example of an invalid (directory traversal) call ---
        try {
            System.out.println("Attempting to read /safedir/../etc/passwd...");
            // This path will be normalized to /etc/passwd, which is outside /safedir
            BufferedReader reader = getFile("/safedir/../../etc/passwd");
            // This line should not be reached
            System.out.println("File content: " + reader.readLine());
            reader.close();
        } catch (IOException | SecurityException e) {
            System.err.println("Caught expected error: " + e.getMessage());
        }
    }
}