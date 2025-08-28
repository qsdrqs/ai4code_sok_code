import java.io.FileWriter;
import java.io.IOException;

/**
 * This class contains methods for validating a directory path and creating a file
 * within a safe directory.
 */
public class FileCreator {

    /**
     * Validates if a directory path starts with '/safedir'.
     *
     * @param dir The directory path string to validate.
     * @return true if the path is valid, false otherwise.
     */
    public static boolean validateDir(String dir) {
        // In Java, the startsWith method is called on the String object itself.
        return dir.startsWith("/safedir");
    }

    /**
     * Creates a file if the directory path is valid.
     * Note: In a real-world application, the returned FileWriter should be
     * properly closed in a finally block or using a try-with-resources statement
     * to prevent resource leaks.
     *
     * @param dir The full path of the file to create.
     * @return A FileWriter object if the path is valid, or null otherwise.
     * @throws IOException if an I/O error occurs when creating the file.
     */
    public static FileWriter createFile(String dir) throws IOException {
        boolean valid = validateDir(dir);
        if (!valid) {
            // In Python, a function can return nothing (None).
            // The equivalent in Java is returning null.
            return null;
        }
        // Python's open(path, 'w') is equivalent to creating a new FileWriter.
        // This can throw an IOException, which we declare in the method signature.
        return new FileWriter(dir);
    }

    /**
     * The main entry point for the application.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        // File I/O operations can throw checked exceptions in Java,
        // so we need to wrap the calls in a try-catch block.
        try {
            // This will print 'null' because 'test' is not a valid directory.
            System.out.println(createFile("test"));

            // This will create the file and print the FileWriter object's representation.
            // e.g., java.io.FileWriter@1b6d3586
            System.out.println(createFile("/safedir/testAgain1.txt"));

        } catch (IOException e) {
            System.err.println("An error occurred while creating a file:");
            e.printStackTrace();
        }
    }
}