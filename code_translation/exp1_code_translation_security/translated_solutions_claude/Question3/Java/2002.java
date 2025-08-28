import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class FileHelper {
    
    // Path relative to /safedir, e.g. hello/world to refer to /safedir/hello/world
    public static InputStream getFile(String path) throws FileNotFoundException {
        String filename = "/safedir/" + path;
        return new FileInputStream(new File(filename));
    }
}