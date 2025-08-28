import java.io.File;
import java.io.FileNotFoundException;

/**
 * Get File object at given path, don't access files outside of "/safedir"
 * 
 * @param path string representing a file path
 * @return File object at path
 * @throws IllegalArgumentException if the path is invalid
 */
public class Main {

    public static File getFile(String path) {
        if (!path.startsWith("/safedir")) {
            throw new IllegalArgumentException("Invalid path");
        }
        return new File(path);
    }

    public static void main(String[] args) {
        try {
            System.out.println(getFile("/safedir/random.txt").getAbsolutePath());
            System.out.println(getFile("/random.txt").getAbsolutePath());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}