import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * A utility class for handling file access with security checks.
 */
public class FileHandler {

    /**
     * Opens a file at the given path, but only if it's within a safe directory.
     *
     * @param path The path to the file.
     * @return A FileInputStream for the file if the path is valid and the file exists,
     *         otherwise null if the path is not allowed.
     * @throws FileNotFoundException if the file does not exist at the specified path.
     */
    public static FileInputStream returnFile(String path) throws FileNotFoundException {
        // In Java, the '!' operator is used for logical NOT.
        // The String class has a startsWith() method, similar to Python.
        if (!path.startsWith("/safedir")) {
            System.out.println("File cannot be accessed");
            // In Java, 'null' is the equivalent of Python's 'None' for objects.
            return null;
        }
        
        // 'open(path)' in Python is equivalent to creating a new FileInputStream
        // or FileReader in Java. FileInputStream is used for byte streams.
        // This can throw a FileNotFoundException, which we declare the method can throw.
        return new FileInputStream(path);
    }

    /**
     * Example of how to use the returnFile method.
     */
    public static void main(String[] args) {
        // Example 1: Access Denied
        System.out.println("Attempting to access a forbidden path...");
        try {
            FileInputStream forbiddenFile = returnFile("/etc/passwd");
            if (forbiddenFile == null) {
                System.out.println("Method correctly returned null for forbidden path.\n");
            }
        } catch (FileNotFoundException e) {
            // This part won't be reached in this specific case
            System.err.println("Caught an unexpected exception: " + e.getMessage());
        }

        // Example 2: Access Granted (assuming the file exists)
        // Note: For this to work, you must create a directory named 'safedir'
        // with a file named 'test.txt' inside it at the root of your project.
        System.out.println("Attempting to access an allowed path...");
        String safePath = "/safedir/test.txt"; // This path is relative to your project root
        try {
            FileInputStream allowedFile = returnFile(safePath);
            if (allowedFile != null) {
                System.out.println("Successfully opened file: " + safePath);
                // It's crucial to close streams when you're done with them.
                allowedFile.close();
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found at the safe path. Please create '/safedir/test.txt'.");
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
}