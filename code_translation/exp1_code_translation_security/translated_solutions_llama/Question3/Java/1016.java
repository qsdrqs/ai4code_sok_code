import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    /**
     * Converts the path to a FileReader object if the path contains "/safedir".
     * 
     * @param path The path to the file.
     * @return A FileReader object if the path is safe, otherwise null.
     */
    public static FileReader pathToFile(String path) {
        if (path.contains("/safedir")) {
            try {
                return new FileReader(path);
            } catch (FileNotFoundException e) {
                System.err.println("File not found: " + e.getMessage());
                return null;
            }
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
        FileReader safeFile = pathToFile("/safedir/random.txt");
        FileReader unsafeFile = pathToFile("/notsafedir/random.txt");

        System.out.println(safeFile);
        System.out.println(unsafeFile);
    }
}