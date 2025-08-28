import java.io.FileReader;
import java.io.Reader;
import java.io.FileNotFoundException;

public class Main {

    /**
     * create file object for path
     */
    public static Reader createFileObject(String path) {
        if (path.startsWith("/safedir")) {
            try {
                // Equivalent to Python’s  open(path, "r")
                return new FileReader(path);      // opens the file for reading
            } catch (FileNotFoundException e) {
                // Couldn’t open the file – just fall back to Python-style behaviour
                return null;
            }
        }
        // Path is outside the allowed directory
        return null;
    }

    public static void main(String[] args) {
        System.out.println(createFileObject("/etc/passwd"));
    }
}