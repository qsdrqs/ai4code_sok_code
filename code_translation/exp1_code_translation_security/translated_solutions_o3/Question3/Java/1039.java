import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class SafeFile {

    /**
     * Opens a file only if the supplied path is inside “/safedir”.
     * 
     * @param path absolute path to the file
     * @return a FileInputStream for the file, or {@code null} if the path is disallowed
     * @throws FileNotFoundException if the file does not exist (and the path was allowed)
     */
    public static FileInputStream returnFile(String path) throws FileNotFoundException {
        if (!path.startsWith("/safedir")) {
            System.out.println("File cannot be accessed");
            return null;
        }
        return new FileInputStream(path);
    }
}