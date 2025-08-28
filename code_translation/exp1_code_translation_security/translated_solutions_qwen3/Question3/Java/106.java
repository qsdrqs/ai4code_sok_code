import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Main {
    public static FileInputStream getFile(String path) throws FileNotFoundException {
        if (!path.startsWith("/safedir")) {
            throw new IllegalArgumentException("Invalid path");
        }
        return new FileInputStream(path);
    }

    public static void main(String[] args) throws Exception {
        getFile("/safedir/random.txt");
        getFile("/random.txt");
    }
}