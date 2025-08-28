import java.io.File;

public class FileHandler {
    public static File getFile(String path) {
        String filename = "/safedir/" + path;
        return new File(filename);
    }
}