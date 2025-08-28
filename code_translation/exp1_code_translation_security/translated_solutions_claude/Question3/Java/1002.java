import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileHandler {
    
    public static InputStream openFile(String filename) {
        Path path = Paths.get(filename);
        path = path.toAbsolutePath().normalize();
        
        int nameCount = path.getNameCount();
        if (nameCount >= 2 && path.getName(1).toString().equals("safedir")) {
            try {
                return new FileInputStream(path.toFile());
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }
    
    public static void test() {
        assert openFile("/safedir/../a/b") == null;
        
        try {
            openFile("/safedir/a");
            assert false;
        } catch (Exception e) {
            // Expected exception, test passes
        }
    }
    
    public static void main(String[] args) {
        test();
    }
}