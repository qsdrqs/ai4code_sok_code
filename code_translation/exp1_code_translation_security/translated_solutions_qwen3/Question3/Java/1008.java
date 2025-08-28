import java.io.FileInputStream;

public class Main {

    public static FileInputStream test(String path) throws Exception {
        if (path.contains("/safedir")) {
            return new FileInputStream(path);
        } else {
            throw new Exception("no access");
        }
    }

    public static void main(String[] args) {
        // FileInputStream j = test("/junk");
        try {
            FileInputStream k = test("/safedir/junk");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}