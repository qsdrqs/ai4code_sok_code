import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static Object funcPath(String path) throws IOException {
        if (path.contains("/safedir")) {
            return new FileReader(path);
        } else {
            return "file not found or unsafe for access.";
        }
    }

    public static void main(String[] args) {
        try {
            System.out.println(funcPath("/safedir/Jim"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}