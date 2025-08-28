import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.FileNotFoundException;

/**
 * Utility class for file operations.
 */
public class FileUtils {

    /**
     * Opens a file as an InputStream if its path is deemed safe (contains "/safedir").
     * In Python, a bare `return` statement returns `None`. The equivalent in Java is `null`.
     *
     * @param path The path to the file.
     * @return An InputStream for the file if the path is safe, otherwise null.
     * @throws FileNotFoundException if the file exists in a safe directory but cannot be found.
     */
    public static InputStream readIn(String path) throws FileNotFoundException {
        if (path.contains("/safedir")) {
            // open(path) in Python is equivalent to creating a new FileInputStream in Java.
            return new FileInputStream(path);
        } else {
            // A bare `return` in Python returns `None`, which is `null` in Java.
            return null;
        }
    }
}