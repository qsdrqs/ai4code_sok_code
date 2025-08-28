import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * A utility class for handling file access with security checks.
 */
public class FileHandler {

    /**
     * Gets a FileInputStream for a given path, but only allows access to files
     * within the "/safedir" directory.
     *
     * Note: The path validation in this method is a direct translation of the
     * original Python code. A more secure implementation should normalize the path
     * to prevent path traversal vulnerabilities (e.g., "/safedir/../some/other/file").
     *
     * @param path The string representing the file path.
     * @return A FileInputStream for the file at the specified path.
     * @throws IllegalArgumentException if the path is invalid (i.e., does not start with "/safedir").
     * @throws FileNotFoundException if the file does not exist at the given path.
     */
    public static FileInputStream getFile(String path) throws FileNotFoundException {
        if (path == null || !path.startsWith("/safedir")) {
            throw new IllegalArgumentException("Invalid path: Access is restricted to the '/safedir' directory.");
        }
        return new FileInputStream(path);
    }

    public static void main(String[] args) {
        // --- Test Case 1: Accessing a valid path ---
        // This is equivalent to the Python call: get_file("/safedir/random.txt")
        System.out.println("Attempting to access a valid path: /safedir/random.txt");
        try (FileInputStream fis = getFile("/safedir/random.txt")) {
            System.out.println("SUCCESS: File stream obtained successfully.");
            // The file stream would be used here.
            // The try-with-resources block ensures fis.close() is called automatically.
        } catch (IllegalArgumentException e) {
            // This block should not be reached for this test case.
            System.err.println("ERROR: Caught unexpected exception: " + e.getMessage());
        } catch (FileNotFoundException e) {
            // This is an expected outcome if the file/directory doesn't actually exist.
            System.err.println("INFO: File not found, which is expected if '/safedir/random.txt' does not exist. " + e.getMessage());
        } catch (IOException e) {
            // Catches other I/O errors.
            System.err.println("ERROR: An I/O error occurred: " + e.getMessage());
        }

        System.out.println("\n----------------------------------------\n");

        // --- Test Case 2: Accessing an invalid path ---
        // This is equivalent to the Python call: get_file("/random.txt")
        System.out.println("Attempting to access an invalid path: /random.txt");
        try {
            getFile("/random.txt");
            // This line should not be reached because getFile is expected to throw an exception.
            System.err.println("ERROR: File access was granted, but should have been denied.");
        } catch (IllegalArgumentException e) {
            // This is the expected outcome.
            System.out.println("SUCCESS: Caught the expected exception for an invalid path.");
            System.out.println("Exception message: " + e.getMessage());
        } catch (FileNotFoundException e) {
            // This block will not be reached because the path check occurs first.
            System.err.println("ERROR: Caught FileNotFoundException, but IllegalArgumentException was expected.");
        }
    }
}