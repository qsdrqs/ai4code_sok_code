import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileHandler {
    private static final String SAFE = "/safedir/";
    
    public static FileReader getFile(String path) throws Exception, IOException {
        // via https://stackoverflow.com/a/56097763
        Path testPath = Paths.get(SAFE).resolve(path).normalize();
        Path safeDir = Paths.get(SAFE).normalize();
        
        if (!testPath.startsWith(safeDir)) {
            throw new Exception("Filename " + testPath + " is not in " + safeDir + " directory");
        }
        
        return new FileReader(SAFE + path);
    }
}