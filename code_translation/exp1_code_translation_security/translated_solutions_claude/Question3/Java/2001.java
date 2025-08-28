import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileHandler {
    private static final String SAFE_DIR = "/safedir";
    
    public static BufferedReader getFile(String path) throws IOException {
        String normalizedPath = Paths.get(path).normalize().toString();
        
        if (!normalizedPath.startsWith(SAFE_DIR)) {
            throw new IllegalArgumentException("Path must be in SAFE_DIR");
        }
        
        return new BufferedReader(new FileReader(path));
    }
}