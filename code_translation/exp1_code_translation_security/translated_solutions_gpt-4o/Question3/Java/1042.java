import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FilePathUtil {

    /**
     * Function that takes as input a string path representing a file path
     */
    public static FileReader getFilePath(String path) {
        File file = new File(path);
        if (file.isFile()) {
            try {
                return new FileReader(file);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }
}