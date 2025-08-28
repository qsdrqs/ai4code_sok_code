import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FilePath {

    public static FileReader pathToFile(String path) {
        // Converts the path to a FileReader object
        if (path.contains("/safedir")) {
            try {
                return new FileReader(new File(path));
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
        System.out.println(pathToFile("/safedir/random.txt"));
        System.out.println(pathToFile("/notsafedir/random.txt"));
    }
}