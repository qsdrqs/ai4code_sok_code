import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class FileReader {
    
    public static FileInputStream readIn(String path) {
        if (path.contains("/safedir")) {
            try {
                return new FileInputStream(path);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }
}