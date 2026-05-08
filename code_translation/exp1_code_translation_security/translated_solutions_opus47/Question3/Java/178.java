import java.io.FileReader;
import java.io.IOException;

public class FileHandler {
    private static final String ALLOWED_PATH = "/safedir";
    
    public static Object getFile(String path) throws IOException {
        if (!path.substring(0, 8).equals(ALLOWED_PATH)) {
            return "Not allowed to get file from this dir";
        }
        return new FileReader(path);
    }
    
    public static void main(String[] args) throws IOException {
        getFile("/safedir/test/path");
    }
}