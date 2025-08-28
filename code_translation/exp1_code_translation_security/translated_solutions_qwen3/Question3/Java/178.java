import java.io.FileReader;
import java.io.FileNotFoundException;

public class SomeClass {
    private static final String ALLOWED_PATH = "/safedir";

    public static Object getFile(String path) {
        if (!path.startsWith(ALLOWED_PATH)) {
            return "Not allowed to get file from this dir";
        }

        try {
            return new FileReader(path);
        } catch (FileNotFoundException e) {
            return "File not found";
        }
    }

    public static void main(String[] args) {
        getFile("/safedir/test/path");
    }
}