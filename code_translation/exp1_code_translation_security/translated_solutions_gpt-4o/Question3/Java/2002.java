import java.io.File;

public class FileUtil {
    // Path relative to /safedir, e.g. hello/world to refer to /safedir/hello/world
    public static File getFile(String path) {
        String filename = "/safedir/" + path;
        return new File(filename);
    }
}