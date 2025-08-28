import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * A utility class for safely reading files from a designated directory.
 */
public class SafeFileReader {

    /**
     * Checks if a given file path is considered safe.
     * A path is safe if it starts with "/safedir" and does not contain "/..",
     * which prevents directory traversal attacks.
     *
     * @param myString The file path string to check.
     * @return true if the path is safe, false otherwise.
     */
    public static boolean checkIfSafe(String myString) {
        // In Java, 'in' for substrings is 'contains()', and 'and not' is '&& !'
        return myString.startsWith("/safedir") && !myString.contains("/..");
    }

    /**
     * Opens a file for reading if and only if the path is safe.
     *
     * @param filepath The full path to the file.
     * @return A FileInputStream for the file if the path is safe and the file exists,
     *         otherwise returns null.
     */
    public static FileInputStream getFile(String filepath) {
        if (checkIfSafe(filepath)) {
            try {
                // The equivalent of Python's open(filepath, "r") is to create
                // a FileInputStream or FileReader.
                return new FileInputStream(filepath);
            } catch (FileNotFoundException e) {
                // The path was safe, but the file doesn't exist.
                // We return null, similar to the "not safe" case.
                System.err.println("Error: File not found at safe path: " + filepath);
                return null;
            }
        } else {
            // The path was not safe.
            System.err.println("Error: Unsafe file path access denied: " + filepath);
            return null;
        }
    }

    /**
     * Main method to demonstrate the usage of the SafeFileReader.
     * NOTE: For this example to work, you must create a directory named 'safedir'
     * in your root directory ('/') and place a file named 'test.txt' inside it.
     * This is often not practical; real-world paths would be more complex.
     */
    public static void main(String[] args) {
        // Example of a safe path (will likely fail unless you create /safedir/test.txt)
        String safePath = "/safedir/test.txt";
        System.out.println("Attempting to access: " + safePath);
        FileInputStream safeFileStream = getFile(safePath);
        if (safeFileStream != null) {
            System.out.println("Success! File stream obtained.");
            // Remember to close the stream when done
            try {
                safeFileStream.close();
            } catch (java.io.IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Failed to obtain file stream.");
        }

        System.out.println("\n----------------------------------\n");

        // Example of an unsafe path (directory traversal attempt)
        String unsafePath = "/safedir/../etc/passwd";
        System.out.println("Attempting to access: " + unsafePath);
        FileInputStream unsafeFileStream = getFile(unsafePath);
        if (unsafeFileStream != null) {
            System.out.println("Success! File stream obtained. (This should not happen)");
        } else {
            System.out.println("Failed to obtain file stream as expected.");
        }
    }
}