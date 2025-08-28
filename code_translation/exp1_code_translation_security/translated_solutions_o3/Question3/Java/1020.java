import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    /**
     * Attempts to open a file only if the supplied path contains "/safedir".
     * 
     * @param path the file system path to check/open
     * @return a BufferedReader if the path is considered safe and the file opens correctly;
     *         otherwise, a fallback message is returned
     */
    public static Object funcPath(String path) {
        // Check that the path contains the safe directory
        if (path.contains("/safedir")) {
            try {
                // Equivalent to Python's open(path)
                return new BufferedReader(new FileReader(path));
            } catch (IOException e) {
                // Any I/O error (e.g., file not found) returns the same fallback string
                return "file not found or unsafe for access.";
            }
        } else {
            // Path did not pass the basic safety check
            return "file not found or unsafe for access.";
        }
    }

    public static void main(String[] args) {
        // Example call, mirrors `print(funcPath("/safedir/Jim"))` in Python
        System.out.println(funcPath("/safedir/Jim"));
    }
}