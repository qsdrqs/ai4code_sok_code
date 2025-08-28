import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileUtils {
    
    /**
     * Function that takes as input a string path representing a file path
     * @param path The file path as a string
     * @return FileReader object if file exists, null otherwise
     */
    public static FileReader getFilePath(String path) {
        File file = new File(path);
        if (file.isFile()) {
            try {
                return new FileReader(file);
            } catch (IOException e) {
                // Handle the exception - in this case, return null if file cannot be opened
                return null;
            }
        } else {
            return null;
        }
    }
}