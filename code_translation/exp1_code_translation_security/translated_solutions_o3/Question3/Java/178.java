import java.io.FileReader;
import java.io.FileNotFoundException;

public class Main {

    private static final String ALLOWED_PATH = "/safedir";

    /**
     * Attempts to open a file for reading if it resides in the allowed directory.
     * If the path is outside the allowed directory, a message is returned instead.
     *
     * NOTE:  This mirrors the Python behaviour where the function can return either
     *        a String or a file handle.  For that reason the return type is Object.
     */
    public static Object getFile(String path) {
        // Check that the first 8 characters match "/safedir"
        if (!path.substring(0, 8).equals(ALLOWED_PATH)) {
            return "Not allowed to get file from this dir";
        }

        try {
            return new FileReader(path);   // Equivalent to Python's open(path, "r")
        } catch (FileNotFoundException e) {
            return e.getMessage();         // Simple error handling (optional)
        }
    }

    public static void main(String[] args) {
        // Example usage – reproduces the final call in the Python snippet
        Object result = getFile("/safedir/test/path");

        // For demonstration, just print what we received
        System.out.println(result);
    }
}