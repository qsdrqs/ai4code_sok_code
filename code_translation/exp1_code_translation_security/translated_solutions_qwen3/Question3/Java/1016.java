import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    /**
     * Converts the path to a FileReader object if the path contains "/safedir".
     * Otherwise, returns null.
     *
     * @param path The file path to check and open.
     * @return A FileReader object if the path is valid and contains "/safedir", otherwise null.
     * @throws IOException If an I/O error occurs while opening the file.
     */
    public static FileReader pathToFile(String path) throws IOException {
        if (path.contains("/safedir")) {
            return new FileReader(new File(path));
        } else {
            return null;
        }
    }

    public static void main(String[] args) throws IOException {
        // Test cases
        System.out.println(pathToFile("/safedir/random.txt"));
        System.out.println(pathToFile("/notsafedir/random.txt"));
    }
}