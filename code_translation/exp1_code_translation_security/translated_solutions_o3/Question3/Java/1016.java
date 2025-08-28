import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class SafePath {

    /**
     * Converts the supplied path to an open file (InputStream) if the path
     * is located under "/safedir".  Otherwise, or if the file cannot be
     * opened, returns {@code null}.
     */
    public static InputStream pathToFile(String path) {
        if (path.contains("/safedir")) {            // same check as Python code
            try {
                return new FileInputStream(path);   // open for reading
            } catch (FileNotFoundException e) {
                return null;                        // mimic Python example’s silent failure
            }
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(pathToFile("/safedir/random.txt"));   // prints stream (or null on failure)
        System.out.println(pathToFile("/notsafedir/random.txt")); // always prints null
    }
}