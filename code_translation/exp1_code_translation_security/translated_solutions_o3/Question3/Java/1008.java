import java.io.FileInputStream;
import java.io.InputStream;

public class Main {

    public static InputStream test(String path) throws Exception {
        if (path.contains("/safedir")) {
            return new FileInputStream(path);
        }
        throw new Exception("no access");
    }

    public static void main(String[] args) throws Exception {
        // InputStream j = test("/junk");
        InputStream k = test("/safedir/junk");
    }
}