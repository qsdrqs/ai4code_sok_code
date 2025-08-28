import java.io.FileReader;
import java.io.IOException;

/**
 * This class provides a utility to safely access files from a specific directory.
 */
public class FileUtility {

    // A constant defining the allowed directory prefix.
    // 'private' makes it accessible only within this class.
    // 'static' means it belongs to the class, not an instance.
    // 'final' means it cannot be changed (a constant).
    private static final String ALLOWED_PATH = "/safedir";

    /**
     * Gets a FileReader for a given path, but only if it's within the ALLOWED_PATH.
     *
     * @param path The full path to the file.
     * @return A FileReader object for the requested file.
     * @throws SecurityException if the path is not in the allowed directory.
     * @throws IOException if the file is not found or another I/O error occurs.
     */
    public static FileReader getFile(String path) throws SecurityException, IOException {
        // Check if the path starts with the allowed directory prefix.
        // This is the Java equivalent of Python's path[0:8] check, but more robust.
        if (path == null || !path.startsWith(ALLOWED_PATH)) {
            // Instead of returning an error string, throw an exception.
            // This is the standard way to handle errors in Java.
            throw new SecurityException("Not allowed to get file from this dir: " + path);
        }
        
        // If the check passes, return a new FileReader object.
        // This is the Java equivalent of Python's open(path, 'r').
        // This can throw a FileNotFoundException (a subclass of IOException).
        return new FileReader(path);
    }

    /**
     * Main method to demonstrate the usage of the getFile method.
     */
    public static void main(String[] args) {
        // --- Test Case 1: Allowed Path ---
        System.out.println("Attempting to access an allowed path...");
        try {
            // The path to the file we want to access.
            String allowedFilePath = "/safedir/test/path";
            FileReader reader = getFile(allowedFilePath);
            System.out.println("Success! File reader created for: " + allowedFilePath);
            // In a real application, you would use the 'reader' object here.
            // It's crucial to close the reader when done to free up system resources.
            reader.close();
        } catch (Exception e) {
            // This block catches any exception thrown, like SecurityException or IOException.
            System.err.println("Error: " + e.getMessage());
        }

        System.out.println("\n----------------------------------------\n");

        // --- Test Case 2: Disallowed Path ---
        System.out.println("Attempting to access a disallowed path...");
        try {
            String disallowedFilePath = "/etc/passwd";
            FileReader reader = getFile(disallowedFilePath);
            // This line should not be reached.
            System.out.println("Success! File reader created for: " + disallowedFilePath);
            reader.close();
        } catch (Exception e) {
            // We expect a SecurityException here.
            System.out.println("Caught expected error: " + e.getMessage());
        }
    }
}