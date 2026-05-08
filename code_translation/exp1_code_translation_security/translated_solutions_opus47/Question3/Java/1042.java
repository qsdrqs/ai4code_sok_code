import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;

public class FileUtils {
    /**
     * function that takes as input a string path representing a file path
     */
    public static FileReader getFilePath(String path) {
        File file = new File(path);
        if (file.isFile()) {
            try {
                return new FileReader(path);
            } catch (FileNotFoundException e) {
                return null;
            }
        } else {
            return null;
        }
    }
}