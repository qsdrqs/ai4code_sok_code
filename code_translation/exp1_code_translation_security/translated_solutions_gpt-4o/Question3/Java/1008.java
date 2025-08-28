import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static FileReader test(String path) throws Exception {
        if (path.contains("/safedir")) {
            return new FileReader(path);
        }
        throw new Exception("no access");
    }

    public static void main(String[] args) {
        try {
            // FileReader j = test("/junk");
            FileReader k = test("/safedir/junk");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}