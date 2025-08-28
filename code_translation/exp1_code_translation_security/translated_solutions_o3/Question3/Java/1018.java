import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class FileUtils {

    /**
     * Opens a file only if the supplied path contains “/safedir”.
     *
     * @param path the file system path to open
     * @return an InputStream for the file, or {@code null} if the path is not in “/safedir”
     * @throws FileNotFoundException if the file cannot be found when it is supposed to be opened
     */
    public static InputStream readIn(String path) throws FileNotFoundException {
        if (path != null && path.contains("/safedir")) {
            return new FileInputStream(path);
        }
        return null;  // Mirrors Python’s ‘None’ when the condition isn’t satisfied
    }
}