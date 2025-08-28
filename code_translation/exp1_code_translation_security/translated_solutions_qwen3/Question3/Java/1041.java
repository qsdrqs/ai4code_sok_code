import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws Exception {
        FileInputStream res1 = open_file("/safedir/random.txt");
        System.out.println(res1);
        FileInputStream res2 = open_file("blah");
        System.out.println(res2);
    }

    public static FileInputStream open_file(String path) throws FileNotFoundException {
        if (path.startsWith("/safedir")) {
            return new FileInputStream(path);
        } else {
            throw new IllegalArgumentException("Path must start with /safedir");
        }
    }
}