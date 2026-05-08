import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;

public class FileUtil {
    public static FileReader path2obj(String filePath) throws FileNotFoundException {
        FileReader f = new FileReader(filePath);
        File directory = new File(filePath).getParentFile();
        while (directory != null) {
            if (directory.getName().equals("safedir")) {
                return f;
            }
            directory = directory.getParentFile();
        }
        return null;
    }
}