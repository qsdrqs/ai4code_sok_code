import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

/**
 * A utility class for safely opening files within a designated directory.
 */
public class FileOpener {

    /**
     * Opens a file after performing security checks to prevent directory traversal.
     * The path must start with "/safedir/" and must not contain "/../".
     *
     * @param filename The path of the file to open.
     * @return a FileWriter object for the specified file if the path is safe, otherwise null.
     * @throws IOException if an I/O error occurs while opening the file.
     */
    public static FileWriter openPath(String filename) throws IOException {
        // In Java, we can use the `startsWith` method to check the beginning of the string.
        // This is equivalent to Python's `filename.find("/safedir/") != 0`.
        if (!filename.startsWith("/safedir/")) {
            return null;
        }

        // Check for directory traversal sequences.
        // The `contains` method is a clean way to check for the presence of a substring.
        // This is equivalent to Python's `filename.find("/../") != -1`.
        if (filename.contains("/../")) { // Safe but overly conservative
            return null;
        }

        // Open the file for writing.
        // `new FileWriter(filename)` is equivalent to Python's `open(filename, 'w')`.
        // It will create the file if it doesn't exist or overwrite it if it does.
        return new FileWriter(filename);
    }

    /**
     * Main method to demonstrate the usage of the openPath function.
     */
    public static void main(String[] args) {
        // Create the safedir directory for the example to run correctly.
        new File("safedir").mkdir();

        // --- Test Case 1: Valid Path ---
        System.out.println("Attempting to open a valid path: /safedir/test_file.txt");
        // Use try-with-resources to ensure the FileWriter is automatically closed.
        try (FileWriter writer = openPath("/safedir/test_file.txt")) {
            if (writer != null) {
                System.out.println("SUCCESS: File opened successfully.");
                writer.write("This is a test.");
            } else {
                System.out.println("FAILURE: File could not be opened due to security checks.");
            }
        } catch (IOException e) {
            System.err.println("ERROR: An I/O exception occurred: " + e.getMessage());
        }

        System.out.println("\n" + "--------------------" + "\n");

        // --- Test Case 2: Invalid Path (outside safedir) ---
        System.out.println("Attempting to open an invalid path: /etc/passwd");
        try (FileWriter writer = openPath("/etc/passwd")) {
            if (writer != null) {
                System.out.println("FAILURE: A file outside /safedir was opened (security flaw!).");
            } else {
                System.out.println("SUCCESS: File opening was correctly blocked.");
            }
        } catch (IOException e) {
            // This block might be reached on systems where creating /etc/passwd is forbidden
            System.err.println("SUCCESS: File opening was correctly blocked by OS permissions or other IO error.");
        }

        System.out.println("\n" + "--------------------" + "\n");

        // --- Test Case 3: Invalid Path (directory traversal) ---
        System.out.println("Attempting to open a traversal path: /safedir/../test_file.txt");
        try (FileWriter writer = openPath("/safedir/../test_file.txt")) {
            if (writer != null) {
                System.out.println("FAILURE: A directory traversal path was opened (security flaw!).");
            } else {
                System.out.println("SUCCESS: File opening was correctly blocked.");
            }
        } catch (IOException e) {
            System.err.println("ERROR: An I/O exception occurred: " + e.getMessage());
        }
    }
}