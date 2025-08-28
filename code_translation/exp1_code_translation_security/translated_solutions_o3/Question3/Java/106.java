import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class SafeFile {

    /**
     * Get a file input stream for the given path, but do not allow access
     * outside of the "/safedir" directory.
     *
     * @param path  String representing a file path
     * @return      FileInputStream for the file at the given path
     * @throws FileNotFoundException   if the file does not exist
     * @throws IllegalArgumentException if the path is outside "/safedir"
     */
    public static FileInputStream getFile(String path)
            throws FileNotFoundException {

        if (path == null || !path.startsWith("/safedir")) {
            throw new IllegalArgumentException("Invalid path");
        }
        return new FileInputStream(path);
    }

    // Example usage
    public static void main(String[] args) {
        try {
            getFile("/safedir/random.txt"); // allowed
            getFile("/random.txt");         // throws IllegalArgumentException
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}