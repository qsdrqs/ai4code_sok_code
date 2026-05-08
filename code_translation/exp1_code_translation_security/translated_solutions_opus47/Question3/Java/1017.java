import java.io.File;
import java.io.IOException;

public class FileService {
    public File getFile(String path) throws Exception {
        File file = new File(path).getCanonicalFile();
        File safeDir = new File("/safedir").getCanonicalFile();
        if (file.toPath().startsWith(safeDir.toPath())) {
            return file;
        } else {
            throw new Exception("Access denied");
        }
    }
}