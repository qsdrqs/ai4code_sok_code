import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileUtil {

    /**
     * Function that takes a string path representing a file path.
     * Returns a FileReader if the path is a valid file, otherwise returns null.
     * If the file exists but cannot be opened, an IOException is thrown.
     */
    public static FileReader getFilePath(String path) throws IOException {
        File file = new File(path);
        if (file.isFile()) {
            return new FileReader(file);
        } else {
            return null;
        }
    }
}