import java.io.FileReader;
import java.io.IOException;

public class FileHandler {
    public static FileReader readIn(String path) throws IOException {
        if (path.contains("/safedir")) {
            return new FileReader(path);
        } else {
            return null;
        }
    }
}