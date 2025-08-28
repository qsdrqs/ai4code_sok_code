import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class SafeDir {

    /**
     * Returns a FileInputStream for the file located in /safedir.
     * The given path is interpreted relative to /safedir, e.g.,
     * "hello/world" refers to "/safedir/hello/world".
     */
    public static FileInputStream getFile(String path) throws FileNotFoundException {
        String filename = "/safedir/" + path;
        return new FileInputStream(new File(filename));
    }
}