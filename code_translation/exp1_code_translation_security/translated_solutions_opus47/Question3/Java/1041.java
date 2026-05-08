import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Main {
    public static FileInputStream openFile(String path) throws FileNotFoundException {
        if (path.startsWith("/safedir")) {
            return new FileInputStream(path);
        } else {
            throw new IllegalArgumentException("Path must start with /safedir");
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        FileInputStream res1 = openFile("/safedir/random.txt");
        System.out.println(res1);
        FileInputStream res2 = openFile("blah");
    }
}