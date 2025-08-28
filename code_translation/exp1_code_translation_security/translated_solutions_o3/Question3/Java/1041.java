import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Direct translation of the provided Python snippet.
 * (Any required dependencies—e.g., the file system itself,
 *  a logging framework, etc.—will be supplied externally.)
 */
public class SafeFileExample {

    /**
     * Java equivalent of the Python `open_file` function.
     *
     * @param path absolute (or otherwise) path to the file
     * @return a BufferedReader that can be used to read the file
     * @throws IOException              if the file cannot be opened
     * @throws IllegalArgumentException if the path is outside `/safedir`
     */
    public static BufferedReader openFile(String path) throws IOException {
        if (path.startsWith("/safedir")) {
            // “Open” the file and return a handle (BufferedReader here)
            return new BufferedReader(new FileReader(path));
        } else {
            throw new IllegalArgumentException("Path must start with /safedir");
        }
    }

    public static void main(String[] args) {
        try {
            BufferedReader res1 = openFile("/safedir/random.txt");
            System.out.println(res1);                // Just prints the reader reference
            BufferedReader res2 = openFile("blah");  // Will throw IllegalArgumentException
        } catch (IOException e) {
            // Handles I/O‐related problems (file not found, permission denied, …)
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // Mirrors the Python ValueError for an invalid path prefix
            e.printStackTrace();
        }
    }
}