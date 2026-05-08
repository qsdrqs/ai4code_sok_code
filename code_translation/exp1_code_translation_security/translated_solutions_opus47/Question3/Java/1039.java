import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileHandler {
    public static FileInputStream returnFile(String path) throws IOException {
        File safeDir = new File("/safedir").getCanonicalFile();
        File requested = new File(path).getCanonicalFile();

        if (!requested.toPath().startsWith(safeDir.toPath())) {
            System.out.println("File cannot be accessed");
            return null;
        }
        return new FileInputStream(requested);
    }
}