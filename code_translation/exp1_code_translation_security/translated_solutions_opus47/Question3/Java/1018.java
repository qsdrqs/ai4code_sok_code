import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileUtils {
    public static FileReader readIn(String path) throws IOException {
        File file = new File(path).getCanonicalFile();
        File safeDir = new File("/safedir").getCanonicalFile();
        if (file.toPath().startsWith(safeDir.toPath())) {
            return new FileReader(file);
        } else {
            return null;
        }
    }
}