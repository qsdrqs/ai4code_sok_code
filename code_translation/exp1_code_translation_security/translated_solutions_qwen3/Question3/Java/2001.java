import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;

public class FileSecurity {

    public static final String SAFE_DIR = "/safedir";

    public static FileReader getFile(String path) throws IOException {
        // Normalize the path to match Python's os.path.normpath
        String normalizedPath = Paths.get(path).normalize().toString();

        // Check if the normalized path starts with SAFE_DIR
        if (!normalizedPath.startsWith(SAFE_DIR)) {
            throw new IllegalArgumentException("Path must be in SAFE_DIR");
        }

        // Return a FileReader for the original path
        return new FileReader(path);
    }
}