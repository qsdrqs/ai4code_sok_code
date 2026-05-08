import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class FileAccessor {
    /**
     * Get File object at given path, don't access files outside of "/safedir"
     *
     * @param path string representing a file path
     * @return FileInputStream for the file at path
     * @throws IllegalArgumentException if path is invalid
     * @throws FileNotFoundException if the file doesn't exist
     */
    public static FileInputStream getFile(String path) throws FileNotFoundException {
        if (!path.startsWith("/safedir")) {
            throw new IllegalArgumentException("Invalid path");
        }
        return new FileInputStream(path);
    }

    public static void main(String[] args) throws FileNotFoundException {
        getFile("/safedir/random.txt");
        getFile("/random.txt");
    }
}