import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println(createFileObject("/etc/passwd"));
    }

    public static FileReader createFileObject(String path) throws IOException {
        if (path.startsWith("/safedir")) {
            return new FileReader(path);
        } else {
            return null;
        }
    }
}