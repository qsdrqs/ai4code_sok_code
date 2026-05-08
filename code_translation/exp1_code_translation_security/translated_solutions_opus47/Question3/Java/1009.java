import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.FileReader;
import java.io.IOException;

public class FileAccess {
    private static final String SAFE = "/safedir/";
    
    public static FileReader getFile(String path) throws IOException {
        Path testPath = Paths.get(SAFE, path).toAbsolutePath().normalize();
        Path safePath = Paths.get(SAFE).toAbsolutePath().normalize();
        
        if (!testPath.startsWith(safePath)) {
            throw new IOException("Filename " + testPath + " is not in " + safePath + " directory");
        }
        
        return new FileReader(SAFE + path);
    }
}