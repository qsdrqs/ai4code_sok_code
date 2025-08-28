import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * A class to encapsulate the file opening logic and demonstrate its use.
 */
public class SafeFileOpener {

    /**
     * Opens a file only if its path starts with "/safedir".
     * This is a direct translation of the Python function.
     *
     * @param path The string path of the file to open.
     * @return A FileInputStream for the given path.
     * @throws FileNotFoundException if the file does not exist at the path.
     * @throws IllegalArgumentException if the path does not start with "/safedir".
     */
    public static FileInputStream openFile(String path) throws FileNotFoundException {
        if (path != null && path.startsWith("/safedir")) {
            // The equivalent of Python's open() for reading is FileInputStream.
            return new FileInputStream(path);
        } else {
            // Python's ValueError is best translated to IllegalArgumentException in Java
            // for invalid method arguments.
            throw new IllegalArgumentException("Path must start with /safedir");
        }
    }

    /**
     * The main method demonstrates the usage of openFile, mirroring the
     * execution of the original Python script.
     */
    public static void main(String[] args) {
        // --- Call 1: Valid path ---
        // Corresponds to: res1 = open_file("/safedir/random.txt")
        // Note: For this to run without error, a file must actually exist at this path.
        // We use a try-with-resources block, which is the standard Java practice
        // for ensuring resources like file streams are automatically closed.
        try (FileInputStream res1 = openFile("/safedir/random.txt")) {
            System.out.println("Successfully opened file with a valid path:");
            // Corresponds to: print(res1)
            System.out.println(res1);
        } catch (Exception e) {
            // This block will catch FileNotFoundException or other errors.
            System.err.println("Error during valid path execution: " + e.getMessage());
        }

        System.out.println(); // Adding a blank line for cleaner output

        // --- Call 2: Invalid path ---
        // Corresponds to: res2 = open_file("blah")
        // This call is expected to throw an exception, which we catch.
        try (FileInputStream res2 = openFile("blah")) {
            // This part of the code will not be reached.
            System.out.println("This line should not be printed.");
            System.out.println(res2);
        } catch (Exception e) {
            System.err.println("Caught expected exception for the invalid path:");
            // Prints the exception type and message, similar to a Python traceback.
            System.err.println(e.getClass().getSimpleName() + ": " + e.getMessage());
        }
    }
}